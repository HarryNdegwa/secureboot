package com.example.demo.security.jwt;

import java.io.IOException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtUsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attempAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            UsernamePasswordAuthRequest authRequest = new ObjectMapper().readValue(request.getInputStream(),
                    UsernamePasswordAuthRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return super.attemptAuthentication(request, response);
    }

}
