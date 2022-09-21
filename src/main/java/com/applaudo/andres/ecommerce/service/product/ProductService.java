package com.applaudo.andres.ecommerce.service.product;

import com.applaudo.andres.ecommerce.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto addProduct(ProductDto productDto);
    ProductDto getProduct(Integer productId);
    List<ProductDto> getProducts();
    ProductDto updateProduct(Integer productId, ProductDto productDto);
    ProductDto deleteProduct(Integer productId);
}
