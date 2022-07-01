package com.snow.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public String hello(Authentication a) {
        return "hello " + a.getName();
    }

    @GetMapping("/user/profile")
    public Map<String, String> userProfile(Authentication a) {
        HashMap<String, String> res = new HashMap<>();
        res.put("username", a.getName());
        res.put("authority", a.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" ")));
        return res;
    }
}
