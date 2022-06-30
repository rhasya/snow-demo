package com.snow.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snow.demo.model.Source;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SubmitControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser("user1")
    void testSubmitSource() throws Exception {
        /* make test data */
        ObjectMapper mapper = new ObjectMapper();
        Source s = new Source();
        s.setUsername("snow");
        s.setLang("cpp");
        s.setSource("#include <cstdio>\nint main() {\n    printf(\"Hello World!\\\\n\");\n}");

        /* test */
        mockMvc.perform(post("/api/v1/submit-source")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(s)))
            .andExpect(status().isOk());
    }
}