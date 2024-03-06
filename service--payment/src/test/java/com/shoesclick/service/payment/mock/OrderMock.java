package com.shoesclick.service.payment.mock;

import com.shoesclick.service.payment.domain.PaymentDomain;
import com.shoesclick.service.payment.entity.Order;

import java.util.Map;

public class OrderMock {
    private OrderMock() { }

    public static PaymentDomain getPaymentDomain_PIX_PAYMENT() {
        return new PaymentDomain().setOrder(new Order().setId(1L)).setPaymentParams(Map.of("keyCode", "435345435345345345345345435345")).setPaymentType("PIX_PAYMENT");
    }

    public static PaymentDomain getPaymentDomain_CARD_PAYMENT() {
        return new PaymentDomain().setOrder(new Order().setId(1L)).setPaymentParams(Map.of("number", "234234234234", "name", "NOME PESSOA", "code", "123", "expirationDate", "2024-01-13T17:09:42.411")).setPaymentType("CARD_PAYMENT");
    }

    public static PaymentDomain getPaymentDomain_BANKSLIP_PAYMENT() {
        return new PaymentDomain().setOrder(new Order().setId(1L)).setPaymentParams(Map.of("codeBar", "3324234234234234324324324234")).setPaymentType("BANKSLIP_PAYMENT");
    }
}
