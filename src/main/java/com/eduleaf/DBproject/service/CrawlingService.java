package com.eduleaf.DBproject.service;

import com.eduleaf.DBproject.domain.Lesson;
import com.eduleaf.DBproject.domain.LessonProblem;
import com.eduleaf.DBproject.domain.Problem;
import com.eduleaf.DBproject.domain.Student;
import com.eduleaf.DBproject.domain.StudentLesson;
import com.eduleaf.DBproject.domain.StudentProblem;
import com.eduleaf.DBproject.dto.ResponseMessageDto;
import com.eduleaf.DBproject.repository.lesson.LessonRepository;
import com.eduleaf.DBproject.repository.lessonproblem.LessonProblemRepository;
import com.eduleaf.DBproject.repository.problem.ProblemRepository;
import com.eduleaf.DBproject.repository.studentProblem.StudentProblemRepository;
import com.eduleaf.DBproject.repository.student.StudentRepository;
import com.eduleaf.DBproject.repository.studentlesson.StudentLessonRepository;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrawlingService {

    private final LessonRepository lessonRepository;
    private final LessonProblemRepository lessonProblemRepository;
    private final StudentLessonRepository studentLessonRepository;
    private final StudentProblemRepository studentProblemRepository;
    private final ProblemRepository problemRepository;
    private final StudentRepository studentRepository;

    public CrawlingService(LessonRepository lessonRepository, LessonProblemRepository lessonProblemRepository,
                           StudentLessonRepository studentLessonRepository,
                           StudentProblemRepository studentProblemRepository,
                           ProblemRepository problemRepository, StudentRepository studentRepository) {
        this.lessonRepository = lessonRepository;
        this.lessonProblemRepository = lessonProblemRepository;
        this.studentLessonRepository = studentLessonRepository;
        this.studentProblemRepository = studentProblemRepository;
        this.problemRepository = problemRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public ResponseMessageDto crawlingWithLessonId(int lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 수업입니다.");
        });

        List<Integer> todayProblems = lessonProblemRepository.findLessonProblemByLesson(lesson).orElseThrow(() -> {
            throw new IllegalArgumentException("수업에 해당하는 문제가 없습니다.");
        }).stream().map(LessonProblem::getProblem).map(Problem::getProblemNo).map(Integer::parseInt).toList();

        List<StudentLesson> studentLessonList = studentLessonRepository.findByLesson(lesson).orElseThrow(() -> {
            throw new IllegalStateException("존재하지 않는 수업입니다.");
        });

        for (StudentLesson studentLesson : studentLessonList) {
            String studentBojId = studentLesson.getStudent().getBojId();
            crawlingWithStudentBojId(studentBojId, todayProblems);
        }

        return ResponseMessageDto.builder()
                .message(lessonId + "의 수업 정보 새로고침(크롤링) 완료")
                .build();
    }

    private void crawlingWithStudentBojId(String studentBojId, List<Integer> todayProblems) {
        Student student = studentRepository.findByBojId(studentBojId).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 학생입니다.");
        });

        List<StudentProblem> studentSolvedProblems = studentProblemRepository.findByStudent(student).orElseThrow(() -> {
            throw new IllegalStateException("존재하지 않는 학생입니다.");
        });

        String correctProblemUrl = "https://www.acmicpc.net/status?user_id=" + studentBojId + "&result_id=4";
        List<Integer> correctProblemList = getCorrectProblemList(correctProblemUrl);
        for (Integer problemNo : correctProblemList) {
            if (todayProblems.contains(problemNo)) {
                Problem newProblem = problemRepository.findById(problemNo.toString()).orElseGet(() -> {
                    Problem problem = new Problem(problemNo.toString());
                    problemRepository.save(problem);
                    return problem;
                });

                if (!isStudentProblemExist(studentSolvedProblems, student, newProblem)) {
                    StudentProblem studentProblem = new StudentProblem(student, newProblem);
                    studentProblemRepository.save(studentProblem);
                    studentSolvedProblems.add(studentProblem);
                }
            }
        }
    }


    private List<Integer> getCorrectProblemList(String correctProblemUrl) {
        Document doc = null;
        try {
            doc = Jsoup.connect(correctProblemUrl).userAgent("Mozilla/5.0").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Elements problemListDivs = doc.select("table tbody tr");
        List<Integer> solvedProblems = problemListDivs.stream().map(element -> {
            String problemNo = element.select("a.problem_title").text();
            return Integer.parseInt(problemNo);
        }).toList();

        return solvedProblems;
    }

    private boolean isStudentProblemExist(List<StudentProblem> studentSolvedProblems, Student student, Problem problem) {
        return studentSolvedProblems.stream().anyMatch(sp -> sp.getStudent().equals(student) && sp.getProblem().equals(problem));
    }
}
