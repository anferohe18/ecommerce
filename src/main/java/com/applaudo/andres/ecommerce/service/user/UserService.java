package com.applaudo.andres.ecommerce.service.user;

import com.applaudo.andres.ecommerce.dto.userDto.SigInDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;

import java.security.Principal;

public interface UserService {

    UserDto updateUser(UserDto user);

    UserDto findUserByEmail(String email);

    Object getEmailFromToken(Principal principal);

    UserDto getUserFromToken();

}
