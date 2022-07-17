package com.snow.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.snow.demo.model.Problem;

@DataJpaTest
public class ProblemRepositoryTest {

    @Autowired
    private ProblemRepository repository;

    @Test
    public void listTest() {
        List<Problem> list = repository.findAllByOrderById();
        assertEquals(0, list.size());
    }
}
