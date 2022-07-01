package com.snow.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snow.demo.model.SubmittedSource;

@RestController
@RequestMapping("/api/v1")
public class SubmitController {

    @PostMapping("/submit-source")
    public void submitSource(@RequestBody SubmittedSource source) {
        if ("cpp".equals(source.getLang())) {
            // TODO: process cpp source
        } else if ("python".equals(source.getLang())) {
            // TODO: process python source
        }
    }
}