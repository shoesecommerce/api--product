package com.shoesclick.api.order.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class CardPayment extends Payment {

    private String number;

    private String name;

    private String code;

    private LocalDateTime expirationDate;

    public String getNumber() {
        return number;
    }

    public CardPayment setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getName() {
        return name;
    }

    public CardPayment setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public CardPayment setCode(String code) {
        this.code = code;
        return this;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public CardPayment setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }
}
