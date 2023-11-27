package com.eduleaf.DBproject.repository.studentlesson;

import com.eduleaf.DBproject.domain.Lesson;
import com.eduleaf.DBproject.domain.Student;
import com.eduleaf.DBproject.domain.StudentLesson;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentLessonJpaRepository extends JpaRepository<StudentLesson, Integer> {
    Optional<StudentLesson> findByStudentAndLesson(Student student, Lesson lesson);
    Optional<List<StudentLesson>> findByLesson(Lesson lesson);
}
