package com.snow.demo.service;

import java.util.List;

import com.snow.demo.model.Problem;

public interface ProblemService {
    List<Problem> listProblems();
    Problem getProblem(Long id);
}
