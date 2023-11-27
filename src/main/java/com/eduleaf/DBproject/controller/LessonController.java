package com.eduleaf.DBproject.controller;

import com.eduleaf.DBproject.dto.LessonInfoResponseDto;
import com.eduleaf.DBproject.service.LessonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/{lessonId}/info/{type}")
    public LessonInfoResponseDto getLessonInfo(@PathVariable int lessonId, @PathVariable String type) {
        return lessonService.getLessonInfo(lessonId, type);
    }

    @GetMapping("/{lessonId}/register/problem/{problemId}")
    public String addProblemToLesson(@PathVariable int lessonId, @PathVariable String problemId) {
        lessonService.addProblemToLesson(lessonId, problemId);
        return problemId + "번 문제가 " + lessonId + " 수업에 등록되었습니다.";
    }

    @GetMapping("/{lessonId}/attendance/{studentBojId}")
    public String toggleStudentAttendanceOfLesson(@PathVariable int lessonId, @PathVariable String studentBojId) {
        lessonService.toggleStudentAttendanceOfLesson(lessonId, studentBojId);
        return studentBojId + " 학생의 " + lessonId + " 수업 출석 상태가 변경되었습니다.";
    }

    @GetMapping("/{lessonId}/register/group/{groupName}")
    public String addAllStudentsInGroupToLesson(@PathVariable int lessonId, @PathVariable String groupName) {
        lessonService.addAllStudentsInGroupToLesson(groupName, lessonId);
        return groupName + " 학생들이 " + lessonId + " 수업에 등록되었습니다.";
    }

    @GetMapping("/{lessonId}/register/student/{studentBojId}")
    public String addStudentsToLesson(@PathVariable int lessonId, @PathVariable String studentBojId) {
        lessonService.addStudentToLesson(lessonId, studentBojId);
        return studentBojId + " 학생이 " + lessonId + " 수업에 등록되었습니다.";
    }
}
