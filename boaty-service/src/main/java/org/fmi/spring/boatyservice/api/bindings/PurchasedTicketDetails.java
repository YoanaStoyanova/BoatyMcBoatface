package org.fmi.spring.boatyservice.api.bindings;

import java.util.Date;

import org.fmi.spring.boatyservice.model.UserPurchasedTickets;

public class PurchasedTicketDetails {

    public Long id;
    public String name;
    public String validUntil;

    public PurchasedTicketDetails() {

    }

    public PurchasedTicketDetails(UserPurchasedTickets userPurchasedTickets) {
        this.id = userPurchasedTickets.getId();
        this.name = userPurchasedTickets.getTicket().getName();
        long validity = userPurchasedTickets.getTicket().getMinutesValidFor();
        long purchaseTime = userPurchasedTickets.getPurchasedOn().getTime();
        this.validUntil = new Date(purchaseTime + (validity * 60L * 1000L)).toString();
    }
}
