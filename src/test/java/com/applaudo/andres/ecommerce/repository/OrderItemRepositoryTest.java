package com.applaudo.andres.ecommerce.repository;

import com.applaudo.andres.ecommerce.entity.OrderItemEntity;
import com.applaudo.andres.ecommerce.entity.PaymentMethod;
import com.applaudo.andres.ecommerce.entity.ProductEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("Testing the OrderItem repository")
class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;

    private ProductEntity productEntity;
    private OrderItemEntity orderItem;

    @BeforeEach
    void setUp() {
        productEntity = ProductEntity.builder()
                .name("Coffee")
                .availableQuantity(10)
                .price(800.0)
                .build();

        orderItem = OrderItemEntity.builder()
                .quantity(10)
                .price(500.0)
                .date(new Date())
                .product(productEntity)
                .build();
    }

    @Test
    void create_OrderItem(){
        productRepository.save(productEntity);
        OrderItemEntity orderItemEntitySaved = orderItemRepository.save(orderItem);
        assertThat(orderItemEntitySaved,samePropertyValuesAs(orderItem));
    }

    @Test
    void get_OrderItemById(){
        productRepository.save(productEntity);
        OrderItemEntity orderItemEntitySaved = orderItemRepository.save(orderItem);
        Optional<OrderItemEntity> orderItemOptional= orderItemRepository.findById(orderItemEntitySaved.getId());
        OrderItemEntity orderItemFound = orderItemOptional.get();
        assertThat(orderItemFound, samePropertyValuesAs(orderItemEntitySaved));
    }

    @Test
    void delete_OrderItemById(){
        productRepository.save(productEntity);
        OrderItemEntity orderItemEntitySaved = orderItemRepository.save(orderItem);
        orderItemRepository.deleteById(orderItemEntitySaved.getId());
        Optional<OrderItemEntity> orderOptional = orderItemRepository.findById(orderItemEntitySaved.getId());
        assertEquals(orderOptional, Optional.empty());
    }
}