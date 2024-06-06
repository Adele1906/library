package com.example.gateway_service;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class MyFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
/*
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String iddus = obj.toString().substring(7, 43);
            Cookie cookie = new Cookie("iddus", iddus);
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            response.addCookie(cookie);
            response.addHeader("Access-Control-Expose-Headers", "iddus");
            response.addHeader("iddus", iddus);

            System.out.println("add email success.");
        }
*/
        filterChain.doFilter(request, response);
    }
    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return true;
    }
}
