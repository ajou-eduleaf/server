package com.eduleaf.DBproject.service;

import com.eduleaf.DBproject.domain.Group;
import com.eduleaf.DBproject.domain.Lesson;
import com.eduleaf.DBproject.domain.LessonProblem;
import com.eduleaf.DBproject.domain.Problem;
import com.eduleaf.DBproject.domain.Student;
import com.eduleaf.DBproject.domain.StudentLesson;
import com.eduleaf.DBproject.domain.StudentProblem;
import com.eduleaf.DBproject.dto.LessonInfoResponseDto;
import com.eduleaf.DBproject.dto.ProblemSolvingStatusDto;
import com.eduleaf.DBproject.dto.StudentInfoDto;
import com.eduleaf.DBproject.repository.group.GroupRepository;
import com.eduleaf.DBproject.repository.lesson.LessonRepository;
import com.eduleaf.DBproject.repository.lessonproblem.LessonProblemRepository;
import com.eduleaf.DBproject.repository.problem.ProblemRepository;
import com.eduleaf.DBproject.repository.stdentproblem.StudentProblemRepository;
import com.eduleaf.DBproject.repository.student.StudentRepository;
import com.eduleaf.DBproject.repository.studentlesson.StudentLessonRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;
    private final StudentLessonRepository studentLessonRepository;
    private final GroupRepository groupRepository;
    private final ProblemRepository problemRepository;
    private final LessonProblemRepository lessonProblemRepository;
    private final StudentProblemRepository studentProblemRepository;

    public LessonService(LessonRepository lessonRepository, StudentRepository studentRepository,
                         StudentLessonRepository studentLessonRepository, GroupRepository groupRepository,
                         ProblemRepository problemRepository, LessonProblemRepository lessonProblemRepository,
                         StudentProblemRepository studentProblemRepository) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.studentLessonRepository = studentLessonRepository;
        this.groupRepository = groupRepository;
        this.problemRepository = problemRepository;
        this.lessonProblemRepository = lessonProblemRepository;
        this.studentProblemRepository = studentProblemRepository;
    }

    public void toggleStudentAttendanceOfLesson(int lessonId, String studentBojId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 수업입니다.");
        });

        Student student = studentRepository.findByBojId(studentBojId).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 학생입니다.");
        });

        StudentLesson studentLesson = studentLessonRepository.findByStudentAndLesson(student, lesson)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 수업입니다.");
                });

        studentLesson.toggleAttendance();
        studentLessonRepository.save(studentLesson);
    }

    public void addStudentToLesson(int lessonId, String studentBojId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 수업입니다.");
        });

        Student student = studentRepository.findByBojId(studentBojId).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 학생입니다.");
        });

        StudentLesson studentLesson = new StudentLesson(false, student, lesson);
        studentLessonRepository.save(studentLesson);
    }

    public void addAllStudentsInGroupToLesson(String groupName, int lessonId) {
        Group group = groupRepository.findGroupByName(groupName).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 반입니다.");
        });

        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 수업입니다.");
        });

        for (Student student : group.getStudents()) {
            StudentLesson studentLesson = new StudentLesson(false, student, lesson);
            studentLessonRepository.save(studentLesson);
        }
    }

    @Transactional
    public void addProblemToLesson(int lessonId, String problemId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 수업입니다.");
        });

        Problem problem = problemRepository.findById(problemId).orElseGet(() -> {
            Problem newProblem = new Problem(problemId);
            problemRepository.save(newProblem);
            return newProblem;
        });

        LessonProblem lessonProblem = new LessonProblem(lesson, problem);
        lessonProblemRepository.save(lessonProblem);
    }

    @Transactional
    public LessonInfoResponseDto getLessonInfo(int lessonId, String type) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 수업입니다.");
        });

        List<StudentLesson> studentLessonList = studentLessonRepository.findByLesson(lesson).orElseThrow(() -> {
            throw new IllegalStateException("존재하지 않는 수업입니다.");
        });

        List<Integer> todayProblems = lessonProblemRepository.findLessonProblemByLesson(lesson).orElseThrow(() -> {
            throw new IllegalArgumentException("수업에 해당하는 문제가 없습니다.");
        }).stream().map(LessonProblem::getProblem).map(Problem::getProblemNo).map(Integer::parseInt).toList();

        LessonInfoResponseDto lessonInfoResponseDto = new LessonInfoResponseDto();

        // for teacher
        for (StudentLesson studentLesson : studentLessonList) {
            boolean attendance = studentLesson.isAttendance();
            Student student = studentLesson.getStudent();
            String studentId = student.getStudentId();
            String studentBojId = student.getBojId();
            String studentName = student.getName();
            ProblemSolvingStatusDto problemSolvingStatusDto = checkProblemsDone(todayProblems, student);
            String groupName = student.getGroup().getName();

            StudentInfoDto studentInfoDto = StudentInfoDto.builder().bojId(studentBojId).name(studentName)
                    .problemSolvingStatusDto(problemSolvingStatusDto).isAttendance(attendance).groupName(groupName)
                    .build();
            lessonInfoResponseDto.addStudentInfo(studentId, studentInfoDto);
        }

        return lessonInfoResponseDto;
    }

    private ProblemSolvingStatusDto checkProblemsDone(List<Integer> todayProblems, Student student) {
        List<StudentProblem> studentProblems = studentProblemRepository.findByStudent(student);
        Set<Integer> solvedProblemsNo = studentProblems.stream()
                .map(sp -> Integer.parseInt(sp.getProblem().getProblemNo())).collect(Collectors.toSet());

        List<Integer> solvedProblems = new ArrayList<>();
        List<Integer> unsolvedProblems = new ArrayList<>();
        for (int problemNo : todayProblems) {
            if (solvedProblemsNo.contains(problemNo)) {
                solvedProblems.add(problemNo);
            } else {
                unsolvedProblems.add(problemNo);
            }
        }
        return new ProblemSolvingStatusDto(todayProblems, solvedProblems, unsolvedProblems);
    }
}
