package com.snow.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snow.demo.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTests {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private MockMvc mockMvc;

    @Test
    @WithMockUser("admin")
    public void usersTest() throws Exception {
        String res = mockMvc.perform(get("/api/users")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = mapper.readValue(res, new TypeReference<>() {});
        Assertions.assertEquals(3, users.size());
    }

    @Test
    @WithMockUser("admin")
    public void getUserTest() throws Exception {
        String res = mockMvc.perform(get("/api/user/1")).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(res, new TypeReference<>() {});
        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("admin", user.getUsername());
    }

    @Test
    @WithMockUser("admin")
    public void getUserFailTest() throws Exception {
        mockMvc.perform(get("/api/user/a")).andExpect(status().is4xxClientError());

        mockMvc.perform(get("/api/user/11")).andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser("admin")
    public void createUserTest() throws Exception {
        Map<String, String> user = Map.of("username", "user3", "password", "user3");
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(user);
        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        ).andExpect(status().isOk());
    }
}
