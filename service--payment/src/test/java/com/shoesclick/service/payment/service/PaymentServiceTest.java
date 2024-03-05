package com.shoesclick.service.payment.service;

import com.shoesclick.service.payment.domain.PaymentDomain;
import com.shoesclick.service.payment.entity.*;
import com.shoesclick.service.payment.repository.CardPaymentRepository;
import com.shoesclick.service.payment.repository.LogRepository;
import com.shoesclick.service.payment.repository.PixPaymentRepository;
import com.shoesclick.service.payment.repository.TicketPaymentRepository;
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

    @Mock
    private LogRepository logRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void shouldProcessPayment_PIX_PAYMENT_Success(){
        paymentService.process(new PaymentDomain().setOrder(new Order()).setPaymentParams(Map.of("keyCode","435345435345345345345345435345")).setPaymentType("PIX_PAYMENT"));
        verify(logRepository, times(1)).save(any(Log.class));
        verify(pixPaymentRepository, times(1)).save(any(PixPayment.class));
    }

    @Test
    void shouldProcessPayment_CARD_PAYMENT_Success(){
        paymentService.process(new PaymentDomain().setOrder(new Order()).setPaymentParams(Map.of("number","234234234234","name","NOME PESSOA","code","123","expirationDate","2024-01-13T17:09:42.411")).setPaymentType("CARD_PAYMENT"));
        verify(logRepository, times(1)).save(any(Log.class));
        verify(cardPaymentRepository, times(1)).save(any(CardPayment.class));
    }

    @Test
    void shouldProcessPayment_TICKET_PAYMENT_Success(){
        paymentService.process(new PaymentDomain().setOrder(new Order()).setPaymentParams(Map.of("codeBar","3324234234234234324324324234")).setPaymentType("TICKET_PAYMENT"));
        verify(logRepository, times(1)).save(any(Log.class));
        verify(ticketPaymentRepository, times(1)).save(any(TicketPayment.class));
    }
}
