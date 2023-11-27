package com.eduleaf.DBproject.repository.studentlesson;

import com.eduleaf.DBproject.domain.Lesson;
import com.eduleaf.DBproject.domain.Student;
import com.eduleaf.DBproject.domain.StudentLesson;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class StudentLessonRepository {

    private final StudentLessonJpaRepository studentLessonJpaRepository;

    public StudentLessonRepository(StudentLessonJpaRepository studentLessonJpaRepository) {
        this.studentLessonJpaRepository = studentLessonJpaRepository;
    }

    public void save(StudentLesson studentLesson) {
        studentLessonJpaRepository.save(studentLesson);
    }

    public Optional<StudentLesson> findByStudentAndLesson(Student studentId, Lesson lesson) {
        return studentLessonJpaRepository.findByStudentAndLesson(studentId, lesson);
    }

    public Optional<List<StudentLesson>> findByLesson(Lesson lesson) {
        return studentLessonJpaRepository.findByLesson(lesson);
    }
}
