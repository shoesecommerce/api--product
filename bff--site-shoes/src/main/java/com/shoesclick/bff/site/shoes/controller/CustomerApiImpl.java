package com.shoesclick.bff.site.shoes.controller;

import com.shoesclick.bff.site.shoes.mapper.CustomerMapper;
import com.shoesclick.bff.site.shoes.openapi.controller.CustomerApi;
import com.shoesclick.bff.site.shoes.openapi.model.domain.CustomerRequest;
import com.shoesclick.bff.site.shoes.openapi.model.domain.CustomerResponse;
import com.shoesclick.bff.site.shoes.openapi.model.domain.StatusResponse;
import com.shoesclick.bff.site.shoes.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerApiImpl implements CustomerApi {

    private final CustomerService customerService;

    private final CustomerMapper customerMapper;

    public CustomerApiImpl(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @Override
    public ResponseEntity<StatusResponse> deleteCustomer(Long id) {
        return ResponseEntity.ok(customerMapper.map(customerService.delete(id)));
    }

    @Override
    public ResponseEntity<CustomerResponse> getCustomer(Long id) {
        return ResponseEntity.ok(customerMapper.map(customerService.findById(id)));
    }

    @Override
    public ResponseEntity<StatusResponse> saveCustomer(CustomerRequest customerRequest) {
        return ResponseEntity.status(201).body(customerMapper.map(customerService.save(customerMapper.map(customerRequest))));
    }


}
