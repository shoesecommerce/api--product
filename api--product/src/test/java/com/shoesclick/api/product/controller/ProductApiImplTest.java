package com.shoesclick.api.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoesclick.api.product.entity.Product;
import com.shoesclick.api.product.entity.Status;
import com.shoesclick.api.product.enums.Category;
import com.shoesclick.api.product.exception.ElementNotFoundException;
import com.shoesclick.api.product.exception.ListNotFoundException;
import com.shoesclick.api.product.handler.ControllerErrorHandler;
import com.shoesclick.api.product.mapper.ProductMapper;
import com.shoesclick.api.product.openapi.model.domain.ProductRequest;
import com.shoesclick.api.product.openapi.model.domain.FilterRequest;
import com.shoesclick.api.product.service.ProductService;
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

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductApiImplTest {



    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Spy
    private ProductMapper productMapper;

    @InjectMocks
    private ProductApiImpl controller;

    @BeforeEach
     void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerErrorHandler())
                .build();
    }

    @Test
     void shouldReturnHttp200_ListProduct() throws Exception {
        when(productService.filter(any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(getProduct())));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/product/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    @Test
     void shouldReturnHttp204_ListProduct() throws Exception {
        when(productService.filter(any(PageRequest.class))).thenThrow(new ListNotFoundException("Erro"));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/product/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNoContent());
    }

    @Test
     void shouldReturnHttp200_FilterProductPost() throws Exception {
        when(productService.filter(any(Category.class), any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(getProduct())));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/product/list")
                        .content(new ObjectMapper().writeValueAsString(getFilter()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }



    @Test
     void shouldReturnHttp204_FilterProductPost() throws Exception {
         when(productService.filter(any(Category.class), any(PageRequest.class))).thenThrow(new ListNotFoundException("Erro"));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/product/list")
                        .content(new ObjectMapper().writeValueAsString(getFilter()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNoContent());
    }

    @Test
     void shouldReturnHttp200_GetProduct() throws Exception {
        when(productService.findById(anyLong())).thenReturn(new Product());
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/product/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    @Test
     void shouldReturnHttp404_GetProduct() throws Exception {
        when(productService.findById(anyLong())).thenThrow(new ElementNotFoundException("ERRO"));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/product/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNotFound());
    }

    @Test
     void shouldReturnHttp200_DeleteProduct() throws Exception {
        when(productService.delete(anyLong())).thenReturn(new Status(0, ""));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/product/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    @Test
     void shouldReturnHttp201_SaveProduct() throws Exception {
        when(productService.save(any(Product.class))).thenReturn(new Status(0, ""));
        when(productMapper.map(any(ProductRequest.class))).thenReturn(new Product());
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/product/save")
                        .content(new ObjectMapper().writeValueAsString(getProductRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated());
    }

    private ProductRequest getProductRequest() {
        var product = new ProductRequest();
        product.setId(1L);
        product.setCode("2000");
        product.setName("BOLSA PRETA");
        product.setCategory(Category.ACESSORIO.name());
        product.setDescription("BOLSA FEMININA PRETA");
        product.setPrice(new BigDecimal(10.00));
        return product;

    }

    private static Product getProduct() {
        return new Product()
            .setId(1L)
            .setCode("2000")
            .setName("BOLSA PRETA")
            .setCategory(Category.ACESSORIO)
            .setDescription("BOLSA FEMININA PRETA")
            .setPrice(new BigDecimal(10.0));
    }



    private static FilterRequest getFilter() {
        var filtroBusca = new FilterRequest();
        filtroBusca.setPage(1);
        filtroBusca.setFilter("ACESSORIO");
        return filtroBusca;
    }

}
