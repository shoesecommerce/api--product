package com.shoesclick.service.notification.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoesclick.service.notification.entity.OrderNotification;
import com.shoesclick.service.notification.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProcessNotification {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    private final NotificationService notificationService;

    private final ObjectMapper objectMapper;

    public ProcessNotification(NotificationService notificationService, ObjectMapper objectMapper) {
        this.notificationService = notificationService;
        this.objectMapper = objectMapper;
    }


    @RabbitListener(queues = "order_notification")
    public void handleMessage(String message) throws JsonProcessingException {
        OrderNotification order = objectMapper.readValue(message, OrderNotification.class);
        notificationService.process(order);

    }
}
