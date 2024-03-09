package com.shoesclick.bff.site.shoes.entity;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

public class Order {
    private Long id;

    private Integer status;

    private Long idCustomer;

    private LocalDateTime createdAt;

    private String paymentType;

    @Valid
    private Map<String, Object> paymentParams = new HashMap<>();

    private List<OrderItem> orderItems;

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Order setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public Order setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Order setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public Order setPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public Map<String, Object> getPaymentParams() {
        return paymentParams;
    }

    public Order setPaymentParams(Map<String, Object> paymentParams) {
        this.paymentParams = paymentParams;
        return this;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Order setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        return this;
    }
}
