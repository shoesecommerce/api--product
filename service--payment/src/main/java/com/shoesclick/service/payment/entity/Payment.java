package com.shoesclick.service.payment.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idOrder;

    private Long idCustomer;

    public Long getId() {
        return id;
    }

    public Payment setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public Payment setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
        return this;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public Payment setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
        return this;
    }
}
