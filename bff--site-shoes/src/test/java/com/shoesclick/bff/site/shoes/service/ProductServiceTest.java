package com.shoesclick.bff.site.shoes.service;


import com.shoesclick.bff.site.shoes.entity.Filter;
import com.shoesclick.bff.site.shoes.entity.ListProduct;
import com.shoesclick.bff.site.shoes.entity.Product;
import com.shoesclick.bff.site.shoes.exception.ElementNotFoundException;
import com.shoesclick.bff.site.shoes.exception.ListNotFoundException;
import com.shoesclick.bff.site.shoes.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ProductServiceTest extends AbstractServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setup() {
        productService = setupServiceTest(productService);
    }


    @Test
    void shouldFilterProductSuccess() {
        when(productRepository.listAll()).thenReturn(new ListProduct().setTotalPages(1).setTotalElements(1).setContent(Arrays.asList(new Product())));
        var pageList = productService.listAll();
        assertNotNull(pageList);
        assertEquals(1, pageList.getTotalElements());
        assertEquals(1, pageList.getTotalPages());
    }

    @Test
    void shouldFilterCategoryProductSuccess() {
        when(productRepository.filter(any(Filter.class))).thenReturn(new ListProduct().setTotalPages(1).setTotalElements(1).setContent(Arrays.asList(new Product())));
        var pageList = productService.filter(new Filter().setFilter("ACESSORIO").setPage(1));
        assertNotNull(pageList);
        assertEquals(1, pageList.getTotalElements());
        assertEquals(1, pageList.getTotalPages());
    }

    @Test
    void shouldFindProductByIdSuccess() {
        when(productRepository.findById(anyLong())).thenReturn(new Product());
        var product = productService.findById(1L);
        assertNotNull(product);
    }

    @Test
    void shouldThrow_ListNotFoundException_FilterProductEmpty() {
        when(productRepository.listAll()).thenReturn(null);
        assertThrows(ListNotFoundException.class, () -> productService.listAll());
    }

    @Test
    void shouldThrow_ListNotFoundException_FilterProductCategoryEmpty() {
        when(productRepository.filter(any(Filter.class))).thenReturn(null);
        assertThrows(ListNotFoundException.class, () -> productService.filter(new Filter()));
    }

    @Test
    void shouldThrow_ElementNotFoundException_ProductByIdNull() {
        when(productRepository.findById(anyLong())).thenReturn(null);
        assertThrows(ElementNotFoundException.class, () -> productService.findById(1L));
    }
}
