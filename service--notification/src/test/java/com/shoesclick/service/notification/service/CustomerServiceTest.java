package com.shoesclick.service.notification.service;

import com.shoesclick.service.notification.entity.Customer;
import com.shoesclick.service.notification.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void sholdCusomerFindByIdSuccess(){
        when(customerRepository.findById(anyLong())).thenReturn(new Customer());
        var customer = customerService.findById(1L);
        assertNotNull(customer);
    }
}
