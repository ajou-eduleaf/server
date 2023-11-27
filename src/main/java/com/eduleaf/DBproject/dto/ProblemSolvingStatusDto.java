package com.eduleaf.DBproject.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
public class ProblemSolvingStatusDto {
    private List<Integer> todayProblems;
    private List<Integer> solvedProblems;
    private List<Integer> unsolvedProblems;

    @Builder
    public ProblemSolvingStatusDto(List<Integer> todayProblems, List<Integer> solvedProblems,
                                   List<Integer> unsolvedProblems) {
        this.todayProblems = todayProblems;
        this.solvedProblems = solvedProblems;
        this.unsolvedProblems = unsolvedProblems;
    }
}
