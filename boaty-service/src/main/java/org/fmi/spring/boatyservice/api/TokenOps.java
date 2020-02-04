package org.fmi.spring.boatyservice.api;

import org.fmi.spring.boatyservice.security.authentication.AuthUtil;
import org.fmi.spring.boatyservice.security.token.AuthToken;
import org.fmi.spring.boatyservice.security.token.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tokens")
public class TokenOps {

    @Autowired
    private TokenProvider tokenProvider;

    @GetMapping
    AuthToken getUserToken() {
        return tokenProvider.createToken(AuthUtil.currentUser());
    }

}
