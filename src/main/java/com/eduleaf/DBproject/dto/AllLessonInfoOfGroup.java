package com.eduleaf.DBproject.dto;

import com.eduleaf.DBproject.domain.Lesson;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
public class AllLessonInfoOfGroup {
    private final List<OneLessonInfoDto> allLessons;
    private final String groupName;

    @Builder
    public AllLessonInfoOfGroup(List<Lesson> allLessons, String groupName) {
        this.allLessons = new ArrayList<>();
        this.groupName = groupName;

        for (Lesson lesson : allLessons) {
            this.allLessons.add(OneLessonInfoDto.builder().lesson(lesson).build());
        }
    }
}
