package com.applaudo.andres.ecommerce.controller;

import com.applaudo.andres.ecommerce.dto.userDto.SigInDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.IDToken;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;


import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<SigInDto> logIn(@RequestBody @Valid SigInDto credentials){

        return new ResponseEntity<>(userService.LogInUser(credentials), HttpStatus.ACCEPTED);
    }
}
