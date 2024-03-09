package com.shoesclick.bff.site.shoes.mapper;

import com.shoesclick.bff.site.shoes.domain.PaymentDomain;
import com.shoesclick.bff.site.shoes.entity.Order;
import com.shoesclick.bff.site.shoes.entity.OrderStatus;
import com.shoesclick.bff.site.shoes.entity.Status;
import com.shoesclick.bff.site.shoes.openapi.model.domain.OrderRequest;
import com.shoesclick.bff.site.shoes.openapi.model.domain.OrderResponse;
import com.shoesclick.bff.site.shoes.openapi.model.domain.OrderStatusRequest;
import com.shoesclick.bff.site.shoes.openapi.model.domain.StatusResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.Map;

@Mapper(componentModel= MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    Order map(OrderRequest request);

    OrderResponse map(Order order);

    List<OrderResponse> map(List<Order> orderList);

    StatusResponse map(Status response);

    OrderStatus map(OrderStatusRequest request);

}
