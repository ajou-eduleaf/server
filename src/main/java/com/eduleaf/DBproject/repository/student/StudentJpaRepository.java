package com.eduleaf.DBproject.repository.student;

import com.eduleaf.DBproject.domain.Student;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJpaRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByBojId(final String bojId);

    Optional <Student> findStudentByStudentId(final String studentId);
}
