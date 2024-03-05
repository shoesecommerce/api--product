package com.shoesclick.api.order.mapper;

import com.shoesclick.api.order.domain.PaymentDomain;
import com.shoesclick.api.order.entity.Order;
import com.shoesclick.api.order.entity.Status;
import com.shoesclick.api.order.openapi.model.domain.OrderRequest;
import com.shoesclick.api.order.openapi.model.domain.OrderResponse;
import com.shoesclick.api.order.openapi.model.domain.StatusResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.Map;

@Mapper(componentModel= MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    Order map(OrderRequest request);

    OrderResponse map(Order order);

    List<OrderResponse> map(List<Order> orderList);

    default PaymentDomain mapPayment(String paymentType, Map<String, Object> payParams){
        return new PaymentDomain()
                .setPaymentType(paymentType)
                .setPaymentParams(payParams);
    }

    StatusResponse map(Status response);
}
