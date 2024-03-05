package com.shoesclick.api.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoesclick.api.customer.entity.Customer;
import com.shoesclick.api.customer.entity.Status;
import com.shoesclick.api.customer.exception.ElementNotFoundException;
import com.shoesclick.api.customer.exception.ListNotFoundException;
import com.shoesclick.api.customer.handler.ControllerErrorHandler;
import com.shoesclick.api.customer.mapper.CustomerMapper;
import com.shoesclick.api.customer.openapi.model.domain.CustomerRequest;
import com.shoesclick.api.customer.openapi.model.domain.FilterRequest;
import com.shoesclick.api.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
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
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerErrorHandler())
                .build();
    }

    @Test
    public void shouldReturnHttp200_ListCustomer() throws Exception {
        when(customerService.filter(any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(getCustomer())));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/customer/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    @Test
    public void shouldReturnHttp204_ListCustomer() throws Exception {
        when(customerService.filter(any(PageRequest.class))).thenThrow(new ListNotFoundException("Erro"));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/customer/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNoContent());
    }

    @Test
    public void shouldReturnHttp200_FilterCustomer() throws Exception {
        when(customerService.filter(any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(getCustomer())));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/customer/list")
                        .content(new ObjectMapper().writeValueAsString(getFilter()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }



    @Test
    public void shouldReturnHttp204_FilterCustomer() throws Exception {
         when(customerService.filter(any(PageRequest.class))).thenThrow(new ListNotFoundException("Erro"));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/customer/list")
                        .content(new ObjectMapper().writeValueAsString(getFilter()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNoContent());
    }

    @Test
    public void shouldReturnHttp200_GetCustomer() throws Exception {
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
    public void shouldReturnHttp404_GetCustomer() throws Exception {
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
    public void shouldReturnHttp200_DeleteCustomer() throws Exception {
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
    public void shouldReturnHttp200_SaveCustomer() throws Exception {
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

    private static Customer getCustomer() {
        return new Customer()
                .setName("nome")
                .setEmail("email")
                .setUsername("username")
                .setPassword("password");
    }



    private static FilterRequest getFilter() {
        var filtroBusca = new FilterRequest();
        filtroBusca.setPage(1);
        filtroBusca.setFilter("nome");
        return filtroBusca;
    }

}
