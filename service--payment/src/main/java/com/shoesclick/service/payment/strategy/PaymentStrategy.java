package com.shoesclick.service.payment.strategy;

import com.shoesclick.service.payment.entity.CardPayment;
import com.shoesclick.service.payment.entity.Order;
import com.shoesclick.service.payment.entity.PixPayment;
import com.shoesclick.service.payment.entity.TicketPayment;
import com.shoesclick.service.payment.exception.ElementNotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

public enum PaymentStrategy {
    PIX_PAYMENT {
        @Override
        public PixPayment convert(Map paymentParams, Order order) {
            var payment = new PixPayment();
            payment.setIdOrder(order.getId());
            payment.setKeyCode(String.valueOf(paymentParams.get("keyCode")));
            return payment;
        }
    },
    CARD_PAYMENT {
        @Override
        public CardPayment convert(Map paymentParams, Order order) {
            var payment = new CardPayment();
            payment.setIdOrder(order.getId());
            payment.setNumber(String.valueOf(paymentParams.get("number")));
            payment.setName(String.valueOf(paymentParams.get("name")));
            payment.setCode(String.valueOf(paymentParams.get("code")));
            payment.setExpirationDate(LocalDateTime.parse(String.valueOf(paymentParams.get("expirationDate"))));
            return payment;
        }
    },

    TICKET_PAYMENT {
        @Override
        public TicketPayment convert(Map paymentParams, Order order) {
            var payment = new TicketPayment();
            payment.setIdOrder(order.getId());
            payment.setCodeBar(String.valueOf(paymentParams.get("codeBar")));
            return payment;


        }
    };

    public abstract <P> P convert(Map<String,Object> campos, Order order);

    public static PaymentStrategy findByName(String name){
        return Arrays.stream(PaymentStrategy.values()).filter( item -> item.name().equals(name)).findFirst().orElseThrow( ()-> new ElementNotFoundException("ELEMENTO NÃO ENCONTRADO"));
    }


}
