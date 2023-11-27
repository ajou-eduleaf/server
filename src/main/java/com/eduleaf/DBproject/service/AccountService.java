package com.eduleaf.DBproject.service;

import com.eduleaf.DBproject.domain.Academy;
import com.eduleaf.DBproject.domain.Group;
import com.eduleaf.DBproject.domain.Parent;
import com.eduleaf.DBproject.domain.Student;
import com.eduleaf.DBproject.domain.Teacher;
import com.eduleaf.DBproject.dto.AcademyFormDto;
import com.eduleaf.DBproject.dto.GroupFormDto;
import com.eduleaf.DBproject.dto.ParentSignUpFormDto;
import com.eduleaf.DBproject.dto.StudentSignUpFormDto;
import com.eduleaf.DBproject.dto.TeacherSignUpFormDto;
import com.eduleaf.DBproject.dto.UserLoginFormDto;
import com.eduleaf.DBproject.dto.UserResponseDto;
import com.eduleaf.DBproject.dto.UserResponseDto.UserType;
import com.eduleaf.DBproject.repository.academy.AcademyRepository;
import com.eduleaf.DBproject.repository.group.GroupRepository;
import com.eduleaf.DBproject.repository.parent.ParentRepository;
import com.eduleaf.DBproject.repository.student.StudentRepository;
import com.eduleaf.DBproject.repository.teacher.TeacherRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class AccountService {

    private final AcademyRepository academyRepository;
    private final TeacherRepository teacherRepository;
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;

    public AccountService(AcademyRepository academyRepository, TeacherRepository teacherRepository,
                          GroupRepository groupRepository, StudentRepository studentRepository,
                          ParentRepository parentRepository) {
        this.academyRepository = academyRepository;
        this.teacherRepository = teacherRepository;
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.parentRepository = parentRepository;
    }

    public void createAcademy(AcademyFormDto academyFormDto) {
        academyRepository.findByAcademyName(academyFormDto.getName())
                .ifPresent((academy) -> {
                    throw new IllegalStateException("이미 존재하는 학원입니다.");
                });

        Academy academy = academyFormDto.toEntity();
        academyRepository.save(academy);
    }

    public void createTeacher(TeacherSignUpFormDto teacherFormDto) {
        Academy academy = academyRepository.findByAcademyName(teacherFormDto.getAcademyName())
                .orElseThrow(() -> {
                    throw new IllegalStateException("존재하지 않는 학원입니다.");
                });

        teacherRepository.findTeacherByTeacherId(teacherFormDto.getTeacherId())
                .ifPresent((teacher) -> {
                    throw new IllegalStateException("이미 존재하는 선생님입니다.");
                });

        System.out.println("teacherFormDto = " + teacherFormDto.getTeacherId());
        Teacher teacher = teacherFormDto.toEntity(academy);
        teacherRepository.save(teacher);
    }

    public void createGroup(GroupFormDto groupFormDto) {
        Academy academy = academyRepository.findByAcademyName(groupFormDto.getAcademyName())
                .orElseThrow(() -> {
                    throw new IllegalStateException("존재하지 않는 학원입니다.");
                });

        Teacher teacher = teacherRepository.findTeacherByTeacherName(groupFormDto.getTeacherName())
                .orElseThrow(() -> {
                    throw new IllegalStateException("존재하지 않는 학원입니다.");
                });

        groupRepository.findGroupByName(groupFormDto.getName())
                .ifPresent((group) -> {
                    throw new IllegalStateException("이미 존재하는 반입니다.");
                });

        Group group = groupFormDto.toEntity(academy, teacher);
        groupRepository.save(group);
    }


    public void createStudent(StudentSignUpFormDto studentSignUpFormDto) {
        Academy academy = academyRepository.findByAcademyName(studentSignUpFormDto.getAcademyName())
                .orElseThrow(() -> {
                    throw new IllegalStateException("존재하지 않는 학원입니다.");
                });

        Group group = groupRepository.findGroupByName(studentSignUpFormDto.getGroupName())
                .orElseThrow(() -> {
                    throw new IllegalStateException("존재하지 않는 반입니다.");
                });

        studentRepository.findByBojId(studentSignUpFormDto.getBojId())
                .ifPresent((student) -> {
                    throw new IllegalStateException("이미 존재하는 학생입니다.");
                });

        Student student = studentSignUpFormDto.toEntity(academy, group);
        studentRepository.save(student);
    }

    public void createParent(ParentSignUpFormDto parentSignUpFormDto) {

        Student student = studentRepository.findStudentByStudentId(parentSignUpFormDto.getStudentId())
                .orElseThrow(() -> {
                    throw new IllegalStateException("존재하지 않는 학생입니다.");
                });
        Parent parent = parentSignUpFormDto.toEntity(student);
        parentRepository.save(parent);
    }

    public UserResponseDto login(UserLoginFormDto userLoginFormDto) {
        // 선생님 로그인 시도
        Optional<Teacher> teacher = teacherLogin(userLoginFormDto);
        if (teacher.isPresent()) {
            return createUserResponseDto(teacher.get(), UserType.teacher);
        }

        // 학생 로그인 시도
        Optional<Student> student = studentLogin(userLoginFormDto);
        if (student.isPresent()) {
            return createUserResponseDto(student.get(), UserType.student);
        }

        // 학부모 로그인 시도
        Optional<Parent> parent = parentLogin(userLoginFormDto);
        if (parent.isPresent()) {
            return createUserResponseDto(parent.get(), UserType.parent);
        }

        throw new IllegalStateException("존재하지 않는 사용자입니다.");
    }

    private UserResponseDto createUserResponseDto(Object user, UserResponseDto.UserType userType) {
        String id, name, academyName, groupName = ""; // groupName은 나중에 수정

        if (userType == UserType.teacher) {
            Teacher teacher = (Teacher) user;
            id = teacher.getTeachId();
            name = teacher.getName();
            academyName = teacher.getAcademy().getName();
        } else if (userType == UserType.student) {
            Student student = (Student) user;
            id = student.getStudentId();
            name = student.getName();
            academyName = student.getAcademy().getName();
        } else {
            Parent parent = (Parent) user;
            id = parent.getParentId();
            name = parent.getName();
            academyName = parent.getStudent().getAcademy().getName();
        }

        return UserResponseDto.builder()
                .id(id)
                .name(name)
                .type(userType)
                .academyName(academyName)
                .groupName(groupName)
                .build();
    }

    private Optional<Teacher> teacherLogin(UserLoginFormDto userLoginFormDto) {
        return teacherRepository.findTeacherByTeacherId(userLoginFormDto.getId());
    }

    private Optional<Student> studentLogin(UserLoginFormDto userLoginFormDto) {
        return studentRepository.findStudentByStudentId(userLoginFormDto.getId());
    }

    private Optional<Parent> parentLogin(UserLoginFormDto userLoginFormDto) {
        return parentRepository.findParentByParentId(userLoginFormDto.getId());
    }
}
