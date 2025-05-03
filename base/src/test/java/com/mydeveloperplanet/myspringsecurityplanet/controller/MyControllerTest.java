package com.mydeveloperplanet.myspringsecurityplanet.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHelloGet() throws Exception {
        mockMvc.perform(get("/api/hello-get"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }

    @Test
    void testHelloPost() throws Exception {
        mockMvc.perform(post("/api/hello-post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\":\"this is a message\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("this is a message"));
    }
}
