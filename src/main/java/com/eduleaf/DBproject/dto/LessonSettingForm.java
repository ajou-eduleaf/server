package com.eduleaf.DBproject.dto;

import com.eduleaf.DBproject.domain.Group;
import com.eduleaf.DBproject.domain.Lesson;
import java.util.Calendar;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
public class LessonSettingForm {

    private final String contents;
    private final int lessonTimes; // 수업 횟수
    private final Date startDate;
}
