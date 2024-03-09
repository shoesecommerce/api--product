package com.shoesclick.bff.site.shoes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoesclick.bff.site.shoes.entity.Customer;
import com.shoesclick.bff.site.shoes.entity.Status;
import com.shoesclick.bff.site.shoes.exception.ElementNotFoundException;
import com.shoesclick.bff.site.shoes.handler.ControllerErrorHandler;
import com.shoesclick.bff.site.shoes.mapper.CustomerMapper;
import com.shoesclick.bff.site.shoes.openapi.model.domain.CustomerRequest;
import com.shoesclick.bff.site.shoes.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CustomerApiImplTest {



    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @Spy
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerApiImpl controller;

    @BeforeEach
     void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerErrorHandler())
                .build();
    }

    @Test
     void shouldReturnHttp200_GetCustomer() throws Exception {
        when(customerService.findById(anyLong())).thenReturn(new Customer());
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/customer/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    @Test
     void shouldReturnHttp404_GetCustomer() throws Exception {
        when(customerService.findById(anyLong())).thenThrow(new ElementNotFoundException("ERRO"));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/customer/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNotFound());
    }

    @Test
     void shouldReturnHttp200_DeleteCustomer() throws Exception {
        when(customerService.delete(anyLong())).thenReturn(new Status(0, ""));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/customer/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    @Test
     void shouldReturnHttp201_SaveCustomer() throws Exception {
        when(customerService.save(any(Customer.class))).thenReturn(new Status(0, ""));
        when(customerMapper.map(any(CustomerRequest.class))).thenReturn(new Customer());
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/customer/save")
                        .content(new ObjectMapper().writeValueAsString(getCustomerRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated());
    }

    private CustomerRequest getCustomerRequest() {
        var informacaoRequest = new CustomerRequest();
        informacaoRequest.setName("NOME");
        informacaoRequest.setEmail("EMAIL");
        informacaoRequest.setUsername("USERNAME");
        informacaoRequest.setPassword("PASSWORD");
        return informacaoRequest;

    }
}
