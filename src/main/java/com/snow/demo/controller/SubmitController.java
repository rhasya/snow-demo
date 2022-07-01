package com.snow.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.snow.demo.model.SubmittedSource;
import com.snow.demo.util.PythonCompileUtil;

@RestController
@RequestMapping("/api/v1")
public class SubmitController {

    private static final Logger logger = LoggerFactory.getLogger(SubmitController.class);

    @PostMapping("/submit-source")
    public void submitSource(@RequestBody SubmittedSource source) {
        final String lang = source.getLang();

        if ("cpp".equals(lang)) {
            // TODO: process cpp source
        } else if ("python".equals(lang)) {
            new PythonCompileUtil().compile(source.getSource());
        }
        
        run(lang);
    }

    private void run(String lang) {
        // prepare input
        // run source
        // judgement
        try {
            ProcessBuilder pb = new ProcessBuilder("/tmp/snowbox/sandbox", lang, "1000", "256m",
                "/tmp/input.txt", "/tmp/output.txt");
            Process p = pb.start();
            String err = new String(p.getErrorStream().readAllBytes());
            if (err.length() > 0) {
                logger.error("STDERR: " + err);
            } else {
                String output = new String(p.getInputStream().readAllBytes());
                logger.debug("STDOUT: " + output);
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}