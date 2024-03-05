package com.shoesclick.api.customer.controller;

import com.shoesclick.api.customer.mapper.CustomerMapper;
import com.shoesclick.api.customer.openapi.controller.CustomerApi;
import com.shoesclick.api.customer.openapi.model.domain.*;
import com.shoesclick.api.customer.service.CustomerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.shoesclick.api.customer.utils.Constants.NUMBER_PAGES;
import static com.shoesclick.api.customer.utils.Constants.SORT_ATTRIBUTE;

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
    public ResponseEntity<ListCustomerResponse> listAllCustomer() {
        return ResponseEntity.ok(customerMapper.map(customerService.filter(PageRequest.of(0, NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE)))));
    }

    @Override
    public ResponseEntity<ListCustomerResponse> filterCustomer(FilterRequest filterRequest) {
        return ResponseEntity.ok(customerMapper.map(customerService.filter(PageRequest.of(filterRequest.getPage(), NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE)))));
    }

    @Override
    public ResponseEntity<StatusResponse> saveCustomer(CustomerRequest customerRequest) {
        return ResponseEntity.status(201).body(customerMapper.map(customerService.save(customerMapper.map(customerRequest))));

    }
}
