package com.eduleaf.DBproject.repository.student;

import com.eduleaf.DBproject.domain.Group;
import com.eduleaf.DBproject.domain.Student;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentJpaRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByBojId(String bojId);

    Optional <Student> findStudentByStudentId(String studentId);

    @Query("SELECT s.group FROM Student s WHERE s = :student ")
    List<Group> findGroupsByStudent(@Param("student") Student student);
}
