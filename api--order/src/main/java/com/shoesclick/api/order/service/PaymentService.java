package com.shoesclick.api.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shoesclick.api.order.domain.PaymentDomain;
import com.shoesclick.api.order.entity.Order;
import com.shoesclick.api.order.entity.Payment;
import com.shoesclick.api.order.exception.BusinessException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Value("${rabbitmq.notification.exchange.name}")
    private static String exchange;

    @Value("${rabbitmq.payment.routing.key}")
    private static String routingKey;

    private final AmqpTemplate rabbitTemplate;

    public PaymentService(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendPayment(Order order, PaymentDomain paymentDomain) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            String json = mapper.writeValueAsString(getPayment(order,paymentDomain));
            rabbitTemplate.convertAndSend(exchange, routingKey, json);
        } catch (JsonProcessingException e) {
            throw new BusinessException("ERRO NO PROCESSAMENTO DA FILA MQ");
        }
    }

    private Payment getPayment(Order order, PaymentDomain paymentDomain) {
        return new Payment()
                .setOrder(order)
                .setPaymentType(paymentDomain.getPaymentType())
                .setPaymentParams(paymentDomain.getPaymentParams());
    }
}
