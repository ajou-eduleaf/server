package com.eduleaf.DBproject.controller;

import com.eduleaf.DBproject.domain.Lesson;
import com.eduleaf.DBproject.dto.AllLessonInfoOfGroup;
import com.eduleaf.DBproject.dto.GroupMemberSettingForm;
import com.eduleaf.DBproject.dto.GroupMembersDto;
import com.eduleaf.DBproject.dto.LessonSettingForm;
import com.eduleaf.DBproject.dto.ResponseMessageDto;
import com.eduleaf.DBproject.service.GroupManagementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/groups")
public class GroupManagementController {

    private final GroupManagementService groupManagementService;

    public GroupManagementController(GroupManagementService groupManagementService){
        this.groupManagementService = groupManagementService;
    }

    // 선생과 학생을 반에 등록하는 컨트롤러
    // 선생이 반을 등록할 때, 반 이름과 선생 이름을 받아서 반에 선생을 등록한다.
    @PostMapping("/{groupName}/register")
    public ResponseMessageDto registerTeacherAndStudentToGroup(@RequestBody GroupMemberSettingForm groupMemberSettingForm,
                                                               @PathVariable String groupName){
        return groupManagementService.settingGroupMember(groupName, groupMemberSettingForm);
    }

    @GetMapping("{groupName}")
    public GroupMembersDto getGroupMembersInfo(@PathVariable String groupName){
        return groupManagementService.getGroupInfo(groupName);
    }

    @PostMapping("/{groupName}/lesson")
    public ResponseMessageDto addLessonsToGroup(@PathVariable String groupName, @RequestBody LessonSettingForm lessonSettingForm){
        return groupManagementService.addLessonsToGroup(groupName, lessonSettingForm);
    }

    @GetMapping("{groupName}/lessons")
    public AllLessonInfoOfGroup getAllLessonsByGroupName(@PathVariable String groupName){
        return groupManagementService.getAllLessonsInfo(groupName);
    }
}
