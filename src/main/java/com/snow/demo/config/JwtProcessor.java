package com.snow.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtProcessor {

    private static final Logger log = LoggerFactory.getLogger(JwtProcessor.class);

    private final Key key;

    public JwtProcessor(@Value("${encryptKey}") String encryptKey) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(encryptKey));
    }

    public String encodeJwt(String username) {
        return Jwts.builder().claim("username", username).signWith(key).compact();
    }

    public String decodeJwt(String enc) {
        Claims c = Jwts.parserBuilder().setSigningKey(key)
                .base64UrlDecodeWith(Decoders.BASE64)
                .build()
                .parseClaimsJwt(enc)
                .getBody();
        return (String) c.get("username");
    }
}
