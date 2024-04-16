package com.example.backend_commerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        Map<String, String> map = new HashMap<>();
        map.put("Hello, World!", "Welcome to the home page!");
        return map;
    }
}