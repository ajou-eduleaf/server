package com.eduleaf.DBproject.repository.stdentproblem;

import com.eduleaf.DBproject.domain.Student;
import com.eduleaf.DBproject.domain.StudentProblem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProblemJpaRepository extends JpaRepository<StudentProblem, Integer> {
    Optional<List<StudentProblem>> findByStudent(Student student);
}
