package com.applaudo.andres.ecommerce.service.user;

import com.applaudo.andres.ecommerce.dto.userDto.UserDto;;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import com.applaudo.andres.ecommerce.mapper.UserMapper;
import com.applaudo.andres.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto createUser(UserDto user) {
        /*
        UserEntity userEntity = userMapper.userDto2UserEntity(user);
        UserEntity userSaved = userRepository.save(userEntity);
        return userMapper.userEntity2UserDto(userSaved);

         */
        UserEntity userEntity = userMapper.userDto2UserEntity(user);
        boolean userExist = userRepository.existsByEmail(user.getEmail());
        if (userExist) {
            return null;
        } else {
            UserEntity entitySaved = userRepository.save(userEntity);
            UserDto result = userMapper.userEntity2UserDto(entitySaved);
            return result;
        }
    }

    @Override
    public UserDto deleteUser(Integer id) {
        Optional<UserEntity> optionalProduct = userRepository.findById(id);
        if(optionalProduct.isPresent()){
            UserEntity userEntity = optionalProduct.get();
            UserDto userDto = userMapper.userEntity2UserDto(userEntity);
            userRepository.deleteById(id);
            return userDto;
        }
        else{
            throw new EntityNotFoundException();
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
            UserDto userFinal = userMapper.userEntity2UserDto(entitySaved);
            return userFinal;
    }

    @Override
    public UserDto findUserById(Integer id) {
        Optional<UserEntity> optionalProduct = userRepository.findById(id);
        if(optionalProduct.isPresent()){
            UserEntity userEntity = optionalProduct.get();
            UserDto userDto = userMapper.userEntity2UserDto(userEntity);
            return userDto;
        }
        else{
            throw new EntityNotFoundException();
        }
    }
}
