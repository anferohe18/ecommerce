package com.applaudo.andres.ecommerce.repository;

import com.applaudo.andres.ecommerce.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("Testing the Product repository")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        productEntity = ProductEntity.builder()
                .name("coffee")
                .price(300.0)
                .availableQuantity(20)
                .build();
    }

    @Test
    void create_Product(){
        ProductEntity productSaved = productRepository.save(productEntity);
        assertThat(productSaved,samePropertyValuesAs(productEntity));
    }

    @Test
    void get_Product(){
        ProductEntity productSaved = productRepository.save(productEntity);
        Optional<ProductEntity> productOptional = productRepository.findById(productSaved.getId());
        ProductEntity productFound = productOptional.get();
        assertThat(productFound,samePropertyValuesAs(productSaved));
    }

    @Test
    void delete_Product(){
        ProductEntity productSaved = productRepository.save(productEntity);
        productRepository.deleteById(productSaved.getId());
        Optional<ProductEntity> productOptional = productRepository.findById(productSaved.getId());
        assertEquals(productOptional, Optional.empty());

    }

}