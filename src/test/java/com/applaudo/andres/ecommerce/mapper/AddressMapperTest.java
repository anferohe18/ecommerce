package com.applaudo.andres.ecommerce.mapper;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.entity.AddressEntity;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Testing the Address mapper")
class AddressMapperTest {
    @Autowired
    private AddressMapper addressMapper;

    private AddressDto addressDto;
    private UserEntity userEntity;
    private UserDto userDto;
    private AddressEntity addressEntity;

    @BeforeEach
    void setUp(){
        userEntity = new UserEntity(1,"andres","rodriguez","andresr427@gmail.com","123456","3123454321",null);
        userDto = new UserDto("andres","rodriguez","andresr427@gmail.com","123456","3123454321",null);
        addressDto = new AddressDto(1,"home","street 19 15-30",userEntity);
        addressEntity = new AddressEntity(1,"home","street 19 15-30",userEntity);
    }

    @Test
    void addressDto2AddressEntity() {
        AddressEntity newAddressEntity = addressMapper.addressDto2AddressEntity(addressDto);
        assertThat(newAddressEntity.getUser(),is(equalTo(addressDto.getUser())));
        assertThat(newAddressEntity.getAddress(),is(equalTo(addressDto.getAddress())));
    }

    @Test
    void addressEntity2AddressDto() {
        AddressDto newAddressDto = addressMapper.addressEntity2AddressDto(addressEntity);
        assertThat(newAddressDto.getUser(), is(equalTo(addressEntity.getUser())));
        assertThat(newAddressDto.getAddress(), is(equalTo(addressEntity.getAddress())));
    }

    @Test
    void addressEntityList2AddressDtoList() {
        List<AddressEntity> addressEntityList = new ArrayList<>();
        AddressEntity addressEntity2 = new AddressEntity(2,"ofice","street 2 3-6",userEntity);
        addressEntityList.add(addressEntity2);
        addressEntityList.add(addressEntity);
        List<AddressDto> addressDtoList = addressMapper.addressEntityList2AddressDtoList(addressEntityList);
        assertThat(addressDtoList, hasSize(2));
    }
}