package com.shoesclick.api.order.repository;

import com.shoesclick.api.order.entity.PixPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixPaymentRepository extends JpaRepository<PixPayment, Long> {
}
