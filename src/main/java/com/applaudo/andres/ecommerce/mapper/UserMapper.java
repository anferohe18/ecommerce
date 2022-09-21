package com.applaudo.andres.ecommerce.mapper;

import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.entity.PaymentMethod;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity userDto2UserEntity(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
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

    public UserEntity userDtoFull2UserEntityFull(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setName(userDto.getName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setPhone(userDto.getPhone());
        userEntity.setAddress(userDto.getAddress());
        userEntity.setPaymentMethod( userDto.getPaymentMethod());
        return userEntity;
    }

    public UserDto userEntityFull2UserDtoFull(UserEntity userEntity){
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setPhone(userEntity.getPhone());
        userDto.setAddress(userEntity.getAddress());
        userDto.setPaymentMethod(userEntity.getPaymentMethod());
        return userDto;
    }
}
