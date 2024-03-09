package com.shoesclick.bff.site.shoes.mapper;


import com.shoesclick.bff.site.shoes.entity.Filter;
import com.shoesclick.bff.site.shoes.entity.ListProduct;
import com.shoesclick.bff.site.shoes.entity.Product;
import com.shoesclick.bff.site.shoes.entity.Status;
import com.shoesclick.bff.site.shoes.openapi.model.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel= MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductResponse map(Product response);

    Product map(ProductRequest request);

    ListProductResponse map(ListProduct response);

    StatusResponse map(Status response);

    Filter map(FilterRequest request);


}
