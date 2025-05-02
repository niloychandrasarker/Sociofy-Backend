package com.niloy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public String homeControllerHandler() {
        return "This is home controller 1";
    }
    @GetMapping("/home")
    public String homeControllerHandler2() {
        return "This is home controller 2";
    }

    @GetMapping("/codewithniloy")
    public String homeControllerHandler3() {
        return "Helo from codewithniloy";
    }
}

