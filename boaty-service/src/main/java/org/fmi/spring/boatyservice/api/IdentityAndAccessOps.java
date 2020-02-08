package org.fmi.spring.boatyservice.api;

import java.util.Arrays;

import org.fmi.spring.boatyservice.api.bindings.UserDetails;
import org.fmi.spring.boatyservice.api.bindings.UserRoleSpec;
import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.model.User;
import org.fmi.spring.boatyservice.security.authentication.AuthUtil;
import org.fmi.spring.boatyservice.security.authentication.UserAuthDetailsService;
import org.fmi.spring.boatyservice.security.authentication.UserRole;
import org.fmi.spring.boatyservice.security.token.AuthToken;
import org.fmi.spring.boatyservice.security.token.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdentityAndAccessOps {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserAuthDetailsService userDetailsService;

    @GetMapping("/api/tokens")
    AuthToken getUserToken() {
        return tokenProvider.createToken(AuthUtil.currentUser());
    }

    @GetMapping("/api/me")
    UserDetails getCurrentUser() {
        User user = (User) userDetailsService.loadUserByUsername(AuthUtil.currentUser());
        return new UserDetails(user);
    }

    @PostMapping("/api/users/{id}/roles")
    UserDetails updateUserRoles(@PathVariable(name = "id") long id, @RequestBody UserRoleSpec userRoleSpec) {
        UserRole newRole = Arrays.stream(UserRole.values())
            .filter(role -> role.name().equals(userRoleSpec.role))
            .findFirst()
            .orElseThrow(() -> {
                String errorMessage = String.format("Invalid role name %s", userRoleSpec.role);
                return new ApplicationException(errorMessage, HttpStatus.BAD_REQUEST);
            });
        return new UserDetails((User)userDetailsService.updateUserRoles(id, newRole));
    }
}
