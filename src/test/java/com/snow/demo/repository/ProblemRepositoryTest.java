package com.snow.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.snow.demo.model.Problem;

@DataJpaTest
public class ProblemRepositoryTest {

    @Autowired
    private ProblemRepository repository;

    public Problem insertData() {
        Problem p = new Problem();
        p.setTitle("title");
        p.setContent("content");

        return repository.save(p);
    }

    @Test
    public void listProblemTest() {
        insertData();

        List<Problem> list = repository.findAllByOrderById();
        assertEquals(1, list.size());
    }

    @Test
    public void getProblemTest() {
        Problem r = insertData();

        Optional<Problem> result = repository.findById(r.getId());
        assertTrue(result.isPresent());
        assertEquals("title", result.get().getTitle());
    }
}
