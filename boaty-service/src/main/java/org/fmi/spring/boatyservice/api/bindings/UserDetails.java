package org.fmi.spring.boatyservice.api.bindings;

import org.fmi.spring.boatyservice.model.User;

public class UserDetails {
    public Long id;
    public String username;
    public String firstName;
    public String lastName;
    public Double balance;
    public String role;

    public UserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.balance = user.getAccountBalance();
        this.role = user.getRole().name();
    }
}
