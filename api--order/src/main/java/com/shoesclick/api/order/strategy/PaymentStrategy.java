package com.shoesclick.api.order.strategy;

import com.shoesclick.api.order.entity.CardPayment;
import com.shoesclick.api.order.entity.Order;
import com.shoesclick.api.order.entity.PixPayment;
import com.shoesclick.api.order.entity.TicketPayment;
import com.shoesclick.api.order.exception.ElementNotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

public enum PaymentStrategy {
    PIX_PAYMENT {
        @Override
        public PixPayment convert(Map paymentParams, Order order) {
            var payment = new PixPayment();
            payment.setOrder(order);
            payment.setKeyCode(String.valueOf(paymentParams.get("keyCode")));
            return payment;
        }
    },
    CARD_PAYMENT {
        @Override
        public CardPayment convert(Map paymentParams, Order order) {
            var payment = new CardPayment();
            payment.setOrder(order);
            payment.setNumber(String.valueOf(paymentParams.get("number")));
            payment.setName(String.valueOf(paymentParams.get("name")));
            payment.setExpirationDate(LocalDateTime.parse(String.valueOf(paymentParams.get("expirationDate"))));
            return payment;
        }
    },

    TICKET_PAYMENT {
        @Override
        public TicketPayment convert(Map paymentParams, Order order) {
            var payment = new TicketPayment();
            payment.setOrder(order);
            payment.setCodeBar(String.valueOf(paymentParams.get("codeBar")));
            return payment;


        }
    };

    public abstract <P> P convert(Map<String,Object> campos, Order order);

    public static PaymentStrategy findByName(String name){
        return Arrays.stream(PaymentStrategy.values()).filter( item -> item.name().equals(name)).findFirst().orElseThrow( ()-> new ElementNotFoundException("ELEMENTO NÃO ENCONTRADO"));
    }


}
