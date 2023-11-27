package com.eduleaf.DBproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api")
@RestController
public class test {

    private static StudentRepository studentRepository = new StudentRepository();
    private static ToDoProblemRepository toDoProblemRepository = new ToDoProblemRepository();

    static class StudentRepository {
        public final Map<String, String> studentRepository;
        // StudentID, StudentName

        public StudentRepository() {
            this.studentRepository = new HashMap<>();
            studentRepository.put("sangjuntest", "박상준");
        }

        public String getValue(String studentId) {
            return this.studentRepository.get(studentId);
        }
    }

    static class ToDoProblemRepository {
        public final Map<String, List<String>> todoProblemRepository;

        // 반 이름, 문제 번호
        public ToDoProblemRepository() {
            this.todoProblemRepository = new HashMap<>();
            todoProblemRepository.put("토요일 오전 A반", Arrays.asList("1000", "1001", "1002"));
            todoProblemRepository.put("토요일 오후 B반", Arrays.asList("1000", "1001", "1002"));
        }

        public List<String> getValue(String banInfo) {
            return this.todoProblemRepository.get(banInfo);
        }
    }


    @GetMapping("/test")
    public String testCrawl(@RequestParam(name = "studentID") String studentID) {
        String crawlingUrl = "https://www.acmicpc.net/user/" + studentID;

        // 학생 이름
        String studentName = studentRepository.getValue(studentID);

        // 반 정보 가져오기

        // 풀어야할 문제
        List<String> todoProblems = toDoProblemRepository.getValue("토요일 오전 A반");

        try {
            Document doc = Jsoup.connect(crawlingUrl).userAgent("Mozilla/5.0").get();
            Elements problemListDivs = doc.select("div.problem-list");
            Element problemSolved, problemUnsolved;

            problemSolved = problemListDivs.get(0); // 예외 처리 필요 -> 사용자마다 div 위치가  다름.
            problemUnsolved = problemListDivs.get(1); // 예외 처리 필요

            // 맞은 문제들
            List<String> solvedProblems = new ArrayList<>();
            for (Element a : problemSolved.select("a")) {
                solvedProblems.add(a.text());
            }

            // 틀린 문제들
            List<String> unsolvedProblems = new ArrayList<>();
            for (Element a : problemUnsolved.select("a")) {
                unsolvedProblems.add(a.text());
            }

            // 사용자 하나의 결과를 key-value 쌍으로 만든다.
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("bojId", studentID);
            userInfo.put("problems", todoProblems);
            userInfo.put("solved_problems", solvedProblems);
            userInfo.put("unsolved_problems", unsolvedProblems);
            userInfo.put("isFire", false);

            // 사용자별 결과를 key-value 쌍에 저장한다.
            Map<String, Object> result = new HashMap<>();
            result.put(studentName, userInfo);

            // 결과 변환
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            String jsonResult = mapper.writeValueAsString(result);
            System.out.println(jsonResult);

            return jsonResult;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
