package com.shoesclick.service.payment.service;

import com.shoesclick.service.payment.domain.PaymentDomain;
import com.shoesclick.service.payment.entity.Log;
import com.shoesclick.service.payment.enums.TypeLog;
import com.shoesclick.service.payment.repository.CardPaymentRepository;
import com.shoesclick.service.payment.repository.LogRepository;
import com.shoesclick.service.payment.repository.PixPaymentRepository;
import com.shoesclick.service.payment.repository.TicketPaymentRepository;
import com.shoesclick.service.payment.strategy.PaymentStrategy;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final CardPaymentRepository cardPaymentRepository;

    private final PixPaymentRepository pixPaymentRepository;

    private final TicketPaymentRepository ticketPaymentRepository;

    private final LogRepository logRepository;

    public PaymentService(CardPaymentRepository cardPaymentRepository, PixPaymentRepository pixPaymentRepository, TicketPaymentRepository ticketPaymentRepository, LogRepository logRepository) {
        this.cardPaymentRepository = cardPaymentRepository;
        this.pixPaymentRepository = pixPaymentRepository;
        this.ticketPaymentRepository = ticketPaymentRepository;
        this.logRepository = logRepository;
    }


    @Transactional
    public void process(PaymentDomain paymentDomain) {
        Log log = null;
        try {
            savePayment(paymentDomain);
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
}
