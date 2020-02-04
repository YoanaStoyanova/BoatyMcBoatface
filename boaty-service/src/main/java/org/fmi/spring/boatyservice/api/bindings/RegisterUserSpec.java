package org.fmi.spring.boatyservice.api.bindings;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class RegisterUserSpec {

    @Email
    @NotEmpty
    public String username;
    @Min(8)
    @NotEmpty
    public String password;
    public String firstName;
    public String lastName;
    public String role;
}
