package com.eduleaf.DBproject.service;

import com.eduleaf.DBproject.domain.Academy;
import com.eduleaf.DBproject.domain.StudentProblem;
import com.eduleaf.DBproject.dto.AcademyFormDto;
import com.eduleaf.DBproject.dto.RankResponseDto;
import com.eduleaf.DBproject.dto.UserResponseDto;
import com.eduleaf.DBproject.repository.academy.AcademyRepository;
import com.eduleaf.DBproject.repository.group.GroupRepository;
import com.eduleaf.DBproject.repository.parent.ParentRepository;
import com.eduleaf.DBproject.repository.student.StudentRepository;
import com.eduleaf.DBproject.repository.studentProblem.StudentProblemJpaRepository;
import com.eduleaf.DBproject.repository.studentProblem.StudentProblemRepository;
import com.eduleaf.DBproject.repository.teacher.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RankService {

    private final AcademyRepository academyRepository;
    private final StudentProblemRepository studentProblemRepository;

    public RankService(AcademyRepository academyRepository, StudentProblemRepository studentProblemRepository) {
        this.academyRepository = academyRepository;
        this.studentProblemRepository = studentProblemRepository;
    }

    public Object rank(String type, Integer academyId) {


        List<RankResponseDto> response = new ArrayList<>();

        //academy가 존재하는지 확인
        Optional<Academy> academy = this.academyRepository.findById(academyId);
        if(academy.isEmpty()) return response;

        switch(type){

            case "today":
                break;

            case "month":
                break;

            case "total":

                //지정된 academy 내의 모든 학생들의 문제 푼 개수 정보를 얻음 -> groupby + count(*)
                List<Object[]> allStudentProblemCount = this.studentProblemRepository.getAllStudentProblemCount(academyId);


                //RankResponseDto 형태로 가공해서 반환
                for(Object[] one : allStudentProblemCount.stream().toList()){

                    List<Object> listOne = Arrays.stream(one).toList();
                    String bojId = listOne.get(0).toString();
                    Integer solved = Integer.valueOf(listOne.get(1).toString());

                    RankResponseDto rankResponseDto =  RankResponseDto.builder()
                            .bojId(bojId)
                            .solved(solved)
                            .build();

                    response.add(rankResponseDto);
                }
                break;

            default:
                break;
        }

        return response;
    }
}
