package com.mainservice.main.util;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    
    @Autowired
    public JwtRequestFilter(JWTUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
    	String uri = request.getRequestURI();
    	
    	if(uri.equals("/user/auth/login")) {
    		chain.doFilter(request, response);
    		return;
    	}
        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        // Check if the authorization header is present and starts with "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            if(jwtUtil == null) System.out.println("check2");
            username = jwtUtil.extractUsername(jwt); // Ensure jwtUtil is not null
            System.out.println("check" + username);
        }

        // Validate the token and set authentication if valid
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	System.out.println("check");
            if (jwt != null && jwtUtil.validateToken(jwt, username)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        chain.doFilter(request, response); // Proceed with the filter chain
    }
}