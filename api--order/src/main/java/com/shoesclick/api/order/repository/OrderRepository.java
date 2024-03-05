package com.shoesclick.api.order.repository;

import com.shoesclick.api.order.entity.CardPayment;
import com.shoesclick.api.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByIdCustomer(Long idCustomer);

}
