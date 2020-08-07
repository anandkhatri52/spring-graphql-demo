package com.example.controller;

import com.example.entity.UserEntity;
import com.example.model.JwtUser;
import com.example.security.JwtGenerator;
import com.example.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final JwtGenerator jwtGenerator;

    @PostMapping
    public String generate(@RequestBody UserEntity userEntity) {
        JwtUser jwtUser = loginService.authenticateUser(userEntity.getUserName(), userEntity.getPassword());
        return jwtGenerator.generate(jwtUser);
    }
}
