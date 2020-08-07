package com.example.service;

import com.example.entity.UserEntity;
import com.example.exception.BadRequestException;
import com.example.mapper.JwtUserMapper;
import com.example.model.JwtUser;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final JwtUserMapper jwtUserMapper;

    public JwtUser authenticateUser(String username, String password){
        UserEntity userEntity = userRepository.findByUserName(username).orElseThrow(() -> new BadRequestException("Wrong Credentials, Please try again!!"));
        if(!password.equalsIgnoreCase(userEntity.getPassword())){
            throw new BadRequestException("Wrong Credentials, Please try again!!");
        }
        return jwtUserMapper.map(username, password);
    }
}
