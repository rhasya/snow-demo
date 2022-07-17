package com.snow.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Override
    public Problem getProblem(Long id) {
        Optional<Problem> op = repository.findById(id);
        if (op.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "wrong id");
        }
        return op.get();
    }
}
