package com.naz1k1.controller;

import com.naz1k1.entity.User;
import com.naz1k1.model.Result;
import com.naz1k1.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody Map<String, String> body) {
        User user = authService.login(body.get("username"), body.get("password"));
        return Result.success(user);
    }


}
