package com.shoesclick.api.product.loader;

import com.shoesclick.api.product.entity.Product;
import com.shoesclick.api.product.enums.Category;
import com.shoesclick.api.product.service.ProductService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductLoader extends AbstractImportFileLoader {


    private final ProductService productService;

    public ProductLoader(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void createItem(String linha) {
        String[] campos = linha.split(";");
        Product product = new Product()
                .setCategory(Category.valueOf(campos[0]))
                .setCode(campos[1])
                .setName(campos[2])
                .setDescription(campos[3])
                .setPrice(new BigDecimal(campos[4]));
        productService.save(product);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        importFile("load/product.txt");
    }
}
