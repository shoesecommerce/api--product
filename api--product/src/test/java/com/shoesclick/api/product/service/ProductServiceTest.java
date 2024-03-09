package com.shoesclick.api.product.service;


import com.shoesclick.api.product.entity.Product;
import com.shoesclick.api.product.enums.Category;
import com.shoesclick.api.product.exception.ElementNotFoundException;
import com.shoesclick.api.product.exception.ListNotFoundException;
import com.shoesclick.api.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ProductServiceTest extends AbstractServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    public static int NUMBER_PAGES = 10;

    public static final String SORT_ATTRIBUTE = "id";

    @BeforeEach
    void setup() {
        productService = setupServiceTest(productService);
    }


    @Test
    void shouldFilterProductSuccess() {
        when(productRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Arrays.asList(new Product())));
        var pageList = productService.filter(PageRequest.of(1, NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE)));
        assertNotNull(pageList);
        assertEquals(1, pageList.getTotalElements());
        assertEquals(1, pageList.getTotalPages());
    }

    @Test
    void shouldFilterCategoryProductSuccess() {
        when(productRepository.findByCategory(any(Category.class), any(Pageable.class))).thenReturn(new PageImpl<>(Arrays.asList(new Product())));
        var pageList = productService.filter(Category.ACESSORIO, PageRequest.of(1, NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE)));
        assertNotNull(pageList);
        assertEquals(1, pageList.getTotalElements());
        assertEquals(1, pageList.getTotalPages());
    }

    @Test
    void shouldFindProductByIdSuccess() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(new Product()));
        var product = productService.findById(1L);
        assertNotNull(product);
    }

    @Test
    void shouldThrow_ListNotFoundException_FilterProductEmpty() {
        when(productRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
        assertThrows(ListNotFoundException.class, () -> productService.filter(PageRequest.of(1, NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE))));
    }

    @Test
    void shouldThrow_ListNotFoundException_FilterProductCategoryEmpty() {
        when(productRepository.findByCategory(any(Category.class), any(Pageable.class))).thenReturn(Page.empty());
        assertThrows(ListNotFoundException.class, () -> productService.filter(Category.CALCADO, PageRequest.of(1, NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE))));
    }


    @Test
    void shouldThrow_ElementNotFoundException_ProductByIdNull() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> productService.findById(1L));
    }

    @Test
    void shouldSaveProductSuccess() {
        var product = new Product();
        product.setId(1l);
        product.setCode("2000");
        product.setName("BOLSA PRETA");
        product.setCategory(Category.ACESSORIO);
        product.setDescription("BOLSA FEMININA PRETA");
        product.setPrice(new BigDecimal(10.00));
        var status = productService.save(product);
        verify(productRepository, times(1)).save(any(Product.class));
        assertNotNull(status);
    }

    @Test
    void shouldDeleteProductSuccess() {
        var status = productService.delete(1L);
        assertNotNull(status);
        verify(productRepository, times(1)).deleteById(any(Long.class));
    }

}
