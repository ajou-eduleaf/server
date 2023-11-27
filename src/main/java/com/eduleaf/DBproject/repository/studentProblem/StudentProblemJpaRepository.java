package com.eduleaf.DBproject.repository.studentProblem;

import com.eduleaf.DBproject.domain.StudentProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentProblemJpaRepository extends JpaRepository<StudentProblem, Integer> {

    //지정된 academy 내에서의 모든 학생들을 group by하여 학생별 문제 푼 수 리스트 리턴
    @Query("SELECT sp.student.bojId, COUNT(*) FROM StudentProblem As sp WHERE sp.student.academy.id =:academyId GROUP BY sp.student ORDER BY COUNT(*)")
    List<Object[]> getAllStudentProblemCount(@Param(value = "academyId") Integer academyId);

}
