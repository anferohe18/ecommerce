package com.applaudo.andres.ecommerce.mapper;

import com.applaudo.andres.ecommerce.dto.ProductDto;
import com.applaudo.andres.ecommerce.entity.ProductEntity;
import org.springframework.stereotype.Component;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.List;
@Component
public class ProductMapper {

    public ProductEntity productDto2ProductEntity(ProductDto productDto){
        ProductEntity product = new ProductEntity();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setAvailableQuantity(productDto.getAvailableQuantity());
        return product;
    }

    public ProductDto productEntity2ProductDto(ProductEntity productEntity){
        ProductDto product = new ProductDto();
        product.setId(productEntity.getId());
        product.setName(productEntity.getName());
        product.setPrice(productEntity.getPrice());
        product.setAvailableQuantity(productEntity.getAvailableQuantity());
        return product;
    }

    public List<ProductDto> productEntityList2ProductDtoList(List<ProductEntity> productEntities){
        List<ProductDto>productsDto = new ArrayList<>();
        for(ProductEntity product:productEntities){
            productsDto.add(productEntity2ProductDto(product));
        }
        return productsDto;
    }

    public ProductEntity productDtoFull2ProductEntity(ProductDto productDto){
        ProductEntity product = new ProductEntity();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setAvailableQuantity(productDto.getAvailableQuantity());
        return product;
    }
}
