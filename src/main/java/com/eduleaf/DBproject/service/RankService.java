package com.eduleaf.DBproject.service;

import com.eduleaf.DBproject.dto.RankResponseDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RankService {
    public static List<RankResponseDto> rank(String type, String location) {

        //repository 생성되어야 작성 가능
        List<RankResponseDto> response = new ArrayList<>();
        return response;
    }
}
