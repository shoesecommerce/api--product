package com.shoesclick.bff.site.shoes.service;


import com.shoesclick.bff.site.shoes.entity.Order;
import com.shoesclick.bff.site.shoes.entity.OrderItem;
import com.shoesclick.bff.site.shoes.entity.OrderStatus;
import com.shoesclick.bff.site.shoes.entity.Status;
import com.shoesclick.bff.site.shoes.exception.ElementNotFoundException;
import com.shoesclick.bff.site.shoes.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class OrderServiceTest extends AbstractServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setup() {
        orderService = setupServiceTest(orderService);
    }


    @Test
    void shouldSaveOrderSuccess() {
        when(orderRepository.save(any(Order.class))).thenReturn(new Status(1,""));
        var order = new Order();
        order.setIdCustomer(1L);
        order.setOrderItems(Arrays.asList(new OrderItem().setIdProduct(1L)));
        var status = orderService.save(order);
        assertNotNull(status);
      }

    @Test
    void shouldUpdateStatusOrderSuccess() {
        var order = new OrderStatus();
        when(orderRepository.updateStatus(anyLong(), any(OrderStatus.class))).thenReturn(new Status(1,""));
        var status = orderService.updateStatus(1L, order);
        assertNotNull(status);
     }

    @Test
    void shouldListOrderByCustomer() {
        when(orderRepository.listByCustomer(anyLong())).thenReturn(Arrays.asList(new Order().setId(1L).setIdCustomer(1L).setCreatedAt(LocalDateTime.now())));
        var list = orderService.listByCustomer(1L);
        assertNotNull(list);
    }

    @Test
    void shouldFindOrderByIdSuccess() {
        when(orderRepository.findById(anyLong())).thenReturn(new Order());
        var order = orderService.findById(1L);
        assertNotNull(order);
    }

    @Test
    void shouldThrow_ElementNotFoundException_OrderByIdNull() {
        when(orderRepository.findById(anyLong())).thenReturn(null);
        assertThrows(ElementNotFoundException.class, () -> orderService.findById(1L));
    }

}
