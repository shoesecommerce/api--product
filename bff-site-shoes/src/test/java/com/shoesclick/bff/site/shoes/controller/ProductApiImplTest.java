package com.shoesclick.bff.site.shoes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoesclick.bff.site.shoes.entity.Filter;
import com.shoesclick.bff.site.shoes.entity.ListProduct;
import com.shoesclick.bff.site.shoes.entity.Product;
import com.shoesclick.bff.site.shoes.exception.ElementNotFoundException;
import com.shoesclick.bff.site.shoes.exception.ListNotFoundException;
import com.shoesclick.bff.site.shoes.handler.ControllerErrorHandler;
import com.shoesclick.bff.site.shoes.mapper.ProductMapper;
import com.shoesclick.bff.site.shoes.openapi.model.domain.FilterRequest;
import com.shoesclick.bff.site.shoes.service.ProductService;
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

import java.math.BigDecimal;
import com.shoesclick.bff.site.shoes.enums.Category;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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
        when(productService.listAll()).thenReturn(new ListProduct());
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
        when(productService.listAll()).thenThrow(new ListNotFoundException("Erro"));
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
        when(productService.filter(any(Filter.class))).thenReturn(new ListProduct());
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/product/list")
                        .content(new ObjectMapper().writeValueAsString(getFilterRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }



    @Test
     void shouldReturnHttp204_FilterProductPost() throws Exception {
         when(productService.filter(any(Filter.class))).thenThrow(new ListNotFoundException("Erro"));
         when(productMapper.map(any(FilterRequest.class))).thenReturn(new Filter().setFilter("").setPage(1));
         mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/product/list")
                        .content(new ObjectMapper().writeValueAsString(getFilterRequest()))
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





    private static Product getProduct() {
        return new Product()
            .setId(1L)
            .setCode("2000")
            .setName("BOLSA PRETA")
            .setCategory(Category.ACESSORIO)
            .setDescription("BOLSA FEMININA PRETA")
            .setPrice(new BigDecimal(10.0));
    }



    private static FilterRequest getFilterRequest() {
        var filtroBusca = new FilterRequest();
        filtroBusca.setPage(1);
        filtroBusca.setFilter("ACESSORIO");
        return filtroBusca;
    }

}
