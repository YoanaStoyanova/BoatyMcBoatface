package org.fmi.spring.boatyservice.api;

import org.fmi.spring.boatyservice.api.bindings.CreateUserSpec;
import org.fmi.spring.boatyservice.api.bindings.UserDetails;
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

    @PostMapping
    UserDetails createUser(@RequestBody CreateUserSpec userSpec) {
        return new UserDetails(userService.createUser(userSpec));
    }

    @RequestMapping
    Page<UserDetails> viewUsers(
        @RequestParam(name="page", defaultValue = "0") int page,
        @RequestParam(name="size", defaultValue = "20") int size,
        @RequestParam(name = "direction", defaultValue = "ASC") String sortDirection) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), DEFAULT_SORT_PROPERTY);
        return userService.listUsers(PageRequest.of(page, size, sort)).map(UserDetails::new);
    }

    @GetMapping("/{id}")
    UserDetails getUser(@PathVariable(name="id") long id) {
        return new UserDetails(userService.getUser(id));
    }

    @PatchMapping("/{id}")
    UserDetails updateUser(@PathVariable(name = "id") long id, @RequestBody CreateUserSpec userSpec) {
        return new UserDetails(userService.updateUser(id, userSpec));
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable(name = "{id}") long id) {
        userService.deleteUser(id);
    }

}
