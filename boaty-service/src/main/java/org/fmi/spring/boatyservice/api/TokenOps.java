package org.fmi.spring.boatyservice.api;

import java.util.Date;

import org.fmi.spring.boatyservice.api.bindings.UserTokenDetails;
import org.fmi.spring.boatyservice.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tokens")
public class TokenOps {

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @GetMapping
    UserTokenDetails getUserToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Date tokenValidity = new Date(new Date().getTime() + validityInMilliseconds);
        String token = tokenProvider.createToken(authentication.getName(), tokenValidity);
        return new UserTokenDetails(token, tokenValidity.getTime());
    }

}
