package com.snow.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LoginControllerTests {

    private static final String adminJws =
            "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFkbWluIn0.e0r24A4Xexy0xxpB8RLeWkiZO56-hnjaQxIpwpkxEDw";
    @Autowired
    MockMvc mockMvc;

    @Test
    public void test1() throws Exception {
        mockMvc.perform(get("/test")).andExpect(status().isOk());
    }

    @Test
    public void test2() throws Exception {
        String result = mockMvc.perform(
                    post("/login")
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"username\":\"admin\",\"password\":\"admin\"}")
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();

        assertEquals(adminJws, result);
    }

    @Test
    public void test3() throws Exception {
        String result = mockMvc
                .perform(get("/api/hello")
                        .header("Authorization", "Bearer " + adminJws))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertEquals("hello", result);
    }
}
