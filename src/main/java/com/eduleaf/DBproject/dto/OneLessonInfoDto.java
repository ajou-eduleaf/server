package com.eduleaf.DBproject.dto;

import com.eduleaf.DBproject.domain.Lesson;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
public class OneLessonInfoDto {
    private final int lessonId;
    private final String content;
    private final Date date;

    @Builder
    public OneLessonInfoDto(Lesson lesson) {
        this.lessonId = lesson.getId();
        this.content = lesson.getContent();
        this.date = lesson.getDate();
    }
}
