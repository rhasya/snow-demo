package com.snow.demo;

import com.snow.demo.config.JwtProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SnowDemoApplicationTests {

    @Autowired
    JwtProcessor jwtProcessor;

    @Test
    void contextLoads() {
    }

    @Test
    void test1() {

    }
}
