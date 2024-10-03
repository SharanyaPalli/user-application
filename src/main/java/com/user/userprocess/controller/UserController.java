package com.user.userprocess.controller;

import com.user.userprocess.entity.User;
import com.user.userprocess.repository.UserRepository;
import com.user.userprocess.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/getUser")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        return userService.addUserOrUpdate(user);
    }
}