package com.shoesclick.bff.site.shoes.service;

import com.shoesclick.bff.site.shoes.aspect.ObjectReturnType;
import com.shoesclick.bff.site.shoes.aspect.ReturnNullObject;
import com.shoesclick.bff.site.shoes.entity.Order;
import com.shoesclick.bff.site.shoes.entity.OrderStatus;
import com.shoesclick.bff.site.shoes.entity.Status;
import com.shoesclick.bff.site.shoes.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @ReturnNullObject(ObjectReturnType.OBJECT)
    public Order findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> listByCustomer(Long idCustomer) {
        return orderRepository.listByCustomer(idCustomer);
    }

    public Status updateStatus(Long id, OrderStatus orderStatus) {
        return orderRepository.updateStatus(id, orderStatus);
    }

    public Status save(Order order) {
        return orderRepository.save(order);
    }

}
