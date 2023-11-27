package com.eduleaf.DBproject.dto;

import com.eduleaf.DBproject.domain.Academy;
import com.eduleaf.DBproject.domain.Group;
import com.eduleaf.DBproject.domain.Student;
import lombok.Builder;
import lombok.Data;

@Data
public class StudentSignUpFormDto {
    private final String name;
    private final String bojId;
    private final String studentId;
    private final String studentPw;
    private final String academyName;
    private final String groupName;
    private final int age;

    @Builder
    public Student toEntity(Academy academy, Group group){
        return Student.builder()
                .name(name)
                .bojId(bojId)
                .studentId(studentId)
                .studentPw(studentPw)
                .academy(academy)
                .group(group)
                .age(age)
                .build();
    }
}
