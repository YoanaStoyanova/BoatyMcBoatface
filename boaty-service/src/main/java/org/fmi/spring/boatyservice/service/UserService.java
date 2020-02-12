package org.fmi.spring.boatyservice.service;

import java.util.List;

import org.fmi.spring.boatyservice.api.bindings.TopUpSpec;
import org.fmi.spring.boatyservice.api.bindings.UserDetailsSpec;
import org.fmi.spring.boatyservice.model.User;
import org.fmi.spring.boatyservice.model.UserPurchasedTickets;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void topUpAccount(Long userId, TopUpSpec topUpSpec);

    Page<User> list(Pageable pageRequest);

    User loadById(Long id);

    User updateUserDetails(Long id, UserDetailsSpec userSpec);

    void delete(Long userId);

    List<UserPurchasedTickets> getPurchasedTickets(Long userId);
}
