package org.fmi.spring.boatyservice.api;

import java.util.List;
import java.util.stream.Collectors;

import org.fmi.spring.boatyservice.api.bindings.PurchasedTicketDetails;
import org.fmi.spring.boatyservice.model.User;
import org.fmi.spring.boatyservice.security.authentication.AuthUtil;
import org.fmi.spring.boatyservice.service.PurchaseService;
import org.fmi.spring.boatyservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseOps {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/ticket/{user_id}")
    List<PurchasedTicketDetails> getPurchasedTicket(@PathVariable(name = "user_id") Long id) {
        return userService.getPurchasedTickets(id).stream()
            .map(PurchasedTicketDetails::new)
            .collect(Collectors.toList());
    }

    @PostMapping("/ticket/{id}")
    void purchaseTicket(@PathVariable(name = "id") Long ticketId) {
        User user = (User)userDetailsService.loadUserByUsername(AuthUtil.currentUser());
        purchaseService.purchase(user.getId(), ticketId);
    }
}
