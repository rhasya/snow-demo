package com.snow.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SnowDemoApplicationTests {

    @Autowired @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    @WithMockUser(username="user", roles="USER")
    void userTest() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/user/profile"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        Assertions.assertEquals("application/json", response.getContentType());
        System.out.println(response.getContentAsString());
    }
}
