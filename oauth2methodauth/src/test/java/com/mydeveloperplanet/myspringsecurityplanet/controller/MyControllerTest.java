package com.mydeveloperplanet.myspringsecurityplanet.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oidcLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = {"hello:read"})
    void testHelloGet() throws Exception {
        mockMvc.perform(get("/api/hello-get"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }

    @Test
    void testHelloGunterGet() throws Exception {
        mockMvc.perform(get("/api/hello-gunter-get")
                        .with(oidcLogin()
                                .idToken(token -> token.claim("preferred_username", "gunter"))
                                .authorities(new SimpleGrantedAuthority("ROLE_hello:read"))))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Gunter!"));
    }

    @Test
    void testHelloGunterForbidden() throws Exception {
        mockMvc.perform(get("/api/hello-gunter-get")
                        .with(oidcLogin()
                                .idToken(token -> token.claim("preferred_username", "john"))
                                .authorities(new SimpleGrantedAuthority("ROLE_hello:read"))))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"hello:write"})
    void testHelloPost() throws Exception {
        mockMvc.perform(post("/api/hello-post")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"content\":\"this is a message\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("this is a message"));
    }
}
