package com.bridgelabz.bookstoreapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTToken {
    private final String SECRET = "Sourabh";
    // Token expiration time in milliseconds (e.g., 1 hour)
    private final long EXPIRATION_TIME = 100000; // 1 hour in milliseconds

    String token;
    Date expiresAt = new Date(System.currentTimeMillis() + EXPIRATION_TIME);


    public String createToken(long id) {
        String token;
        token = JWT.create()
                .withClaim("id", id).withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    public Long decodeToken(String token) {

        long id = 0;
        if (token != null) {
            id = JWT.require(Algorithm.HMAC256(SECRET))
                    .build().verify(token)
                    .getClaim("id").asLong();
        }
        return id;
    }
}
