package com.snow.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.tomcat.util.descriptor.InputSourceUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
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
    public String submitSource(@RequestBody SubmittedSource source) {
        final String lang = source.getLang();

        if ("cpp".equals(lang)) {
            // TODO: process cpp source
        } else if ("python".equals(lang)) {
            new PythonCompileUtil().compile(source.getSource());
        }
        // prepare input
        prepareInput(source.getProblemId());

        // run + judge
        int inputCount = 3;
        int score = run(lang, inputCount);
        logger.debug("SCORE: " + score);
        if (score == -1) {
            return "Error";
        }
        else if (score < inputCount) {
            return "Wrong Answer";
        }
        else {
            return "Correct Answer";
        }
    }

    private void prepareInput(Long problemId) {
        // clear prev input/output
        try {
            FileUtils.cleanDirectory(new File("/tmp/snowbox/tmp"));
            Files.list(Paths.get(String.format("/home/rhasya/problems/%d", problemId))).forEach(p -> {
                try {
                    Files.copy(p, Paths.get("/tmp/snowbox/tmp/" + p.getFileName()));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            });
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    private int run(String lang, int inputCount) {
        int score = 0;
        try {
            for (int i = 1; i <= inputCount; i++) {
                // run
                ProcessBuilder pb = new ProcessBuilder("/tmp/snowbox/sandbox", lang, "1000", "256m",
                    String.format("/tmp/input%d.txt", i), String.format("/tmp/answer%d.txt", i));
                Process p = pb.start();
                String err = new String(p.getErrorStream().readAllBytes());
                if (err.length() > 0) {
                    logger.error("STDERR: " + err);
                    return -1;
                } else {
                    String output = new String(p.getInputStream().readAllBytes());
                    logger.debug("STDOUT: " + output.trim());
                }

                // judge
                String ans = new String(Files.readAllBytes(Paths.get("/tmp/snowbox/tmp/answer" + i + ".txt"))).trim();
                String out = new String(Files.readAllBytes(Paths.get("/tmp/snowbox/tmp/output" + i + ".txt"))).trim();

                if (!ans.equals(out)) {
                    return score;
                }

                score++;
            }
        } catch(Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return score;
   }
}