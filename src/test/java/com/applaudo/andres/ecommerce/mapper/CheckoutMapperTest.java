package com.applaudo.andres.ecommerce.mapper;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CheckoutDto;
import com.applaudo.andres.ecommerce.entity.AddressEntity;
import com.applaudo.andres.ecommerce.entity.CheckoutEntity;
import com.applaudo.andres.ecommerce.entity.ProductEntity;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@DisplayName("Testing the Checkout mapper")
class CheckoutMapperTest {
    @Autowired
    private CheckoutMapper checkoutMapper;

    private UserEntity userEntity;
    private ProductEntity productEntity;
    private CheckoutEntity checkoutEntity;
    private CheckoutDto checkoutDto;

    @BeforeEach
    void setUp(){
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

        checkoutEntity = CheckoutEntity.builder()
                .id(1)
                .user(userEntity)
                .product(productEntity)
                .quantity(5)
                .date(new Date())
                .deliveryAddress("street 10 3-45")
                .build();

        checkoutDto = CheckoutDto.builder()
                .id(1)
                .user(userEntity)
                .product(productEntity)
                .quantity(5)
                .date(new Date())
                .deliveryAddress("street 10 3-45")
                .build();
    }


    @Test
    void cartDto2CartEntity() {
        CheckoutEntity checkout = checkoutMapper.cartDto2CartEntity(checkoutDto);
        assertThat(checkout.getUser(),is(equalTo(checkoutDto.getUser())));
        assertThat(checkout.getDeliveryAddress(),is(equalTo(checkoutDto.getDeliveryAddress())));

    }

    @Test
    void cartEntity2CartDto() {
        CheckoutDto checkout = checkoutMapper.cartEntity2CartDto(checkoutEntity);
        assertThat(checkout.getUser(),is(equalTo(checkoutEntity.getUser())));
        assertThat(checkout.getDeliveryAddress(),is(equalTo(checkoutEntity.getDeliveryAddress())));
    }

    @Test
    void cartEntityList2CartDtoList() {
        List<CheckoutEntity> checkoutEntityList = new ArrayList<>();
        CheckoutEntity checkoutEntity2 = new CheckoutEntity(2,userEntity,productEntity,10,new Date(),"street 14 13-21");
        checkoutEntityList.add(checkoutEntity2);
        checkoutEntityList.add(checkoutEntity);
        List<CheckoutDto> checkoutDtoList = checkoutMapper.cartEntityList2CartDtoList(checkoutEntityList);
        assertThat(checkoutDtoList, hasSize(2));
    }
}