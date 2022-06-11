package com.snow.demo.controller;

import com.snow.demo.model.User;
import com.snow.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> users() {
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/user")
    public void createUser(User u) {

    }

    @DeleteMapping("/user/:id")
    public void deleteUser(@PathVariable Long id) {
    }
}
