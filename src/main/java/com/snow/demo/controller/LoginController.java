package com.snow.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtEncoder encoder;

    @GetMapping("/test")
    public String test() {
        return "Hello";
    }

    @PostMapping("/login")
    public String loginProc(@RequestBody Map<String, String> model) {
        String username = model.get("username");
        String password = model.get("password");

        if (!StringUtils.hasLength(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (!StringUtils.hasLength(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        UserDetails targetUser;
        try {
            targetUser = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        if (!targetUser.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        Instant now = Instant.now();

        String scope = targetUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(36000))
                .subject(targetUser.getUsername())
                .claim("scope", scope)
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
