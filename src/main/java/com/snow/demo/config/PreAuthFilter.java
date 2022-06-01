package com.snow.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    public PreAuthFilter() {
        setAuthenticationManager(new CustomAuthenticationManager());
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtProcessor jwtProcessor;

    private static final Logger log = LoggerFactory.getLogger(PreAuthFilter.class);
    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        log.info("pre login process");
        String bearerToken = request.getHeader("Authorization");

        if (!StringUtils.hasLength(bearerToken) || !bearerToken.startsWith("Bearer ")) {
            return null;
        }
        String jwt = bearerToken.substring("Bearer ".length());
        String username = jwtProcessor.decodeJwt(jwt);
        UserDetails user = userDetailsService.loadUserByUsername(username);
        return new PreAuthenticatedAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return null;
    }
}
