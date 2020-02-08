package org.fmi.spring.boatyservice.api;

import org.fmi.spring.boatyservice.api.bindings.UserDetails;
import org.fmi.spring.boatyservice.model.User;
import org.fmi.spring.boatyservice.security.authentication.AuthUtil;
import org.fmi.spring.boatyservice.security.token.AuthToken;
import org.fmi.spring.boatyservice.security.token.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdentityAndAccessOps {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/api/tokens")
    AuthToken getUserToken() {
        return tokenProvider.createToken(AuthUtil.currentUser());
    }

    @GetMapping("/api/me")
    UserDetails getCurrentUser() {
        User user = (User) userDetailsService.loadUserByUsername(AuthUtil.currentUser());
        return new UserDetails(user);
    }
}
