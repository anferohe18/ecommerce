package com.applaudo.andres.ecommerce.mapper;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.dto.ProductDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.entity.AddressEntity;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringBootTest
@DisplayName("Testing the User mapper")
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private UserEntity userEntity;
    private UserDto userDto;
    private AddressEntity addressEntity;
    private List<AddressEntity> addressEntityList;



    @BeforeEach
    void setUp() {
        addressEntity = AddressEntity.builder()
                .id(1)
                .addressType("home")
                .address("street 19 15-30")
                .user(userEntity)
                .build();

        addressEntityList = Arrays.asList(addressEntity);

        userEntity = UserEntity.builder()
                .name("andres")
                .lastName("rodriguez")
                .email("andresr427@gmail.com")
                .phone("312345654")
                .password("1234567")
                .address(addressEntityList)
                .build();

        userDto = UserDto.builder()
                .name("andres")
                .lastName("rodriguez")
                .email("andresr427@gmail.com")
                .phone("312345654")
                .password("1234567")
                .address(addressEntityList)
                .build();
    }

    @Test
    void userDto2UserEntity() {
        UserEntity user = userMapper.userDto2UserEntity(userDto);
        assertThat(user.getName(),is(equalTo(userDto.getName())));
        assertThat(user.getEmail(),is(equalTo(userDto.getEmail())));

    }

    @Test
    void userEntity2UserDto() {
        UserDto user = userMapper.userEntity2UserDto(userEntity);
        assertThat(user.getName(),is(equalTo(userEntity.getName())));
        assertThat(user.getEmail(),is(equalTo(userEntity.getEmail())));
    }

    @Test
    void userDtoFull2UserEntityFull() {
        UserEntity userEntityFull = userMapper.userDtoFull2UserEntityFull(userDto);
        assertThat(userEntityFull.getAddress(),is(equalTo(userDto.getAddress())));

    }

    @Test
    void userEntityFull2UserDtoFull() {
        UserDto userDtoFull = userMapper.userEntityFull2UserDtoFull(userEntity);
        assertThat(userDtoFull.getAddress(),is(equalTo(userEntity.getAddress())));
    }
}