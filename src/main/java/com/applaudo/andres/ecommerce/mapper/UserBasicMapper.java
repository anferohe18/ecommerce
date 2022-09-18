package com.applaudo.andres.ecommerce.mapper;

import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.entity.UserEntity;

public class UserBasicMapper {
    public UserEntity userDto2UserEntity(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setPhone(userDto.getPhone());
        return userEntity;
    }

    public UserDto userEntity2UserDto(UserEntity userEntity){
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setPhone(userEntity.getPhone());
        return userDto;
    }
}
