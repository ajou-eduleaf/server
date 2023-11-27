package com.eduleaf.DBproject.service;

import com.eduleaf.DBproject.domain.Group;
import com.eduleaf.DBproject.domain.Lesson;
import com.eduleaf.DBproject.domain.Student;
import com.eduleaf.DBproject.domain.Teacher;
import com.eduleaf.DBproject.dto.GroupMemberSettingForm;
import com.eduleaf.DBproject.dto.GroupMembersDto;
import com.eduleaf.DBproject.dto.LessonSettingForm;
import com.eduleaf.DBproject.repository.group.GroupRepository;
import com.eduleaf.DBproject.repository.lesson.LessonRepository;
import com.eduleaf.DBproject.repository.student.StudentRepository;
import com.eduleaf.DBproject.repository.teacher.TeacherRepository;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupManagementService {

    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    public GroupManagementService(GroupRepository groupRepository, TeacherRepository teacherRepository,
                                  StudentRepository studentRepository, LessonRepository lessonRepository) {
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
    }

    @Transactional
    public void settingGroupMember(String groupName, GroupMemberSettingForm groupMemberSettingForm) {

        System.out.println("teacherId = " + groupMemberSettingForm.getTeacherId());
        // 반 조회
        Group group = groupRepository.findGroupByName(groupName).orElseThrow(() -> {
            throw new IllegalStateException("존재하지 않는 반입니다.");
        });

        // 선생님 조회
        Teacher teacher = teacherRepository.findTeacherByTeacherId(groupMemberSettingForm.getTeacherId())
                .orElseThrow(() -> {

                    throw new IllegalStateException("존재하지 않는 선생님입니다.");
                });
        group.setTeacher(teacher);

        // 학생 조회
        for (String studentBojId : groupMemberSettingForm.getStudentBojId()) {
            Student student = studentRepository.findByBojId(studentBojId).orElseThrow(() -> {
                throw new IllegalStateException("존재하지 않는 학생입니다.");
            });
            group.addStudent(student);
        }
        groupRepository.save(group);
    }

    public GroupMembersDto getGroupInfo(String groupName) {
        Group group = groupRepository.findGroupByName(groupName).orElseThrow(() -> {
            throw new IllegalStateException("존재하지 않는 반입니다.");
        });

        GroupMembersDto groupMembersDto = GroupMembersDto.builder()
                .groupName(groupName)
                .teacherName(group.getTeacher().getName())
                .students(group.getStudents().stream().map(Student::getName).toList())
                .build();
        return groupMembersDto;
    }


    @Transactional
    public void addLessonsToGroup(String groupName, LessonSettingForm lessonSettingForm) {
        Group group = groupRepository.findGroupByName(groupName).orElseThrow(() -> {
            throw new IllegalStateException("존재하지 않는 반입니다.");
        });

        int lessonTimes = lessonSettingForm.getLessonTimes();
        Date startDate = lessonSettingForm.getStartDate();
        String contents = lessonSettingForm.getContents();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        for (int i = 0; i < lessonTimes; i++) {
            Date lessonDate = calendar.getTime();
            Lesson lesson = Lesson.builder()
                    .content(contents)
                    .date(lessonDate)
                    .group(group)
                    .build();
            group.addLesson(lesson);
            lessonRepository.save(lesson);
            calendar.add(Calendar.DATE, 7);
        }
        groupRepository.save(group);
    }
}
