package com.example.security;

import com.example.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtValidator {

    private String secret = "Graphql";

    public JwtUser validate(String token) {

        JwtUser jwtUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = new JwtUser();

            jwtUser.setUserName(body.getSubject());
            jwtUser.setPassword((String) body.get("userId"));
            jwtUser.setRole((String) body.get("role"));
        } catch (Exception e) {
            log.error("Error while validating the user: {}", e.getMessage());
        }

        return jwtUser;
    }
}
