package com.eduleaf.DBproject.repository.student;

import com.eduleaf.DBproject.domain.Student;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private final StudentJpaRepository studentJpaRepository;

    public StudentRepository(StudentJpaRepository studentJpaRepository) {
        this.studentJpaRepository = studentJpaRepository;
    }

    public Student save(Student student) {
        return studentJpaRepository.save(student);
    }

    public Optional<Student> findByBojId(String bojId) {
        return studentJpaRepository.findByBojId(bojId);
    }

    public Optional<Student> findStudentByStudentId(String studentId) {
        return studentJpaRepository.findStudentByStudentId(studentId);
    }
}
