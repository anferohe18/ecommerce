package com.applaudo.andres.ecommerce.service.address;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.entity.AddressEntity;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import com.applaudo.andres.ecommerce.mapper.AddressMapper;
import com.applaudo.andres.ecommerce.mapper.UserMapper;
import com.applaudo.andres.ecommerce.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing de Address service")
class AddressServiceImpTest {

    @Mock
    private AddressRepository addressRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private AddressMapper addressMapper;

    private AddressServiceImp addressService;

    private AddressDto addressDto;
    private UserEntity userEntity;
    private UserDto userDto;

    AddressEntity addressEntity;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity(1,"Andres", "Rodriguez", "andresr427@gmail.com", "12345", "3168442393", null, null);
        userDto = new UserDto(1,"Andres", "Rodriguez", "andresr427@gmail.com", "12345", "3168442393",null, null);
        addressEntity = new AddressEntity(1,"house","calle 17 12-61", userEntity);
        addressDto = new AddressDto(1,"house","calle 17 12-61",null);
        addressService = new AddressServiceImp(addressRepository, addressMapper,userMapper);
    }

    @Test
    void addAddress() {
        when(userMapper.userDtoFull2UserEntityFull(userDto)).thenReturn(userEntity);
        when(addressMapper.addressDto2AddressEntity(any(AddressDto.class))).thenReturn(addressEntity);
        when(addressRepository.save(any(AddressEntity.class))).thenReturn(addressEntity);
        when(addressMapper.addressEntity2AddressDto(any(AddressEntity.class))).thenReturn(addressDto);
        addressDto.setUser(userEntity);
        AddressDto addressSaved = addressService.addAddress(addressDto,userDto);
        assertThat(addressSaved, is(equalTo(addressDto)));
        verify(addressRepository, times(1)).save(any(AddressEntity.class));

    }

    @Test
    void findAddressByTypeAndUser() {
        String type = "house";
        when(userMapper.userDtoFull2UserEntityFull(userDto)).thenReturn(userEntity);
        when(addressRepository.findByAddressTypeAndUser(type,userEntity)).thenReturn(addressEntity);
        when(addressMapper.addressEntity2AddressDto(any(AddressEntity.class))).thenReturn(addressDto);

        AddressDto addressSaved = addressService.findAddressByTypeAndUser(type,userDto);
        assertThat(addressSaved, is(equalTo(addressDto)));
        verify(addressRepository, times(1)).findByAddressTypeAndUser(type,userEntity);

    }

    @Test
    void findAddressByUser() {


        List<AddressEntity> addressListEntity = Collections.singletonList(addressEntity);
        List<AddressDto> addressDtoList = Collections.singletonList(addressDto);
        when(userMapper.userDtoFull2UserEntityFull(any(UserDto.class))).thenReturn(userEntity);
        when(addressRepository.findByUser(any(UserEntity.class))).thenReturn(addressListEntity);
        when(addressMapper.addressEntityList2AddressDtoList(addressListEntity)).thenReturn(addressDtoList);

        List<AddressDto> addressDtoListExpected = addressService.findAddressByUser(userDto);
        assertThat(addressDtoListExpected, is(equalTo(addressDtoList)));
        verify(addressRepository, times(1)).findByUser(userEntity);
    }
}