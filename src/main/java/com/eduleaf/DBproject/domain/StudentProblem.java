package com.eduleaf.DBproject.domain;

import jakarta.persistence.*;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student_problem")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Column
    private Date solvedDate;

    @Builder
    public StudentProblem(Student student, Problem problem) {
        this.student = student;
        this.problem = problem;
        this.solvedDate = new Date();
    }
}
