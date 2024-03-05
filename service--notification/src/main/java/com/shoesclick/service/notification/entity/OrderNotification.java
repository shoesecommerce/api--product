package com.shoesclick.service.notification.entity;

import com.shoesclick.service.notification.enums.TypeTemplate;


public class OrderNotification {

    private Long idOrder;

    private Long idCustomer;

    private TypeTemplate typeTemplate;

    public Long getIdOrder() {
        return idOrder;
    }

    public OrderNotification setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
        return this;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public OrderNotification setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
        return this;
    }

    public TypeTemplate getTypeTemplate() {
        return typeTemplate;
    }

    public OrderNotification setTypeTemplate(TypeTemplate typeTemplate) {
        this.typeTemplate = typeTemplate;
        return this;
    }
}
