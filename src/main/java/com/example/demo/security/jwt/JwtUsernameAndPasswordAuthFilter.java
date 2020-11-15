package com.example.demo.security.jwt;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtUsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attempAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

    }

}
