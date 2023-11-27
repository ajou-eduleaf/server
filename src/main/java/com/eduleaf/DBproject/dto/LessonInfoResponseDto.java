package com.eduleaf.DBproject.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
public class LessonInfoResponseDto {
    private Map<String, StudentInfoDto> studentInfo;

    @Builder
    public LessonInfoResponseDto() {
        this.studentInfo = new HashMap<>();
    }

    public void addStudentInfo(String bojId, StudentInfoDto studentInfoDto) {
        studentInfo.put(bojId, studentInfoDto);
    }
}
