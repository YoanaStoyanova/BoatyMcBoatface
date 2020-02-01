package org.fmi.spring.boatyservice.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    static final String AUTH_HEADER_NAME = "Authorization";
    static final String AUTH_HEADER_PREFIX = "Bearer ";

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = resolveToken(request);
        try {
            if (token != null && validateToken(token)) {
                Authentication auth = getAuthentication(token);
                userDetailsService.loadUserByUsername(auth.getName());
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

    private Authentication getAuthentication(String token) {
        Jws<Claims> claims = jwtTokenProvider.parseToken(token);
        UserDetails principal = userDetailsService.loadUserByUsername(claims.getBody().getSubject());
        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }

    private boolean validateToken(String token) {
        try {
            jwtTokenProvider.parseToken(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new ApplicationException("Expired JWT token", HttpStatus.UNAUTHORIZED);
        } catch (JwtException | IllegalArgumentException e) {
            throw new ApplicationException("Invalid JWT token", HttpStatus.BAD_REQUEST);
        }
    }
}
