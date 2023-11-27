package com.eduleaf.DBproject.repository.stdentproblem;

import com.eduleaf.DBproject.domain.Student;
import com.eduleaf.DBproject.domain.StudentProblem;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class StudentProblemRepository {

    private final StudentProblemJpaRepository studentProblemJpaRepository;

    public StudentProblemRepository(StudentProblemJpaRepository studentProblemJpaRepository) {
        this.studentProblemJpaRepository = studentProblemJpaRepository;
    }

    public void save(StudentProblem studentProblem){
        studentProblemJpaRepository.save(studentProblem);
    }

    public Optional<List<StudentProblem>> findByStudent(Student student){
        return studentProblemJpaRepository.findByStudent(student);
    }
}
