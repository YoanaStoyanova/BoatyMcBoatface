package org.fmi.spring.boatyservice.service;

import org.fmi.spring.boatyservice.api.bindings.RegisterUserSpec;
import org.fmi.spring.boatyservice.model.User;

public interface UserRegistrationService {

    User register(RegisterUserSpec registerUserSpec);

}
