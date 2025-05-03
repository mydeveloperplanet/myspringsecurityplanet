package com.mydeveloperplanet.myspringsecurityplanet.controller;

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
