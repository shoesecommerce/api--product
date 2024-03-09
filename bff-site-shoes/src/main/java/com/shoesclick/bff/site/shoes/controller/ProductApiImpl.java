package com.shoesclick.bff.site.shoes.controller;

import com.shoesclick.bff.site.shoes.enums.Category;
import com.shoesclick.bff.site.shoes.mapper.ProductMapper;
import com.shoesclick.bff.site.shoes.openapi.controller.ProductApi;
import com.shoesclick.bff.site.shoes.openapi.model.domain.FilterRequest;
import com.shoesclick.bff.site.shoes.openapi.model.domain.ListProductResponse;
import com.shoesclick.bff.site.shoes.openapi.model.domain.ProductResponse;
import com.shoesclick.bff.site.shoes.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApiImpl implements ProductApi {

    private final ProductService productService;

    private final ProductMapper productMapper;

    public ProductApiImpl(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    public ResponseEntity<ProductResponse> getProduct(Long id) {
        return ResponseEntity.ok(productMapper.map(productService.findById(id)));
    }

    @Override
    public ResponseEntity<ListProductResponse> listAllProduct() {
        return ResponseEntity.ok(productMapper.map(productService.listAll()));
    }

    @Override
    public ResponseEntity<ListProductResponse> filterProduct(FilterRequest filterRequest) {
        return ResponseEntity.ok(productMapper.map(productService.filter(productMapper.map(filterRequest))));
    }

}
