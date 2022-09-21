package com.applaudo.andres.ecommerce.service.user;

import com.applaudo.andres.ecommerce.dto.userDto.SigInDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import com.applaudo.andres.ecommerce.exceptions.AuthenticationFailException;
import com.applaudo.andres.ecommerce.mapper.UserMapper;
import com.applaudo.andres.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.IDToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public SigInDto LogInUser(SigInDto credentials) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        UserEntity entity = userRepository.findByEmail(credentials.getEmail());
        if (getEmailFromToken(principal).equals(entity.getEmail())) {
            return credentials;
        } else {
            throw new AuthenticationFailException("failed authentication");
        }
    }


    @Override
    public UserDto updateUser(UserDto user) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setPhone(user.getPhone());
            userDto.setPassword(user.getPassword());
            userDto.setAddress(user.getAddress());
            userDto.setPaymentMethod(user.getPaymentMethod());
            UserEntity userEntity = userMapper.userDtoFull2UserEntityFull(userDto);
            UserEntity entitySaved = userRepository.save(userEntity);
            UserDto userFinal = userMapper.userEntityFull2UserDtoFull(entitySaved);
            return userFinal;
    }

    @Override
    public UserDto findUserByEmail(String email) {
        boolean optionalUser = userRepository.existsByEmail(email);
        if(optionalUser == true){
            UserEntity userEntity = userRepository.findByEmail(email);
            UserDto userDto = userMapper.userEntityFull2UserDtoFull(userEntity);
            return userDto;
        }
        else{
            throw new EntityNotFoundException();
        }
    }
    @Override
    public  Object getEmailFromToken(Principal principal){
        JwtAuthenticationToken jwtToken = (JwtAuthenticationToken) principal;
        return jwtToken.getToken().getClaims().get("email");

    }
}
