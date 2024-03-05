package com.shoesclick.api.order.service;

import com.shoesclick.api.order.domain.PaymentDomain;
import com.shoesclick.api.order.entity.*;
import com.shoesclick.api.order.repository.CardPaymentRepository;
import com.shoesclick.api.order.repository.PixPaymentRepository;
import com.shoesclick.api.order.repository.TicketPaymentRepository;
import com.shoesclick.api.order.strategy.PaymentStrategy;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final CardPaymentRepository cardPaymentRepository;

    private final PixPaymentRepository pixPaymentRepository;

    private final TicketPaymentRepository ticketPaymentRepository;

    public PaymentService(CardPaymentRepository cardPaymentRepository, PixPaymentRepository pixPaymentRepository, TicketPaymentRepository ticketPaymentRepository) {
        this.cardPaymentRepository = cardPaymentRepository;
        this.pixPaymentRepository = pixPaymentRepository;
        this.ticketPaymentRepository = ticketPaymentRepository;
    }


    public void save(PaymentDomain paymentDomain, Order order) {
        var paymentStrategy = PaymentStrategy.findByName(paymentDomain.getPaymentType());
        switch (paymentStrategy) {
            case PIX_PAYMENT ->
                    pixPaymentRepository.save(paymentStrategy.convert(paymentDomain.getPaymentParams(), order));
            case CARD_PAYMENT -> cardPaymentRepository.save(paymentStrategy.convert(paymentDomain.getPaymentParams(), order));
            case TICKET_PAYMENT ->
                    ticketPaymentRepository.save(paymentStrategy.convert(paymentDomain.getPaymentParams(), order));
        }
    }
}
