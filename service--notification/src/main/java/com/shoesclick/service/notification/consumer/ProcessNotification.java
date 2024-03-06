package com.shoesclick.service.notification.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoesclick.service.notification.config.properties.MqProperties;
import com.shoesclick.service.notification.entity.OrderNotification;
import com.shoesclick.service.notification.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProcessNotification {


    private final MqProperties mqProperties;

    private final NotificationService notificationService;

    private final ObjectMapper objectMapper;

    public ProcessNotification(MqProperties mqProperties, NotificationService notificationService, ObjectMapper objectMapper) {
        this.mqProperties = mqProperties;
        this.notificationService = notificationService;
        this.objectMapper = objectMapper;
    }


    @RabbitListener(queues = "mq_notification")
    public void handleMessage(String message) throws JsonProcessingException {
        OrderNotification order = objectMapper.readValue(message, OrderNotification.class);
        notificationService.process(order);

    }
}
