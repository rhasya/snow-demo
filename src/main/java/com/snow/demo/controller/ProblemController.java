package com.snow.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snow.demo.model.Problem;
import com.snow.demo.service.ProblemService;

@RestController
@RequestMapping("/api/v1")
public class ProblemController {
    
    private final ProblemService service;

    public ProblemController(ProblemService service) {
        this.service = service;
    }

    @GetMapping("/problems")
    public List<Problem> listProblems() {
        return service.listProblems();
    }
}
