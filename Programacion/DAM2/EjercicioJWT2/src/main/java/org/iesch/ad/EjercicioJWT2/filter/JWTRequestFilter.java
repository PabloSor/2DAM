package org.iesch.ad.EjercicioJWT2.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesch.ad.EjercicioJWT2.utilJWT.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        System.out.println(authorizationHeader); //traceo

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUserName(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            if (jwtUtil.validateToken(jwt, jwtUtil.extractUserName(jwt))){
                //noseq nose cuantas
            }
        }
    }
}
