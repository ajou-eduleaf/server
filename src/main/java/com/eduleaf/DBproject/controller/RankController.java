package com.eduleaf.DBproject.controller;

import com.eduleaf.DBproject.dto.*;
import com.eduleaf.DBproject.service.AccountService;
import com.eduleaf.DBproject.service.RankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rank")
public class RankController {

    private final RankService rankService;

    public RankController(RankService rankService){
        this.rankService = rankService;
    }

    @GetMapping("/show")
    public Object rank(@RequestParam String type, @RequestParam Integer academyId){
        return rankService.rank(type, academyId);
    }

}

