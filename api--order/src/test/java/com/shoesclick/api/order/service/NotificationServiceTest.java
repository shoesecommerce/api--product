package com.shoesclick.api.order.service;

import com.shoesclick.api.order.entity.Order;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class NotificationServiceTest {

    @Mock
    private AmqpTemplate rabbitTemplate;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(notificationService, "exchange", "valor");
        ReflectionTestUtils.setField(notificationService, "routingKey", "valor");
    }

    @Test
    void shouldSendNotificationSuccess() throws IllegalAccessException {
        notificationService.sendNotification(new Order().setId(1L).setCreatedAt(LocalDateTime.now()).setStatus(1).setIdCustomer(1L));
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), anyString());
    }
}
