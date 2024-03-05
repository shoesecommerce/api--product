package com.shoesclick.api.customer.service;


import com.shoesclick.api.customer.entity.Customer;
import com.shoesclick.api.customer.exception.ElementNotFoundException;
import com.shoesclick.api.customer.exception.ListNotFoundException;
import com.shoesclick.api.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CustomerServiceTest extends AbstractServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;


    public static int NUMBER_PAGES = 10;

    public static final String SORT_ATTRIBUTE = "id";

    @BeforeEach
    void setup() {
        customerService = setupServiceTest(customerService);
    }


    @Test
    void shouldFilterCustomerSuccess() {
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Arrays.asList(new Customer())));
        var pageList = customerService.filter(PageRequest.of(1, NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE)));
        assertNotNull(pageList);
        assertEquals(1, pageList.getTotalElements());
        assertEquals(1, pageList.getTotalPages());
    }

    @Test
    void shouldFindCustomerByIdSuccess() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer()));
        var customer = customerService.findById(1L);
        assertNotNull(customer);
    }

    @Test
    void shouldThrow_ListNotFoundException_CustomerByIdNull() {
        when(customerRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
        assertThrows(ListNotFoundException.class, () -> customerService.filter(PageRequest.of(1, NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE))));
    }

    @Test
    void shouldThrow_ElementNotFoundException_CustomerByIdNull() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> customerService.findById(1L));
    }

    @Test
    void shouldSaveCustomerSuccess() {
        var customer = new Customer();
        customer.setId(1000L);
        customer.setName("Cliente");
        customer.setEmail("email@email.com");
        customer.setPassword("senha");
        customer.setUsername("usuario");
        var status = customerService.save(customer);
        verify(customerRepository, times(1)).save(any(Customer.class));
        assertNotNull(status);
    }

    @Test
    void shouldDeleteCustomerSuccess() {
        var status = customerService.delete(1L);
        assertNotNull(status);
        verify(customerRepository, times(1)).deleteById(any(Long.class));
    }

}
