package com.project.it.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping
    public String viewHomePage() {
        return "redirect:/employees";
    }
}
