package com.shoesclick.api.customer.service;


import com.shoesclick.api.customer.aspect.ObjectReturnType;
import com.shoesclick.api.customer.aspect.ReturnNullObject;
import com.shoesclick.api.customer.entity.Customer;
import com.shoesclick.api.customer.entity.Status;
import com.shoesclick.api.customer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Transactional
    public Status save(Customer customer) {
        customerRepository.save(customer);
        return new Status(0,"SUCESSO");
    }


    @Transactional
    @ReturnNullObject(ObjectReturnType.SPRING_PAGE)
    public Page<Customer> filter(Pageable page) {
        return customerRepository.findAll(page);
    }

    @ReturnNullObject(ObjectReturnType.OBJECT)
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }


    public Status delete(Long id) {
        customerRepository.deleteById(id);
        return new Status(0,"SUCESSO");
    }

}