package com.applaudo.andres.ecommerce.service.user;

import com.applaudo.andres.ecommerce.dto.userDto.SigInDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.entity.ProductEntity;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import com.applaudo.andres.ecommerce.exceptions.UserNotFoundException;
import com.applaudo.andres.ecommerce.mapper.UserMapper;
import com.applaudo.andres.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.security.Principal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing the User service")
class UserServiceImpTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    private UserServiceImp userServiceImp;
    UserEntity userEntity;
    UserDto userDto;


    @BeforeEach
    void setUp() {
        userServiceImp = new UserServiceImp(userRepository,userMapper);
        userEntity = UserEntity.builder()
                .id(1)
                .name("Andres")
                .lastName("Rodriguez")
                .email("andresr427@gmail.com")
                .password("12345")
                .phone("3123456543")
                .address(null)
                .paymentMethod(null)
                .build();

        userDto = UserDto.builder()
                .id(1)
                .name("Andres")
                .lastName("Rodriguez")
                .email("andresr427@gmail.com")
                .password("12345")
                .phone("3123456543")
                .address(null)
                .paymentMethod(null)
                .build();
    }


    @Test
    void findUserByEmail() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
        when(userMapper.userEntityFull2UserDtoFull(any(UserEntity.class))).thenReturn(userDto);

        UserDto userDtoFound = userServiceImp.findUserByEmail(userDto.getEmail());
        assertThat(userDtoFound, is(equalTo(userDto)));
        verify(userRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void shouldThrowException_whenUserWasNotFound(){
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        assertThrows(UserNotFoundException.class, ()->{
            userServiceImp.findUserByEmail(anyString());
        });
    }

    @Test
    void updateUser(){
        UserDto userUpdated = UserDto.builder()
                .id(1)
                .name("andrea")
                .lastName("Rodriguez")
                .email("andresr427@gmail.com")
                .password("98765")
                .phone("31231234")
                .address(null)
                .paymentMethod(null)
                .build();

        UserEntity userEntityUpdated = UserEntity.builder()
                .id(1)
                .name("andrea")
                .lastName("Rodriguez")
                .email("andresr427@gmail.com")
                .password("98765")
                .phone("31231234")
                .address(null)
                .paymentMethod(null)
                .build();

        when(userMapper.userDtoFull2UserEntityFull(any(UserDto.class))).thenReturn(userEntityUpdated);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntityUpdated);
        when(userMapper.userEntityFull2UserDtoFull(any(UserEntity.class))).thenReturn(userUpdated);

        UserDto userActual = userServiceImp.updateUser(userUpdated);
        assertThat(userActual, is(equalTo(userUpdated)));

    }

}