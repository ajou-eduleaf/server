package com.eduleaf.DBproject.controller;

import com.eduleaf.DBproject.dto.LessonInfoRequestFormDto;
import com.eduleaf.DBproject.dto.LessonInfoResponseDto;
import com.eduleaf.DBproject.service.CrawlingService;
import com.eduleaf.DBproject.service.LessonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;
    private final CrawlingService crawlingService;

    public LessonController(LessonService lessonService, CrawlingService crawlingService) {
        this.lessonService = lessonService;
        this.crawlingService = crawlingService;
    }

    @GetMapping("/{lessonId}/info")
    public LessonInfoResponseDto getLessonInfo(@PathVariable int lessonId, @ModelAttribute LessonInfoRequestFormDto lessonInfoRequestFormDto) {
        return lessonService.getLessonInfo(lessonId, lessonInfoRequestFormDto);
    }

    @PutMapping("/{lessonId}/register/problem/{problemId}")
    public String addProblemToLesson(@PathVariable int lessonId, @PathVariable String problemId) {
        lessonService.addProblemToLesson(lessonId, problemId);
        return problemId + "번 문제가 " + lessonId + " 수업에 등록되었습니다.";
    }

    @PatchMapping("/{lessonId}/attendance/{studentBojId}")
    public String toggleStudentAttendanceOfLesson(@PathVariable int lessonId, @PathVariable String studentBojId) {
        lessonService.toggleStudentAttendanceOfLesson(lessonId, studentBojId);
        return studentBojId + " 학생의 " + lessonId + " 수업 출석 상태가 변경되었습니다.";
    }

    @PostMapping("/{lessonId}/register/group/{groupName}")
    public String addAllStudentsInGroupToLesson(@PathVariable int lessonId, @PathVariable String groupName) {
        lessonService.addAllStudentsInGroupToLesson(groupName, lessonId);
        return groupName + " 학생들이 " + lessonId + " 수업에 등록되었습니다.";
    }

    @PostMapping("/{lessonId}/register/student/{studentBojId}")
    public String addStudentsToLesson(@PathVariable int lessonId, @PathVariable String studentBojId) {
        lessonService.addStudentToLesson(lessonId, studentBojId);
        return studentBojId + " 학생이 " + lessonId + " 수업에 등록되었습니다.";
    }

    @PostMapping("/{lessonId}/refresh")
    public String crawlLesson(@PathVariable int lessonId) {
        crawlingService.crawlingWithLessonId(lessonId);
        return lessonId + " 수업의 정보가 업데이트 되었습니다.";
    }
}
