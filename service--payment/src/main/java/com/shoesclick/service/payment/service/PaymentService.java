package com.shoesclick.service.payment.service;

import com.shoesclick.service.payment.domain.PaymentDomain;
import com.shoesclick.service.payment.entity.Log;
import com.shoesclick.service.payment.entity.Notification;
import com.shoesclick.service.payment.entity.Order;
import com.shoesclick.service.payment.entity.OrderStatus;
import com.shoesclick.service.payment.enums.TypeLog;
import com.shoesclick.service.payment.enums.TypeTemplate;
import com.shoesclick.service.payment.repository.*;
import com.shoesclick.service.payment.strategy.PaymentStrategy;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final Integer PAYMENT_PROCESS = 2;
    private final CardPaymentRepository cardPaymentRepository;

    private final PixPaymentRepository pixPaymentRepository;

    private final TicketPaymentRepository ticketPaymentRepository;

    private final LogRepository logRepository;

    private final NotificationService notificationService;

    private final OrderRepository orderRepository;

    public PaymentService(CardPaymentRepository cardPaymentRepository, PixPaymentRepository pixPaymentRepository, TicketPaymentRepository ticketPaymentRepository, LogRepository logRepository, NotificationService notificationService, OrderRepository orderRepository) {
        this.cardPaymentRepository = cardPaymentRepository;
        this.pixPaymentRepository = pixPaymentRepository;
        this.ticketPaymentRepository = ticketPaymentRepository;
        this.logRepository = logRepository;
        this.notificationService = notificationService;
        this.orderRepository = orderRepository;
    }


    @Transactional
    public void process(PaymentDomain paymentDomain) {
        Log log = null;
        try {
            savePayment(paymentDomain);
            orderRepository.updateStatus(paymentDomain.getOrder().getId(),new OrderStatus().setStatus(PAYMENT_PROCESS));
            notificationService.sendNotification(getNotification(paymentDomain.getOrder()));
            log = setLog("PROCESSADO COM SUCESSO A NOTIFICAÇÃO " + paymentDomain.getOrder().getId(), "EXCECUTADO COM SUCESSO", TypeLog.SUCCESS);

        } catch (Exception e) {
            log = setLog("ERRO AO PROCESSAR A NOTIFICAÇÃO " + paymentDomain.getOrder().getId(), e.getMessage(), TypeLog.ERROR);
        } finally {
            logRepository.save(log);
        }
    }

     private void savePayment(PaymentDomain paymentDomain) {
        var paymentStrategy = PaymentStrategy.findByName(paymentDomain.getPaymentType());
        switch (paymentStrategy) {
            case PIX_PAYMENT ->
                    pixPaymentRepository.save(paymentStrategy.convert(paymentDomain.getPaymentParams(), paymentDomain.getOrder()));
            case CARD_PAYMENT ->
                    cardPaymentRepository.save(paymentStrategy.convert(paymentDomain.getPaymentParams(), paymentDomain.getOrder()));
            case TICKET_PAYMENT ->
                    ticketPaymentRepository.save(paymentStrategy.convert(paymentDomain.getPaymentParams(), paymentDomain.getOrder()));
        }
    }

    private Log setLog(String message, String details, TypeLog success) {
        return new Log().setClassName(this.getClass().getName())
                .setError(message)
                .setDetails(details)
                .setTypeLog(success);
    }

    private Notification getNotification(Order order) {
            return new Notification()
                    .setIdOrder(order.getId())
                    .setTypeTemplate(TypeTemplate.PAYMENT_APPROVED)
                    .setIdCustomer(order.getIdCustomer());
    }
}
