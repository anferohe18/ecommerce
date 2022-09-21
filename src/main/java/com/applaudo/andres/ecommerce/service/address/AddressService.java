package com.applaudo.andres.ecommerce.service.address;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;


import java.util.List;

public interface AddressService {
    AddressDto addAddress(AddressDto addressDto, UserDto user);
    AddressDto findAddressByTypeAndUser(String type, UserDto user);
    List<AddressDto> findAddressByUser(UserDto user);
}
