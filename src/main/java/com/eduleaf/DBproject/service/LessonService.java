package com.eduleaf.DBproject.service;

import com.eduleaf.DBproject.domain.Group;
import com.eduleaf.DBproject.domain.Lesson;
import com.eduleaf.DBproject.domain.LessonProblem;
import com.eduleaf.DBproject.domain.Problem;
import com.eduleaf.DBproject.domain.Student;
import com.eduleaf.DBproject.domain.StudentLesson;
import com.eduleaf.DBproject.domain.StudentProblem;
import com.eduleaf.DBproject.dto.LessonInfoRequestFormDto;
import com.eduleaf.DBproject.dto.LessonInfoResponseDto;
import com.eduleaf.DBproject.dto.ProblemSolvingStatusDto;
import com.eduleaf.DBproject.dto.StudentInfoDto;
import com.eduleaf.DBproject.repository.group.GroupRepository;
import com.eduleaf.DBproject.repository.lesson.LessonRepository;
import com.eduleaf.DBproject.repository.lessonproblem.LessonProblemRepository;
import com.eduleaf.DBproject.repository.parent.ParentRepository;
import com.eduleaf.DBproject.repository.problem.ProblemRepository;
import com.eduleaf.DBproject.repository.studentProblem.StudentProblemRepository;
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
    private final ParentRepository parentRepository;

    public LessonService(LessonRepository lessonRepository, StudentRepository studentRepository,
                         StudentLessonRepository studentLessonRepository, GroupRepository groupRepository,
                         ProblemRepository problemRepository, LessonProblemRepository lessonProblemRepository,
                         StudentProblemRepository studentProblemRepository, ParentRepository parentRepository) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.studentLessonRepository = studentLessonRepository;
        this.groupRepository = groupRepository;
        this.problemRepository = problemRepository;
        this.lessonProblemRepository = lessonProblemRepository;
        this.studentProblemRepository = studentProblemRepository;
        this.parentRepository = parentRepository;
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

    public void addProblemToLesson(int lessonId, String problemId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> {
            throw new IllegalArgumentException("존재하지 않는 수업입니다.");
        });

        Problem problem = problemRepository.findById(problemId).orElseGet(() -> {
            Problem newProblem = new Problem(problemId);
            problemRepository.save(newProblem);
            return newProblem;
        });

        List<LessonProblem> lessonProblems = lessonProblemRepository.findLessonProblemByLesson(lesson).orElseThrow(() -> {
            throw new IllegalArgumentException("수업에 해당하는 문제가 없습니다.");
        });

        for (LessonProblem lessonProblem : lessonProblems) {
            if (lessonProblem.getProblem().equals(problem)) {
                throw new IllegalStateException("이미 등록된 문제입니다.");
            }
        }

        LessonProblem lessonProblem = new LessonProblem(lesson, problem);
        lessonProblemRepository.save(lessonProblem);
    }

    @Transactional
    public LessonInfoResponseDto getLessonInfo(int lessonId, LessonInfoRequestFormDto lessonInfoRequestFormDto) {
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
        String type = lessonInfoRequestFormDto.getType();

        if (type.equals("teacher")) {
            for (StudentLesson studentLesson : studentLessonList) {

                boolean attendance = studentLesson.isAttendance();
                Student student = studentLesson.getStudent();
                String studentId = student.getStudentId();
                String studentBojId = student.getBojId();
                String studentName = student.getName();
                String groupName = student.getGroup().getName();
                ProblemSolvingStatusDto problemSolvingStatusDto = checkProblemsDone(todayProblems, student);

                StudentInfoDto studentInfoDto = StudentInfoDto
                        .builder()
                        .bojId(studentBojId)
                        .name(studentName)
                        .problemSolvingStatusDto(problemSolvingStatusDto)
                        .isAttendance(attendance)
                        .groupName(groupName)
                        .build();

                lessonInfoResponseDto.addStudentInfo(studentId, studentInfoDto);
            }
        } else if (type.equals("student")) {
            String id = lessonInfoRequestFormDto.getId();
            Student student = studentRepository.findStudentByStudentId(id)
                    .orElseThrow(() -> {
                        throw new IllegalStateException("존재하지 않는 학생입니다.");
                    });
            // studentLessonList에서 해당 학생의 수업만 찾아서 그 수업의 출석 여부를 가져온다.
            StudentLesson studentLesson = studentLessonList.stream()
                    .filter(sl -> sl.getStudent().equals(student))
                    .findFirst()
                    .orElseThrow(() -> {
                        throw new IllegalStateException("존재하지 않는 학생입니다.");
                    });

            boolean attendance = studentLesson.isAttendance();
            String studentId = student.getStudentId();
            String studentBojId = student.getBojId();
            String studentName = student.getName();
            String groupName = student.getGroup().getName();

            ProblemSolvingStatusDto problemSolvingStatusDto = checkProblemsDone(todayProblems, student);

            StudentInfoDto studentInfoDto = StudentInfoDto.builder()
                    .bojId(studentBojId)
                    .name(studentName)
                    .problemSolvingStatusDto(problemSolvingStatusDto)
                    .isAttendance(attendance)
                    .groupName(groupName)
                    .build();

            lessonInfoResponseDto.addStudentInfo(studentId, studentInfoDto);
        } else if (type.equals("parent")) {
            String parent_id = lessonInfoRequestFormDto.getId();
            Student student = parentRepository.findParentByParentId(parent_id).orElseThrow(
                    () -> {
                        throw new IllegalStateException("존재하지 않는 부모입니다.");
                    }
            ).getStudent();

            StudentLesson studentLesson = studentLessonList.stream()
                    .filter(sl -> sl.getStudent().equals(student))
                    .findFirst()
                    .orElseThrow(() -> {
                        throw new IllegalStateException("존재하지 않는 학생입니다.");
                    });

            boolean attendance = studentLesson.isAttendance();
            String studentBojId = student.getBojId();
            String studentName = student.getName();
            String groupName = student.getGroup().getName();

            ProblemSolvingStatusDto problemSolvingStatusDto = checkProblemsDone(todayProblems, student);

            StudentInfoDto studentInfoDto = StudentInfoDto.builder()
                    .bojId(studentBojId)
                    .name(studentName)
                    .problemSolvingStatusDto(problemSolvingStatusDto)
                    .isAttendance(attendance)
                    .groupName(groupName)
                    .build();

            lessonInfoResponseDto.addStudentInfo(parent_id, studentInfoDto);
        } else {
            throw new IllegalStateException("존재하지 않는 사용자입니다.");
        }

        return lessonInfoResponseDto;
    }

    private ProblemSolvingStatusDto checkProblemsDone(List<Integer> todayProblems, Student student) {
        List<StudentProblem> studentProblems = studentProblemRepository.findByStudent(student).orElseThrow(() -> {
            throw new IllegalStateException("존재하지 않는 학생입니다.");
        });
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
