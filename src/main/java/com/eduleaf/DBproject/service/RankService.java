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

    public List<RankResponseDto> rank(String type, String location) {


        List<RankResponseDto> response = new ArrayList<>();
        List<Object[]> studentProblemCounts;
        List<Object[]> studentZeroProblem;

        //academy가 존재하는지 확인
        Optional<Academy> academy = this.academyRepository.findByAcademyName(location);
        if(academy.isEmpty()) return response;

        switch(type){

            case "today":
                //지정된 academy 내의 모든 학생들의 문제 푼 개수 정보를 얻음 -> groupby + count(*)
                studentProblemCounts = this.studentProblemRepository.getTodayStudentProblemCount(location);
                studentZeroProblem = this.studentProblemRepository.getTodayStudentProblemZero(location);
                break;

            case "month":
                //지정된 academy 내의 모든 학생들의 문제 푼 개수 정보를 얻음 -> groupby + count(*)
                studentProblemCounts = this.studentProblemRepository.getMonthStudentProblemCount(location);
                studentZeroProblem = this.studentProblemRepository.getMonthStudentProblemZero(location);
                break;

            case "total":

                //지정된 academy 내의 모든 학생들의 문제 푼 개수 정보를 얻음 -> groupby + count(*)
                studentProblemCounts = this.studentProblemRepository.getAllStudentProblemCount(location);
                studentZeroProblem = this.studentProblemRepository.getAllStudentProblemZero(location);
                break;

            default:
                //type 이상하면 그냥 total로 해버림
                studentProblemCounts = this.studentProblemRepository.getAllStudentProblemCount(location);
                studentZeroProblem = this.studentProblemRepository.getAllStudentProblemZero(location);
                break;
        }

        //RankResponseDto 형태로 가공해서 반환
        for(Object[] one : studentProblemCounts.stream().toList()){

            List<Object> listOne = Arrays.stream(one).toList();
            String bojId = listOne.get(0).toString();
            Integer solved = Integer.valueOf(listOne.get(1).toString());

            RankResponseDto rankResponseDto =  RankResponseDto.builder()
                    .bojId(bojId)
                    .solved(solved)
                    .build();

            response.add(rankResponseDto);
        }

        //푼 개수가 0인 애도 넣기
        for(Object[] one : studentZeroProblem.stream().toList()){

            List<Object> listOne = Arrays.stream(one).toList();
            String bojId = listOne.get(0).toString();
            //Integer solved = Integer.valueOf(listOne.get(1).toString());

            RankResponseDto rankResponseDto =  RankResponseDto.builder()
                    .bojId(bojId)
                    .solved(0)
                    .build();

            response.add(rankResponseDto);
        }

        return response;
    }
}
