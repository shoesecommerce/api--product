package com.shoesclick.service.payment.service;

import com.shoesclick.service.payment.entity.*;
import com.shoesclick.service.payment.repository.CardPaymentRepository;
import com.shoesclick.service.payment.repository.OrderRepository;
import com.shoesclick.service.payment.repository.PixPaymentRepository;
import com.shoesclick.service.payment.repository.BankSlipPaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.shoesclick.service.payment.mock.OrderMock.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class PaymentServiceTest extends AbstractServiceTest {

    @Mock
    private CardPaymentRepository cardPaymentRepository;

    @Mock
    private PixPaymentRepository pixPaymentRepository;

    @Mock
    private BankSlipPaymentRepository bankSlipPaymentRepository;
    @Mock
    private NotificationService notificationService;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setup() {
        paymentService = setupServiceTest(paymentService);
    }


    @Test
    void shouldProcessPayment_PIX_PAYMENT_Success(){
        paymentService.process(getPaymentDomain_PIX_PAYMENT());
        verify(pixPaymentRepository, times(1)).save(any(PixPayment.class));
        verify(orderRepository,times(1)).updateStatus(anyLong(), any(OrderStatus.class));
        verify(notificationService, times(1)).sendNotification(any(Notification.class));
    }

    @Test
    void shouldProcessPayment_CARD_PAYMENT_Success(){
        paymentService.process(getPaymentDomain_CARD_PAYMENT());
         verify(cardPaymentRepository, times(1)).save(any(CardPayment.class));
        verify(orderRepository,times(1)).updateStatus(anyLong(), any(OrderStatus.class));
        verify(notificationService, times(1)).sendNotification(any(Notification.class));
    }
    @Test
    void shouldProcessPayment_BANKSLIP_PAYMENT_Success(){
        paymentService.process(getPaymentDomain_BANKSLIP_PAYMENT());
        verify(bankSlipPaymentRepository, times(1)).save(any(BankSlipPayment.class));
        verify(orderRepository,times(1)).updateStatus(anyLong(), any(OrderStatus.class));
        verify(notificationService, times(1)).sendNotification(any(Notification.class));
    }
}
