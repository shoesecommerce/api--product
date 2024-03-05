package com.shoesclick.service.payment.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoesclick.service.payment.domain.PaymentDomain;
import com.shoesclick.service.payment.service.PaymentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProcessPayment {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    private final PaymentService paymentService;

    private final ObjectMapper objectMapper;

    public ProcessPayment(PaymentService paymentService, ObjectMapper objectMapper) {
        this.paymentService = paymentService;
        this.objectMapper = objectMapper;
    }


    @RabbitListener(queues = "mq_payment")
    public void handleMessage(String message) throws JsonProcessingException {
        PaymentDomain order = objectMapper.readValue(message, PaymentDomain.class);
        paymentService.process(order);

    }
}
