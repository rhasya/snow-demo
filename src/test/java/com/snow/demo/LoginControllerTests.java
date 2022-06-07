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

    private final MockMvc mockMvc;

    LoginControllerTests(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void test1() throws Exception {
        mockMvc.perform(get("/test")).andExpect(status().isOk());
    }

    @Test
    public void adminLoginTest() throws Exception {
        String token = mockMvc.perform(
                        post("/login")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"username\":\"admin\",\"password\":\"admin\"}")
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();

        String result = mockMvc
                .perform(get("/api/hello")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("hello admin", result);
    }

    @Test
    public void wrongUser() throws Exception {
        mockMvc.perform(post("/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user99\",\"password\":\"user99\"}")
        ).andExpect(status().isForbidden());
    }

    @Test
    public void wrongPassword() throws Exception {
        mockMvc.perform(post("/login")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"admin\",\"password\":\"user99\"}")
        ).andExpect(status().isForbidden());
    }

    @Test
    public void userLoginTest() throws Exception {
        String token = mockMvc.perform(
                        post("/login")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"username\":\"user1\",\"password\":\"user1\"}")
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();

        String result = mockMvc
                .perform(get("/api/hello")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("hello user1", result);
    }
}
