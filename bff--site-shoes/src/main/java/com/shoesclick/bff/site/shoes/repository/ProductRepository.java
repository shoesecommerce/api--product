package com.shoesclick.bff.site.shoes.repository;


import com.shoesclick.bff.site.shoes.entity.Filter;
import com.shoesclick.bff.site.shoes.entity.ListProduct;
import com.shoesclick.bff.site.shoes.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "productClient", url = "${backend.services.productUrl}")
public interface ProductRepository {

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/{id}", produces = "application/json")
    Product findById(@PathVariable("id") Long id);

    @RequestMapping(method = RequestMethod.POST, value = "/api/product/list", produces = "application/json")
    ListProduct filter(Filter filter);

    @RequestMapping(method = RequestMethod.GET, value = "/api/product/list", produces = "application/json")
    ListProduct listAll();

}
