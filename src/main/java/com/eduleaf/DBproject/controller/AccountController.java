package com.eduleaf.DBproject.controller;

import com.eduleaf.DBproject.dto.AcademyFormDto;
import com.eduleaf.DBproject.dto.GroupFormDto;
import com.eduleaf.DBproject.dto.ParentSignUpFormDto;
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
    public String signUpAcademy(@ModelAttribute AcademyFormDto academyFormDto){
        accountService.createAcademy(academyFormDto);
        return academyFormDto.getName() + " 학원 등록 완료";
    }

    @PostMapping("/signup/teacher")
    public String signUpTeacher(@ModelAttribute TeacherSignUpFormDto teacherSignUpFormDto){
        accountService.createTeacher(teacherSignUpFormDto);
        return teacherSignUpFormDto.getName() + " 선생님 등록 완료";
    }

    @PostMapping("/signup/group")
    public String signUpGroup(@ModelAttribute GroupFormDto groupFormDto){
        accountService.createGroup(groupFormDto);
        return groupFormDto.getName() + "반 등록 완료";
    }

    @PostMapping("/signup/student")
    public String signUpStudent(@ModelAttribute StudentSignUpFormDto studentSignUpFormDto){
        accountService.createStudent(studentSignUpFormDto);
        return studentSignUpFormDto.getName() + " 학생 등록 완료";
    }

    @PostMapping("/signup/parent")
    public String signUpParent(@ModelAttribute ParentSignUpFormDto parentSignUpFormDto){
        accountService.createParent(parentSignUpFormDto);
        return parentSignUpFormDto.getName() + " 학부모 등록 완료";
    }

    @PostMapping("/login")
    public UserResponseDto login(@ModelAttribute UserLoginFormDto userLoginFormDto){
        return accountService.login(userLoginFormDto);
    }

}
