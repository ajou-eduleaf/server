package com.eduleaf.DBproject.repository.studentProblem;

import com.eduleaf.DBproject.domain.Academy;
import com.eduleaf.DBproject.domain.Student;
import com.eduleaf.DBproject.domain.StudentProblem;
import com.eduleaf.DBproject.repository.academy.AcademyJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentProblemRepository {

    private final StudentProblemJpaRepository studentProblemJpaRepository;

    public StudentProblemRepository(StudentProblemJpaRepository studentProblemJpaRepository) {
        this.studentProblemJpaRepository = studentProblemJpaRepository;
    }
    public void save(StudentProblem studentProblem){
        studentProblemJpaRepository.save(studentProblem);
    }


    public List<Object[]> getAllStudentProblemCount(String location){
        return studentProblemJpaRepository.getAllStudentProblemCount(location);
    }

    public List<Object[]> getTodayStudentProblemCount(String location){
        return studentProblemJpaRepository.getTodayStudentProblemCount(location);
    }

    public List<Object[]> getMonthStudentProblemCount(String location){
        return studentProblemJpaRepository.getMonthStudentProblemCount(location);
    }

    public Optional<List<StudentProblem>> findByStudent(Student student){
        return studentProblemJpaRepository.findByStudent(student);
    }
}
