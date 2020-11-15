package com.example.demo.security.jwt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        // this method is called on login
        // it checks the request input and validates the payload
        try {
            UsernamePasswordAuthRequest authRequest = new ObjectMapper().readValue(request.getInputStream(),
                    UsernamePasswordAuthRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                    authRequest.getPassword());

            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain Chain,
            Authentication auth) {

        String key = "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";
        // this method will be called on successful authentication in the above method
        String token = Jwts.builder().setSubject(auth.getName()).claim("authorities", auth.getAuthorities())
                .setIssuedAt(new Date()).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                .signWith(Keys.hmacShaKeyFor(key.getBytes())).compact();

        response.addHeader("Authorization", "Bearer " + token);
    }

}
