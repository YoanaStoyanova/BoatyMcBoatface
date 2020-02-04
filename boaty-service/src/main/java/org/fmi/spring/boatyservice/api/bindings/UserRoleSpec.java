package org.fmi.spring.boatyservice.api.bindings;

import javax.validation.constraints.NotEmpty;

/**
 * Represents the expected request body from an UpdateRolesSpec
 */
public class UserRoleSpec {

    @NotEmpty
    public String role;
}
