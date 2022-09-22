package com.applaudo.andres.ecommerce.mapper;

import com.applaudo.andres.ecommerce.dto.ProductDto;
import com.applaudo.andres.ecommerce.entity.CheckoutEntity;
import com.applaudo.andres.ecommerce.entity.ProductEntity;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@DisplayName("Testing the product mapper")
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    private ProductEntity productEntity;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        productEntity = ProductEntity.builder()
                .name("coffee")
                .availableQuantity(10)
                .price(300.0)
                .build();

        productDto = ProductDto.builder()
                .name("coffee")
                .availableQuantity(10)
                .price(300.0)
                .build();
    }

    @Test
    void productDto2ProductEntity() {
        ProductEntity product = productMapper.productDto2ProductEntity(productDto);
        assertThat(product.getName(),is(equalTo(productDto.getName())));
        assertThat(product.getPrice(),is(equalTo(productDto.getPrice())));

    }

    @Test
    void productEntity2ProductDto() {
        ProductDto product = productMapper.productEntity2ProductDto(productEntity);
        assertThat(product.getName(),is(equalTo(productEntity.getName())));
        assertThat(product.getPrice(),is(equalTo(productEntity.getPrice())));
    }

    @Test
    void productEntityList2ProductDtoList() {
        List<ProductEntity> productEntityList = new ArrayList<>();
        ProductEntity productEntity2 = new ProductEntity(2,"water",15,500.0);
        productEntityList.add(productEntity2);
        productEntityList.add(productEntity);
        List<ProductDto> productDtoList = productMapper.productEntityList2ProductDtoList(productEntityList);
        assertThat(productDtoList, hasSize(2));

    }

    @Test
    void productDtoFull2ProductEntity() {
        UserEntity user = new UserEntity("andres", "rodriguez", "andres@mail.com","12345", "12345678");
        CheckoutEntity checkoutEntity = new CheckoutEntity(productEntity,5,user);
        List<CheckoutEntity> checkoutEntityList = Arrays.asList(checkoutEntity);
        ProductEntity productEntityFull = new ProductEntity(3,"rice",20,450.0,checkoutEntityList);
        ProductDto productDtoFull = new ProductDto(3,"rice",20,450.0,checkoutEntityList);

        ProductEntity product = productMapper.productDtoFull2ProductEntity(productDtoFull);
        assertThat(product.getPrice(),is(equalTo(productDtoFull.getPrice())));
        assertThat(product.getName(),is(equalTo(productDtoFull.getName())));
    }
}