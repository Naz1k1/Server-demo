package com.naz1k1.controller;

import com.naz1k1.entity.User;
import com.naz1k1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public User Test() {
        return userService.test();
    }
}
