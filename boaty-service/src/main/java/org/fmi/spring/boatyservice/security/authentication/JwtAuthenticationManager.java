package org.fmi.spring.boatyservice.security.authentication;

import java.util.Base64;

import javax.annotation.PostConstruct;

import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtAuthenticationManager implements AuthenticationManager {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    @Override
    public Authentication getAuthentication(String token) {
        Jws<Claims> claims = parseToken(token);
        UserDetails principal = userDetailsService.loadUserByUsername(claims.getBody().getSubject());
        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }

    private Jws<Claims> parseToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new ApplicationException("Expired JWT token", HttpStatus.UNAUTHORIZED);
        } catch (JwtException | IllegalArgumentException e) {
            throw new ApplicationException("Invalid JWT token", HttpStatus.BAD_REQUEST);
        }
    }
}

