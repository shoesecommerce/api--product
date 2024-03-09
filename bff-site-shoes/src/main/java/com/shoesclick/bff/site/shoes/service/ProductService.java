package com.shoesclick.bff.site.shoes.service;

import com.shoesclick.bff.site.shoes.aspect.ObjectReturnType;
import com.shoesclick.bff.site.shoes.aspect.ReturnNullObject;
import com.shoesclick.bff.site.shoes.entity.Filter;
import com.shoesclick.bff.site.shoes.entity.ListProduct;
import com.shoesclick.bff.site.shoes.entity.Product;
import com.shoesclick.bff.site.shoes.repository.ProductRepository;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @ReturnNullObject(ObjectReturnType.SPRING_PAGE)
    public ListProduct filter(Filter filter) {
        return productRepository.filter(filter);
    }

    @ReturnNullObject(ObjectReturnType.SPRING_PAGE)
    public ListProduct listAll() {
        return productRepository.listAll();
    }

    @ReturnNullObject(ObjectReturnType.OBJECT)
    public Product findById(Long id) {
        return productRepository.findById(id);
    }

}