package com.shoesclick.service.payment.entity;

import jakarta.persistence.Entity;

@Entity
public class TicketPayment extends Payment {

    private String codeBar;

    public String getCodeBar() {
        return codeBar;
    }

    public TicketPayment setCodeBar(String codeBar) {
        this.codeBar = codeBar;
        return this;
    }
}
