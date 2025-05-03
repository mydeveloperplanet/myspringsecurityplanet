package com.mydeveloperplanet.myspringsecurityplanet.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class MyController {

    @GetMapping("hello-get")
    String helloGet() {
        return "Hello World!";
    }

    @GetMapping("hello-gunter-get")
    @PostAuthorize("returnObject.toLowerCase().contains(@keycloakSecurity.preferredUsername(authentication))")
    String helloGunterGet(Authentication authentication) {
        return "Hello Gunter!";
    }

    @PostMapping("hello-post")
    String helloPost(@RequestBody HelloBody body) {
        return body.content;
    }

    static class HelloBody {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
