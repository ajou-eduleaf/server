package com.eduleaf.DBproject.dto;

import com.eduleaf.DBproject.domain.Academy;
import com.eduleaf.DBproject.domain.Teacher;
import lombok.Builder;
import lombok.Data;

@Data
public class TeacherSignUpFormDto {
    private final String name;
    private final String teacherId;
    private final String teacherPw;
    private final String academyName;

    @Builder
    public Teacher toEntity(Academy academy){
        return Teacher.builder()
                .name(name)
                .teachId(teacherId)
                .teachPw(teacherPw)
                .academy(academy)
                .build();
    }
}
