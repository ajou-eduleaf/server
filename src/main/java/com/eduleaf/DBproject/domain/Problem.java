package com.eduleaf.DBproject.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "problem")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Problem {

    @Id
    private String problemNo;

    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY)
    private final List<StudentProblem> studentProblems = new ArrayList<>();

    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY)
    private final List<LessonProblem> lessonProblems = new ArrayList<>();

    @Builder
    public Problem(String problemNo) {
        this.problemNo = problemNo;
    }
}
