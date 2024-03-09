package com.shoesclick.bff.site.shoes.controller;

import com.shoesclick.bff.site.shoes.mapper.OrderMapper;
import com.shoesclick.bff.site.shoes.openapi.controller.OrderApi;
import com.shoesclick.bff.site.shoes.openapi.model.domain.OrderRequest;
import com.shoesclick.bff.site.shoes.openapi.model.domain.OrderResponse;
import com.shoesclick.bff.site.shoes.openapi.model.domain.OrderStatusRequest;
import com.shoesclick.bff.site.shoes.openapi.model.domain.StatusResponse;
import com.shoesclick.bff.site.shoes.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderApiImpl implements OrderApi {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    public OrderApiImpl(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @Override
    public ResponseEntity<OrderResponse> findById(Long id) {
        return ResponseEntity.ok(orderMapper.map(orderService.findById(id)));
    }

    @Override
    public ResponseEntity<List<OrderResponse>> listByCustomer(Long idCustomer) {
        return ResponseEntity.ok(orderMapper.map(orderService.listByCustomer(idCustomer)));
    }

    @Override
    public ResponseEntity<StatusResponse> save(OrderRequest orderRequest) {
        return ResponseEntity.status(201).body(orderMapper.map(orderService.save(orderMapper.map(orderRequest))));
    }

    @Override
    public ResponseEntity<StatusResponse> updateStatus(Long id, OrderStatusRequest orderStatusRequest) {
        return ResponseEntity.ok(orderMapper.map(orderService.updateStatus(id, orderMapper.map(orderStatusRequest))));
    }
}



