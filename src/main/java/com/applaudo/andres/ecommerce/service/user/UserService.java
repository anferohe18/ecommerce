package com.applaudo.andres.ecommerce.service.user;

import com.applaudo.andres.ecommerce.dto.userDto.UserDto;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto deleteUser(Integer id);

    UserDto updateUser(UserDto user);

    UserDto findUserById(Integer id);

}
