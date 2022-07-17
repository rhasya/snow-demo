package com.snow.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snow.demo.model.Problem;
import com.snow.demo.repository.ProblemRepository;
import com.snow.demo.service.ProblemService;

@Service
public class DefaultProblemService implements ProblemService {
    
    private final ProblemRepository repository;

    public DefaultProblemService(ProblemRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Problem> listProblems() {
        return repository.findAllByOrderById();
    }
}
