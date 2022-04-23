package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("hello")
public class HelloController {
	
	@GetMapping
    public String hello() {
        return "hello";
    }
}