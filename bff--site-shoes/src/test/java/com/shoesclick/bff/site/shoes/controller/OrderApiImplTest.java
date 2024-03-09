package com.shoesclick.bff.site.shoes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoesclick.bff.site.shoes.entity.Order;
import com.shoesclick.bff.site.shoes.entity.OrderStatus;
import com.shoesclick.bff.site.shoes.entity.Status;
import com.shoesclick.bff.site.shoes.exception.ElementNotFoundException;
import com.shoesclick.bff.site.shoes.exception.ListNotFoundException;
import com.shoesclick.bff.site.shoes.handler.ControllerErrorHandler;
import com.shoesclick.bff.site.shoes.mapper.OrderMapper;
import com.shoesclick.bff.site.shoes.openapi.model.domain.OrderRequest;
import com.shoesclick.bff.site.shoes.openapi.model.domain.OrderStatusRequest;
import com.shoesclick.bff.site.shoes.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderApiImplTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @Spy
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderApiImpl controller;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerErrorHandler())
                .build();
    }

    @Test
    void shouldReturnHttp201_SaveOrder() throws Exception {
        when(orderService.save(any(Order.class))).thenReturn(new Status(0, ""));
        when(orderMapper.map(any(OrderRequest.class))).thenReturn(new Order());
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/order/save")
                        .content(new ObjectMapper().writeValueAsString(getOrderRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated());
    }

    @Test
    void shouldReturnHttp200_GetOrder() throws Exception {
        when(orderService.findById(anyLong())).thenReturn(new Order());
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/order/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    @Test
    void shouldReturnHttp404_GetOrder() throws Exception {
        when(orderService.findById(anyLong())).thenThrow(new ElementNotFoundException("ERRO"));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/order/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNotFound());
    }


    @Test
    void shouldReturnHttp200_ListOrderByCustomer() throws Exception {
        when(orderService.listByCustomer(anyLong())).thenReturn(List.of(new Order()));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/order/list/{idCustomer}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }


    @Test
    void shouldReturnHttp204_ListOrderByCustomer() throws Exception {
        when(orderService.listByCustomer(anyLong())).thenThrow(new ListNotFoundException("Erro"));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/order/list/{idCustomer}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNoContent());
    }

    @Test
    void shouldReturnHttp200_UpdateStatus() throws Exception {
        when(orderMapper.map(any(OrderStatusRequest.class))).thenReturn(new OrderStatus());
        when(orderService.updateStatus(anyLong(), any(OrderStatus.class))).thenReturn(new Status(0, ""));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/order/{id}/status", 1L)
                        .content(new ObjectMapper().writeValueAsString(getOrderStatusRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    private OrderStatusRequest getOrderStatusRequest() {
        var orderStatus = new OrderStatusRequest();
        orderStatus.setStatus(1);
        return orderStatus;
    }

    private OrderRequest getOrderRequest() {
        var request = new OrderRequest();
        request.setPaymentType("PIX_PAYMENT");
        request.setPaymentParams(Map.of("keyCode", "3423423234234234324234234"));
        return request;
    }

}
