package com.shoesclick.service.payment.strategy;

import com.shoesclick.service.payment.domain.PaymentDomain;
import com.shoesclick.service.payment.entity.*;
import com.shoesclick.service.payment.repository.CardPaymentRepository;
import com.shoesclick.service.payment.repository.PixPaymentRepository;
import com.shoesclick.service.payment.repository.TicketPaymentRepository;
import jakarta.transaction.Transactional;
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


    @Transactional
    public void process(PaymentDomain paymentDomain) {
        savePayment(paymentDomain);
    }

    private void savePayment(PaymentDomain paymentDomain) {
        var paymentStrategy = PaymentStrategy.findByName(paymentDomain.getPaymentType());
        switch (paymentStrategy) {
            case PIX_PAYMENT ->
                    pixPaymentRepository.save(paymentStrategy.convert(paymentDomain.getPaymentParams(), paymentDomain.getOrder()));
            case CARD_PAYMENT -> cardPaymentRepository.save(paymentStrategy.convert(paymentDomain.getPaymentParams(), paymentDomain.getOrder()));
            case TICKET_PAYMENT ->
                    ticketPaymentRepository.save(paymentStrategy.convert(paymentDomain.getPaymentParams(), paymentDomain.getOrder()));
        }
    }
}
