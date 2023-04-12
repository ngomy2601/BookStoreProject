package com.example.bookstoreproject.api.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/google")
    public String login() {
        return "index.html";
    }
}
