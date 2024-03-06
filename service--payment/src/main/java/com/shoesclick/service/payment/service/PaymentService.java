package com.shoesclick.service.payment.service;

import com.shoesclick.service.payment.aspect.InjectLogger;
import com.shoesclick.service.payment.domain.PaymentDomain;
import com.shoesclick.service.payment.entity.Notification;
import com.shoesclick.service.payment.entity.Order;
import com.shoesclick.service.payment.entity.OrderStatus;
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

    private final BankSlipPaymentRepository bankSlipPaymentRepository;

    private final NotificationService notificationService;

    private final OrderRepository orderRepository;

    public PaymentService(CardPaymentRepository cardPaymentRepository, PixPaymentRepository pixPaymentRepository, BankSlipPaymentRepository bankSlipPaymentRepository, NotificationService notificationService, OrderRepository orderRepository) {
        this.cardPaymentRepository = cardPaymentRepository;
        this.pixPaymentRepository = pixPaymentRepository;
        this.bankSlipPaymentRepository = bankSlipPaymentRepository;
        this.notificationService = notificationService;
        this.orderRepository = orderRepository;
    }


    @Transactional
    @InjectLogger
    public void process(PaymentDomain paymentDomain) {
        savePayment(paymentDomain);
        orderRepository.updateStatus(paymentDomain.getOrder().getId(), new OrderStatus().setStatus(PAYMENT_PROCESS));
        notificationService.sendNotification(getNotification(paymentDomain.getOrder()));
    }


    private void savePayment(PaymentDomain paymentDomain) {

        var paymentStrategy = PaymentStrategy.findByName(paymentDomain.getPaymentType());
        switch (paymentStrategy) {
            case PIX_PAYMENT ->
                    pixPaymentRepository.save(paymentStrategy.convert(paymentDomain.getPaymentParams(), paymentDomain.getOrder()));
            case CARD_PAYMENT ->
                    cardPaymentRepository.save(paymentStrategy.convert(paymentDomain.getPaymentParams(), paymentDomain.getOrder()));
            case BANKSLIP_PAYMENT ->
                    bankSlipPaymentRepository.save(paymentStrategy.convert(paymentDomain.getPaymentParams(), paymentDomain.getOrder()));
        }
    }

    private Notification getNotification(Order order) {
        return new Notification()
                .setIdOrder(order.getId())
                .setTypeTemplate(TypeTemplate.PAYMENT_APPROVED)
                .setIdCustomer(order.getIdCustomer());
    }
}
