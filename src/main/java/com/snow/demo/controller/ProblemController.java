package com.snow.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snow.demo.model.Problem;
import com.snow.demo.repository.ProblemRepository;

@RestController
@RequestMapping("/api/v1")
public class ProblemController {
    
    private final ProblemRepository repository;

    public ProblemController(ProblemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/problems")
    public List<Problem> listProblems() {
        return repository.findAll();
    }

    @GetMapping("/problem/{id}")
    public Problem getProblem(@PathVariable Long id) {
        return repository.findById(id).get();
    }
}
