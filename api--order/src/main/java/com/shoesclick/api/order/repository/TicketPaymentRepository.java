package com.shoesclick.api.order.repository;

import com.shoesclick.api.order.entity.TicketPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketPaymentRepository extends JpaRepository<TicketPayment, Long> {
}
