package com.shoesclick.api.order.service;

import com.shoesclick.api.order.domain.PaymentDomain;
import com.shoesclick.api.order.entity.CardPayment;
import com.shoesclick.api.order.entity.Order;
import com.shoesclick.api.order.entity.PixPayment;
import com.shoesclick.api.order.entity.TicketPayment;
import com.shoesclick.api.order.repository.CardPaymentRepository;
import com.shoesclick.api.order.repository.PixPaymentRepository;
import com.shoesclick.api.order.repository.TicketPaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class PaymentServiceTest {

    @Mock
    private CardPaymentRepository cardPaymentRepository;

    @Mock
    private PixPaymentRepository pixPaymentRepository;

    @Mock
    private TicketPaymentRepository ticketPaymentRepository;

    @InjectMocks
    private PaymentService paymentService;


    @Test
    void shouldSavePixPaymentSuccess() {
        var paymentDomain = new PaymentDomain().setPaymentType("PIX_PAYMENT").setPaymentParams(Map.of("keyCode", "3423423234234234324234234"));
        paymentService.save(paymentDomain, new Order().setId(1L));
        verify(pixPaymentRepository, times(1)).save(any(PixPayment.class));
        verify(cardPaymentRepository, times(0)).save(any(CardPayment.class));
        verify(ticketPaymentRepository, times(0)).save(any(TicketPayment.class));
    }

    @Test
    void shouldSaveCardPaymentSuccess() {
        var paymentDomain = new PaymentDomain().setPaymentType("CARD_PAYMENT").setPaymentParams(Map.of("number", "34234232342342343242", "name", "NOME PESSOA", "code", "123", "expirationDate", "2020-08-17T10:11:16.908732"));

        paymentService.save(paymentDomain, new Order().setId(1L));
        verify(cardPaymentRepository, times(1)).save(any(CardPayment.class));
        verify(pixPaymentRepository, times(0)).save(any(PixPayment.class));
        verify(ticketPaymentRepository, times(0)).save(any(TicketPayment.class));
    }

    @Test
    void shouldSaveTicketPaymentSuccess() {
        var paymentDomain = new PaymentDomain().setPaymentType("TICKET_PAYMENT").setPaymentParams(Map.of("codeBar", "3423423234234234324234234"));

        paymentService.save(paymentDomain, new Order().setId(1L));
        verify(ticketPaymentRepository, times(1)).save(any(TicketPayment.class));
        verify(pixPaymentRepository, times(0)).save(any(PixPayment.class));
        verify(cardPaymentRepository, times(0)).save(any(CardPayment.class));

    }
}