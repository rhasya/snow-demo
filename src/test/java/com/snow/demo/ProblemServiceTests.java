package com.snow.demo;

import com.snow.demo.model.Problem;
import com.snow.demo.service.ProblemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProblemServiceTests {

    @Autowired
    private ProblemService problemService;

    @Test
    public void saveProblem() {
        Problem problem = new Problem();
        problem.setTitle("Sample Problem");
        problem.setContent("This is Content");
        problemService.save(problem);
    }

    @Test
    public void getProblem() {
        Problem input = new Problem();
        input.setTitle("Sample Problem");
        input.setContent("This is Content");
        input = problemService.save(input);

        Problem output = problemService.getProblem(input.getId());

        assertEquals("Sample Problem", output.getTitle());
        assertEquals("This is Content", output.getContent());
    }

    @Test
    public void getProblems() {
        List<Problem> problems = problemService.getProblems();
        assertEquals(2, problems.size());
    }
}
