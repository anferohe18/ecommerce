package com.applaudo.andres.ecommerce.service.address;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.entity.AddressEntity;
import com.applaudo.andres.ecommerce.mapper.AddressMapper;
import com.applaudo.andres.ecommerce.mapper.UserMapper;
import com.applaudo.andres.ecommerce.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceImp implements AddressService{

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final UserMapper userMapper;

    @Override
    public AddressDto addAddress(AddressDto addressDto, UserDto user) {
        addressDto.setUser(userMapper.userDtoFull2UserEntityFull(user));
        AddressEntity addressEntity = addressRepository.save(addressMapper.addressDto2AddressEntity(addressDto));
        return addressMapper.addressEntity2AddressDto(addressEntity);
    }

    @Override
    public AddressDto findAddressByTypeAndUser(String type, UserDto user) {
        AddressEntity addressEntity = addressRepository.findByAddressTypeAndUser(type,userMapper.userDtoFull2UserEntityFull(user));
        return addressMapper.addressEntity2AddressDto(addressEntity);
    }

    @Override
    public List<AddressDto> findAddressByUser(UserDto user) {
        List<AddressEntity> addressEntities = addressRepository.findByUser(userMapper.userDtoFull2UserEntityFull(user));
        return addressMapper.addressEntityList2AddressDtoList(addressEntities);
    }
}
