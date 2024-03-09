package com.shoesclick.bff.site.shoes.service;

import com.shoesclick.bff.site.shoes.entity.Customer;
import com.shoesclick.bff.site.shoes.entity.Status;
import com.shoesclick.bff.site.shoes.exception.ElementNotFoundException;
import com.shoesclick.bff.site.shoes.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CustomerServiceTest extends AbstractServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setup() {
        customerService = setupServiceTest(customerService);
    }

    @Test
    void shouldFindCustomerByIdSuccess() {
        when(customerRepository.findById(anyLong())).thenReturn(new Customer());
        var customer = customerService.findById(1L);
        assertNotNull(customer);
    }


    @Test
    void shouldSaveCustomerSuccess() {
        var customer = new Customer();
        when(customerService.save(any(Customer.class))).thenReturn(new Status(1, "SUCESSO"));
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
        when(customerService.delete(anyLong())).thenReturn(new Status(1, "SUCESSO"));
        var status = customerService.delete(1L);
        assertNotNull(status);
        verify(customerRepository, times(1)).delete(any(Long.class));
    }

    @Test
    void shouldThrow_ElementNotFoundException_ProductByIdNull() {
        when(customerRepository.findById(anyLong())).thenReturn(null);
        assertThrows(ElementNotFoundException.class, () -> customerService.findById(1L));
    }

}
