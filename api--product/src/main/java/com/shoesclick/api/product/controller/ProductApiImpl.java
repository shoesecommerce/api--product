package com.shoesclick.api.product.controller;

import com.shoesclick.api.product.enums.Category;
import com.shoesclick.api.product.mapper.ProductMapper;
import com.shoesclick.api.product.openapi.controller.ProductApi;
import com.shoesclick.api.product.openapi.model.domain.*;
import com.shoesclick.api.product.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.shoesclick.api.product.utils.Constants.NUMBER_PAGES;
import static com.shoesclick.api.product.utils.Constants.SORT_ATTRIBUTE;

@RestController
public class ProductApiImpl implements ProductApi {

    private final ProductService productService;

    private final ProductMapper productMapper;

    public ProductApiImpl(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    public ResponseEntity<StatusResponse> deleteProduct(Long id) {
        return ResponseEntity.ok(productMapper.map(productService.delete(id)));
    }

    @Override
    public ResponseEntity<ProductResponse> getProduct(Long id) {
        return ResponseEntity.ok(productMapper.map(productService.findById(id)));
    }

    @Override
    public ResponseEntity<ListProductResponse> listAllProduct() {
        return ResponseEntity.ok(productMapper.map(productService.filter(PageRequest.of(0, NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE)))));
    }

    @Override
    public ResponseEntity<ListProductResponse> filterProduct(FilterRequest filterRequest) {
        return ResponseEntity.ok(productMapper.map(productService.filter(Category.valueOf(filterRequest.getFilter()), PageRequest.of(filterRequest.getPage(), NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE)))));
    }

    @Override
    public ResponseEntity<StatusResponse> saveProduct(ProductRequest productRequest) {
        return ResponseEntity.status(201).body(productMapper.map(productService.save(productMapper.map(productRequest))));

    }
}
