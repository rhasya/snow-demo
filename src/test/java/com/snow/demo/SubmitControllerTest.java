package com.snow.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SubmitControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @Test
    void testSubmitSource() throws Exception {
        mockMvc.perform(post("/api/v1/submit-source"))
            .andExpect(status().isOk());
    }
}