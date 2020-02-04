package org.fmi.spring.boatyservice.security.token;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider implements TokenProvider {

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds;

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    @Override
    public AuthToken createToken(String userName) {
        Claims claims = Jwts.claims().setSubject(userName);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        String token = Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();

        return new AuthToken(token, validity.getTime());
    }

}
