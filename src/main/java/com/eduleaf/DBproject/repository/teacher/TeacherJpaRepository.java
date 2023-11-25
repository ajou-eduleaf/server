package com.eduleaf.DBproject.repository.teacher;

import com.eduleaf.DBproject.domain.Teacher;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherJpaRepository extends JpaRepository <Teacher, Integer> {
    Optional<Teacher> findTeacherByTeachId(String teachId);
    Optional<Teacher> findTeacherByName(String teacherName);
}
