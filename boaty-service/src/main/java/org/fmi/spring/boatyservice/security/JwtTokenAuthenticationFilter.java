package org.fmi.spring.boatyservice.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    static final String AUTH_HEADER_NAME = "Authorization";
    static final String AUTH_HEADER_PREFIX = "Bearer ";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = resolveToken(request);
        try {
            if (token != null) {
                Authentication auth = authenticationManager.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (ApplicationException ex) {
            SecurityContextHolder.clearContext();
            response.sendError(ex.getStatusCode().value(), ex.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTH_HEADER_NAME);
        if (bearerToken != null && bearerToken.startsWith(AUTH_HEADER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
