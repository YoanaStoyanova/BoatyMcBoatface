package org.fmi.spring.boatyservice.api;

import org.fmi.spring.boatyservice.api.bindings.PagedResponse;
import org.fmi.spring.boatyservice.api.bindings.RegisterUserSpec;
import org.fmi.spring.boatyservice.api.bindings.TopUpSpec;
import org.fmi.spring.boatyservice.api.bindings.UserDetails;
import org.fmi.spring.boatyservice.api.bindings.UserDetailsSpec;
import org.fmi.spring.boatyservice.service.UserRegistrationService;
import org.fmi.spring.boatyservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserOps {

    static final String DEFAULT_SORT_PROPERTY = "id";

    @Autowired
    private UserService userService;

    @Autowired
    private UserRegistrationService userRegistrationService;

    @PostMapping
    UserDetails createUser(@RequestBody RegisterUserSpec userSpec) {
        return new UserDetails(userRegistrationService.register(userSpec));
    }

    @RequestMapping
    PagedResponse<UserDetails> viewUsers(
        @RequestParam(name="page", defaultValue = "0") int page,
        @RequestParam(name="size", defaultValue = "20") int size,
        @RequestParam(name = "direction", defaultValue = "ASC") String sortDirection) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), DEFAULT_SORT_PROPERTY);
        Page<UserDetails> userDetailsPage = userService.list(PageRequest.of(page, size, sort))
            .map(UserDetails::new);
        return new PagedResponse<>(userDetailsPage);
    }

    @GetMapping("/{id}")
    UserDetails getUser(@PathVariable(name="id") long id) {
        return new UserDetails(userService.loadById(id));
    }

    @PatchMapping("/{id}")
    UserDetails updateUserDetails(@PathVariable(name = "id") long id, @RequestBody UserDetailsSpec userSpec) {
        return new UserDetails(userService.updateUserDetails(id, userSpec));
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable(name = "{id}") long id) {
        userService.delete(id);
    }

    @PostMapping("/{id}/top-up")
    void topUp(@PathVariable(name = "id") Long id, @RequestBody TopUpSpec topUpSpec) {
        userService.topUpAccount(id, topUpSpec);
    }
}
