package com.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class HelloController {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/api/hello")
    public String hello() {
        return "Salut de la backend!";
    }
}
