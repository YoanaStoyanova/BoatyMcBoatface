package org.fmi.spring.boatyservice.model;

import java.time.YearMonth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class CardPaymentMethod {

    private Long id;
    private String cardHolder;
    private User user;
    private Long cardNumber;
    private Integer validityYear;
    private Integer validityMonth;

    public CardPaymentMethod() {

    }

    public CardPaymentMethod(String cardHolder, Long cardNumber, YearMonth validity) {
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.validityMonth = validity.getMonth().getValue();
        this.validityYear = validity.getYear();
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(nullable = false)
    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Column(nullable = false)
    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Transient
    public YearMonth getValidity() {
        return YearMonth.of(this.validityYear, this.validityMonth);
    }

    public void setValidity(YearMonth validity) {
        this.validityMonth = validity.getMonth().getValue();
        this.validityYear = validity.getYear();
    }

    @Column(nullable = false)
    public Integer getValidityYear() {
        return validityYear;
    }

    public void setValidityYear(Integer validityYear) {
        this.validityYear = validityYear;
    }

    @Column(nullable = false)
    public Integer getValidityMonth() {
        return validityMonth;
    }

    public void setValidityMonth(Integer validityMonth) {
        this.validityMonth = validityMonth;
    }
}
