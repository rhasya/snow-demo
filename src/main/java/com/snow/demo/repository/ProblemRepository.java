package com.snow.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snow.demo.model.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
