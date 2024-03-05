package com.shoesclick.api.product.service;


import com.shoesclick.api.product.aspect.ObjectReturnType;
import com.shoesclick.api.product.aspect.ReturnNullObject;
import com.shoesclick.api.product.entity.Product;
import com.shoesclick.api.product.entity.Status;
import com.shoesclick.api.product.enums.Category;
import com.shoesclick.api.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Transactional
    public Status save(Product product) {
        productRepository.save(product);
        return new Status(0, "SUCESSO");
    }

    @Transactional
    @ReturnNullObject(ObjectReturnType.SPRING_PAGE)
    public Page<Product> filter(Category categoy, Pageable page) {
        return productRepository.findAllByCategory(categoy, page);
    }

    @Transactional
    @ReturnNullObject(ObjectReturnType.SPRING_PAGE)
    public Page<Product> filter(Pageable page) {
        return productRepository.findAll(page);
    }

    @ReturnNullObject(ObjectReturnType.OBJECT)
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }


    public Status delete(Long id) {
        productRepository.deleteById(id);
        return new Status(0, "SUCESSO");
    }
}