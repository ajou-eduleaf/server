package com.eduleaf.DBproject.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
public class GroupMemberSettingForm {
    private final String teacherId;
    private final List<String> studentBojId;

    @Builder
    public GroupMemberSettingForm(String teacherId, List<String> studentBojId){
        this.teacherId = teacherId;
        this.studentBojId = studentBojId;
    }
}
