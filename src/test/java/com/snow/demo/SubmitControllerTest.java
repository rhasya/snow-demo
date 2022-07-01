package com.snow.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class SubmitControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser("snow")
    void testSubmitSource() throws Exception {
        /* read test source */
        String source;
        try (FileInputStream fis = new FileInputStream("src/test/pythontest.txt")) {
            source = new String(fis.readAllBytes());
        }

        /* make test data */
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(
            Map.of("username", "snow", "lang", "python", "source", source)
        );

        /* test */
        mockMvc.perform(post("/api/v1/submit-source")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(str))
            .andExpect(status().isOk());
    }
}