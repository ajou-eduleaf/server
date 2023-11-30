package com.eduleaf.DBproject.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
public class StudentInfoDto {
    private String bojId;
    private String name;
    private List<Integer> todayProblems;
    private List<Integer> solvedProblems;
    private List<Integer> unsolvedProblems;
    private boolean isAttendance;
    private String groupName;
    private boolean isFire;

    @Builder
    public StudentInfoDto(String bojId, String name, ProblemSolvingStatusDto problemSolvingStatusDto, boolean isAttendance, String groupName) {
        this.bojId = bojId;
        this.name = name;
        this.todayProblems = problemSolvingStatusDto.getTodayProblems();
        this.solvedProblems = problemSolvingStatusDto.getSolvedProblems();
        this.unsolvedProblems = problemSolvingStatusDto.getUnsolvedProblems();
        this.isAttendance = isAttendance;
        this.groupName = groupName;
        this.isFire = false;
    }

    public void makeFire() {
        this.isFire = true;
    }
}
