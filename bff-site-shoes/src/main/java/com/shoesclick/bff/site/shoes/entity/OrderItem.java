package com.shoesclick.bff.site.shoes.entity;

import java.math.BigDecimal;

public class OrderItem {
     private Long id;

    private Long idProduct;

    private String nameProduct;

    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public OrderItem setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public OrderItem setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
        return this;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public OrderItem setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
