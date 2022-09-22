package com.applaudo.andres.ecommerce.repository;

import com.applaudo.andres.ecommerce.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("Testing the Order repository")
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    private OrderEntity orderEntity;
    private UserEntity userEntity;
    private ProductEntity productEntity;
    private AddressEntity addressEntity;
    private CheckoutEntity checkoutEntity;
    private OrderItemEntity orderItemEntity;
    private List<OrderItemEntity> orderItemEntityList;

    @BeforeEach
    void setUp() {
        productEntity = ProductEntity.builder()
                .name("coffee")
                .availableQuantity(30)
                .price(500.0)
                .build();

        userEntity = UserEntity.builder()
                .name("andres")
                .lastName("rodriguez")
                .email("andresr427@gmail.com")
                .password("123456")
                .phone("3121234567")
                .address(null)
                .paymentMethod(null)
                .build();

        addressEntity = AddressEntity.builder()
                .user(userEntity)
                .addressType("house")
                .address("street 10 1-10")
                .build();

        checkoutEntity = CheckoutEntity.builder()
                .user(userEntity)
                .product(productEntity)
                .date(new Date())
                .quantity(10)
                .deliveryAddress(addressEntity.getAddress())
                .build();

        orderItemEntity = OrderItemEntity.builder()
                .quantity(10)
                .price(500.0)
                .date(new Date())
                .product(productEntity)
                .build();

        orderItemEntityList = Arrays.asList(orderItemEntity);
        orderEntity = OrderEntity.builder()
                .date(new Date())
                .totalPrice(5000.0)
                .orderItems(orderItemEntityList)
                .user(userEntity)
                .deliveryAddress(addressEntity.getAddress())
                .build();
    }

    @Test
    void findByUser_idOrderByDate() {
        productRepository.save(productEntity);
        orderItemRepository.save(orderItemEntity);
        UserEntity userSaved = userRepository.save(userEntity);
        orderRepository.save(orderEntity);

        List<OrderEntity> orderEntityList = orderRepository.findByUser_idOrderByDate(userSaved.getId());
        assertThat(orderEntityList,hasSize(1));
        assertThat(orderEntityList, contains(orderEntity));

    }

    @Test
    void should_CreateOrder(){
        OrderEntity orderSaved = orderRepository.save(orderEntity);
        assertThat(orderSaved, is(equalTo(orderEntity)));
    }

    @Test
    void should_DeleteOrder(){
        OrderEntity orderSaved = orderRepository.save(orderEntity);
        orderRepository.delete(orderSaved);
        Optional<OrderEntity> orderEntity = orderRepository.findById(orderSaved.getId());
        assertEquals(orderEntity, Optional.empty());
    }
}