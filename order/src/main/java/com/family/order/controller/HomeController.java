package com.family.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("home")
public class HomeController {
    @GetMapping("ping")
    public String ping() {
        return "pong. current time = " + new Date();
    }
}