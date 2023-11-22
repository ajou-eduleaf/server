package com.eduleaf.DBproject.controller;

import com.eduleaf.DBproject.dto.RankResponseDto;
import com.eduleaf.DBproject.service.RankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RankController {

    @GetMapping("/rank")
    public List<RankResponseDto> rank(@RequestParam String type, @RequestParam String location){
        return RankService.rank(type, location);
    }

}
