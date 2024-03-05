package com.shoesclick.api.product.mapper;


import com.shoesclick.api.product.entity.Product;
import com.shoesclick.api.product.entity.Status;
import com.shoesclick.api.product.openapi.model.domain.ListProductResponse;
import com.shoesclick.api.product.openapi.model.domain.ProductRequest;
import com.shoesclick.api.product.openapi.model.domain.ProductResponse;
import com.shoesclick.api.product.openapi.model.domain.StatusResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;

@Mapper(componentModel= MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductResponse map(Product response);

    Product map(ProductRequest request);

    ListProductResponse map(Page<Product> response);

    StatusResponse map(Status response);


}
