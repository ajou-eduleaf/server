package com.eduleaf.DBproject.controller;

import com.eduleaf.DBproject.dto.LessonInfoRequestFormDto;
import com.eduleaf.DBproject.dto.LessonInfoResponseDto;
import com.eduleaf.DBproject.dto.ResponseMessageDto;
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
    public ResponseMessageDto addProblemToLesson(@PathVariable int lessonId, @PathVariable String problemId) {
        return lessonService.addProblemToLesson(lessonId, problemId);
    }

    @PatchMapping("/{lessonId}/attendance/{studentId}")
    public ResponseMessageDto toggleStudentAttendanceOfLesson(@PathVariable int lessonId, @PathVariable String studentId) {
        return lessonService.toggleStudentAttendanceOfLesson(lessonId, studentId);
    }

    @PostMapping("/{lessonId}/register/group/{groupName}")
    public ResponseMessageDto addAllStudentsInGroupToLesson(@PathVariable int lessonId, @PathVariable String groupName) {
        return lessonService.addAllStudentsInGroupToLesson(groupName, lessonId);
    }

    @PostMapping("/{lessonId}/register/student/{studentBojId}")
    public ResponseMessageDto addStudentsToLesson(@PathVariable int lessonId, @PathVariable String studentBojId) {
        return lessonService.addStudentToLesson(lessonId, studentBojId);
    }

    @PostMapping("/{lessonId}/refresh")
    public ResponseMessageDto crawlLesson(@PathVariable int lessonId) {
        return crawlingService.crawlingWithLessonId(lessonId);
    }
}
