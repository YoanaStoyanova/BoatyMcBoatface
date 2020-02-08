package org.fmi.spring.boatyservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.fmi.spring.boatyservice.security.authentication.UserAuthDetails;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "users")
@OnDelete(action = OnDeleteAction.CASCADE)
public class User extends UserAuthDetails {

    private Long id;
    private String firstName;
    private String lastName;
    private Double accountBalance = 0.0;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false)
    public Double getAccountBalance() {
        return this.accountBalance;
    }

    public void setAccountBalance(Double newBalance) {
        this.accountBalance = newBalance;
    }
}
