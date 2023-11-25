package com.eduleaf.DBproject.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
public class GroupMembersDto {

    private String groupName;
    private List<String> students;
    private String teacherName;

    @Builder
    public GroupMembersDto(String groupName, List<String> students, String teacherName) {
        this.groupName = groupName;
        this.students = students;
        this.teacherName = teacherName;
    }
}
