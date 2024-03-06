package com.shoesclick.api.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shoesclick.api.order.config.properties.MqProperties;
import com.shoesclick.api.order.entity.Notification;
import com.shoesclick.api.order.entity.Order;
import com.shoesclick.api.order.enums.TypeTemplate;
import com.shoesclick.api.order.exception.BusinessException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class NotificationService {


    private final MqProperties mqProperties;
    private final AmqpTemplate rabbitTemplate;

    public NotificationService(MqProperties mqProperties, AmqpTemplate rabbitTemplate) {
        this.mqProperties = mqProperties;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(Order order) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            String json = mapper.writeValueAsString(getNotification(order));
            rabbitTemplate.convertAndSend(mqProperties.getExchange(), mqProperties.getNotification().getRoutingKey(), json);
        } catch (JsonProcessingException e) {
            throw new BusinessException("ERRO NO PROCESSAMENTO DA FILA MQ");
        }
    }

    private Notification getNotification(Order order) {
        return new Notification()
                .setIdOrder(order.getId())
                .setIdCustomer(order.getIdCustomer())
                .setTypeTemplate(TypeTemplate.CREATE_ORDER);
    }
}
