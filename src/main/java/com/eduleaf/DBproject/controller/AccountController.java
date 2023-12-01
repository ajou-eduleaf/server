package com.eduleaf.DBproject.controller;

import com.eduleaf.DBproject.dto.AcademyFormDto;
import com.eduleaf.DBproject.dto.GroupFormDto;
import com.eduleaf.DBproject.dto.ParentSignUpFormDto;
import com.eduleaf.DBproject.dto.ResponseMessageDto;
import com.eduleaf.DBproject.dto.StudentSignUpFormDto;
import com.eduleaf.DBproject.dto.TeacherSignUpFormDto;
import com.eduleaf.DBproject.dto.UserLoginFormDto;
import com.eduleaf.DBproject.dto.UserResponseDto;
import com.eduleaf.DBproject.service.AccountService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
    유저 관련 컨트롤러
    회원가입, 로그인 진행
 */

@RestController
@RequestMapping("/api/users")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("/signup/academy")
    public ResponseMessageDto signUpAcademy(@ModelAttribute AcademyFormDto academyFormDto){
        return accountService.createAcademy(academyFormDto);
    }

    @PostMapping("/signup/teacher")
    public ResponseMessageDto signUpTeacher(@ModelAttribute TeacherSignUpFormDto teacherSignUpFormDto){
        return accountService.createTeacher(teacherSignUpFormDto);
    }

    @PostMapping("/signup/group")
    public ResponseMessageDto signUpGroup(@ModelAttribute GroupFormDto groupFormDto){
        return accountService.createGroup(groupFormDto);
    }

    @PostMapping("/signup/student")
    public ResponseMessageDto signUpStudent(@ModelAttribute StudentSignUpFormDto studentSignUpFormDto){
        return accountService.createStudent(studentSignUpFormDto);
    }

    @PostMapping("/signup/parent")
    public ResponseMessageDto signUpParent(@ModelAttribute ParentSignUpFormDto parentSignUpFormDto){
        return accountService.createParent(parentSignUpFormDto);
    }

    @PostMapping("/login")
    public UserResponseDto login(@ModelAttribute UserLoginFormDto userLoginFormDto){
        return accountService.login(userLoginFormDto);
    }

}
