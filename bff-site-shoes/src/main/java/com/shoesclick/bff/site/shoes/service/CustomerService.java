package com.shoesclick.bff.site.shoes.service;

import com.shoesclick.bff.site.shoes.aspect.ObjectReturnType;
import com.shoesclick.bff.site.shoes.aspect.ReturnNullObject;
import com.shoesclick.bff.site.shoes.entity.Customer;
import com.shoesclick.bff.site.shoes.entity.Status;
import com.shoesclick.bff.site.shoes.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Status save(Customer customer) {
       return customerRepository.save(customer);
    }

    @ReturnNullObject(ObjectReturnType.OBJECT)
    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }

    public Status delete(Long id) {
        return customerRepository.delete(id);
    }

}