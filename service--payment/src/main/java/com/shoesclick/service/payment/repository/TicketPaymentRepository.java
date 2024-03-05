package com.shoesclick.service.payment.repository;

import com.shoesclick.service.payment.entity.TicketPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketPaymentRepository extends JpaRepository<TicketPayment, Long> {
}
