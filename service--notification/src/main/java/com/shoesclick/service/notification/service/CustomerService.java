package com.shoesclick.service.notification.service;

import com.shoesclick.service.notification.entity.Customer;
import com.shoesclick.service.notification.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findById(Long id){
        return customerRepository.findById(id);
    }
}
