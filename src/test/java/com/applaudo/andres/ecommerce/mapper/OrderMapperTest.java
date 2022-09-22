package com.applaudo.andres.ecommerce.mapper;

import com.applaudo.andres.ecommerce.dto.checkoutDto.CheckoutDto;
import com.applaudo.andres.ecommerce.dto.orderDto.OrderDtoFull;
import com.applaudo.andres.ecommerce.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@DisplayName("Testing the Order mapper")
class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;
    private OrderEntity orderEntity;
    private OrderDtoFull orderDto;
    private UserEntity userEntity;
    private OrderItemEntity orderItemEntity;
    private ProductEntity productEntity;
    private List<OrderItemEntity> orderItemEntityList;

    @BeforeEach
    void setUp() {
        userEntity = UserEntity.builder()
                .name("andres")
                .lastName("rodriguez")
                .email("andresr427@gmail.com")
                .phone("312345654")
                .password("1234567")
                .address(null)
                .build();

        productEntity = ProductEntity.builder()
                .name("coffee")
                .availableQuantity(10)
                .price(300.0)
                .build();

        orderItemEntity = OrderItemEntity.builder()
                .quantity(5)
                .price(300.0)
                .date(new Date())
                .product(productEntity)
                .build();

        orderItemEntityList = Arrays.asList(orderItemEntity);

        orderEntity = OrderEntity.builder()
                .date(new Date())
                .totalPrice(1500.0)
                .orderItems(orderItemEntityList)
                .user(userEntity)
                .deliveryAddress("street 6 6-66")
                .build();

        orderDto = OrderDtoFull.builder()
                .date(orderEntity.getDate())
                .totalPrice(1500.0)
                .orderItems(orderItemEntityList)
                .user(userEntity)
                .deliveryAddress("street 6 6-66")
                .build();
    }

    @Test
    void orderDto2OrderEntity() {
        OrderEntity orderEntityMapped = orderMapper.orderDto2OrderEntity(orderDto);
        assertThat(orderEntityMapped.getUser(), is(equalTo(orderDto.getUser())));
        assertThat(orderEntityMapped.getTotalPrice(),is(equalTo(orderDto.getTotalPrice())));
    }

    @Test
    void orderEntity2OrderDto() {
        OrderDtoFull orderDto = orderMapper.orderEntity2OrderDto(orderEntity);
        assertThat(orderDto.getUser(), is(equalTo(orderEntity.getUser())));
        assertThat(orderDto.getTotalPrice(),is(equalTo(orderEntity.getTotalPrice())));

    }


    @Test
    void orderEntityList2OrderDtoList() {
        List<OrderEntity> orderEntityList = new ArrayList<>();
        OrderEntity orderEntity2 = new OrderEntity(2,new Date(),5000.0,orderItemEntityList,userEntity,"street 12 12-12");
        orderEntityList.add(orderEntity2);

        orderEntityList.add(orderEntity);
        List<OrderDtoFull> orderDtoList = orderMapper.orderEntityList2OrderDtoList(orderEntityList);
        assertThat(orderDtoList, hasSize(2));
    }
}