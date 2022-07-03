package com.snow.demo.util;

import java.io.PrintWriter;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PythonCompileUtil implements CompileUtil {
    @Override
    public void compile(String source) {
        // save
        save(source);
    }

    /**
     * Save
     * @param source
     */
    private void save(String source) {
        try (PrintWriter pw = new PrintWriter("/tmp/snowbox/solution.py")) {
            pw.print(source);
        } catch(Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
