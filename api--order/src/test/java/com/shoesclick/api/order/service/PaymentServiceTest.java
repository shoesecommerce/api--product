package com.shoesclick.api.order.service;

import com.shoesclick.api.order.domain.PaymentDomain;
import com.shoesclick.api.order.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class PaymentServiceTest {

    @Mock
    private AmqpTemplate rabbitTemplate;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(paymentService, "exchange", "valor");
        ReflectionTestUtils.setField(paymentService, "routingKey", "valor");
    }

    @Test
    void shouldSendNotificationSuccess() throws IllegalAccessException {
        paymentService.sendPayment(new Order()
                .setId(1L)
                .setCreatedAt(LocalDateTime.now())
                .setStatus(1)
                .setIdCustomer(1L),
                new PaymentDomain().setPaymentType("PIX_PAYMENT").setPaymentParams(Map.of()));
        verify(rabbitTemplate, times(1)).convertAndSend(anyString(), anyString(), anyString());
    }
}
