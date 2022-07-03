package com.snow.demo.service;

import com.snow.demo.model.Problem;
import com.snow.demo.repository.ProblemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public Problem save(Problem problem) {
        return problemRepository.save(problem);
    }

    public Problem getProblem(Long id) {
        Optional<Problem> r = problemRepository.findById(id);
        if (r.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't find target problem");
        }

        return r.get();
    }

    public List<Problem> getProblems() {
        return problemRepository.findAll();
    }
}
