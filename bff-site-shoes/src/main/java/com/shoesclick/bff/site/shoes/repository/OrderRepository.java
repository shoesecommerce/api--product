package com.shoesclick.bff.site.shoes.repository;

import com.shoesclick.bff.site.shoes.entity.Order;
import com.shoesclick.bff.site.shoes.entity.OrderStatus;
import com.shoesclick.bff.site.shoes.entity.Status;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "orderClient", url = "${backend.services.orderUrl}")
public interface OrderRepository {


    @RequestMapping(method = RequestMethod.POST, value = "/api/order/save", produces = "application/json")
    Status save(Order order);

    @RequestMapping(method = RequestMethod.GET, value = "/api/order/{id}", produces = "application/json")
    Order findById(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/api/order/list/{idCustomer}", produces = "application/json")
    List<Order> listByCustomer(@PathVariable("idCustomer") Long idCustomer);

    @RequestMapping(method = RequestMethod.POST, value = "/api/order/{id}/status", produces = "application/json")
    Status updateStatus (@PathVariable("id") Long id, OrderStatus orderStatus);

}
