package com.eduleaf.DBproject.repository.studentProblem;

import com.eduleaf.DBproject.domain.Student;
import com.eduleaf.DBproject.domain.StudentProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentProblemJpaRepository extends JpaRepository<StudentProblem, Integer> {

    //지정된 academy 내에서의 모든 학생들을 group by하여 학생별 문제 푼 수 리스트 리턴
    @Query("SELECT sp.student.bojId, COUNT(*) FROM StudentProblem As sp WHERE sp.student.academy.name =:location GROUP BY sp.student ORDER BY COUNT(*)")
    List<Object[]> getAllStudentProblemCount(@Param(value = "location") String location);

    //today인 것만 조회
    @Query("SELECT sp.student.bojId, COUNT(*) FROM StudentProblem As sp WHERE sp.student.academy.name =:location AND sp.solvedDate = CURDATE() GROUP BY sp.student ORDER BY COUNT(*)")
    List<Object[]> getTodayStudentProblemCount(@Param(value = "location") String location);

    //month인 것만 조회
    @Query("SELECT sp.student.bojId, COUNT(*) FROM StudentProblem As sp WHERE sp.student.academy.name =:location AND year(sp.solvedDate) = year(now()) AND month(sp.solvedDate) = month(now()) GROUP BY sp.student ORDER BY COUNT(*)")
    List<Object[]> getMonthStudentProblemCount(@Param(value = "location") String location);

    Optional<List<StudentProblem>> findByStudent(Student student);

}
