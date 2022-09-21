package com.applaudo.andres.ecommerce.mapper;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.entity.AddressEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressMapper {
    public AddressEntity addressDto2AddressEntity(AddressDto addressDto){
        AddressEntity address = new AddressEntity();
        address.setAddressType(addressDto.getType());
        address.setAddress(addressDto.getAddress());
        address.setUser(addressDto.getUser());
        return address;
    }

    public AddressDto addressEntity2AddressDto(AddressEntity addressEntity){
        AddressDto address = new AddressDto();
        address.setId(addressEntity.getId());
        address.setType(addressEntity.getAddressType());
        address.setAddress(addressEntity.getAddress());
        address.setUser(addressEntity.getUser());
        return address;
    }

    public List<AddressDto> addressEntityList2AddressDtoList(List<AddressEntity> addressEntities){
        List<AddressDto>addressDto = new ArrayList<>();
        for(AddressEntity address:addressEntities){
            addressDto.add(addressEntity2AddressDto(address));
        }
        return addressDto;
    }

}
