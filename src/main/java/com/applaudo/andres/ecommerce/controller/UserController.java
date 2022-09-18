package com.applaudo.andres.ecommerce.controller;

import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable(value = "id")Integer id){
        return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.NO_CONTENT);
    }
}
