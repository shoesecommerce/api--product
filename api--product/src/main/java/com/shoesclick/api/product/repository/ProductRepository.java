package com.shoesclick.api.product.repository;

import com.shoesclick.api.product.entity.Product;
import com.shoesclick.api.product.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from #{#entityName} p where p.category = :category")
    Page<Product> findByCategory(@Param("category") Category category, Pageable page);

}
