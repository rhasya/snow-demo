package com.snow.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class ApiController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
