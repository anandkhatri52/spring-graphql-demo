package com.example.mapper;

import com.example.model.JwtUser;
import org.springframework.stereotype.Component;

@Component
public class JwtUserMapper {

    public JwtUser map(String username, String password) {
        return JwtUser.builder()
                .userName(username)
                .password(password)
                .role(username)
                .build();
    }
}
