package com.applaudo.andres.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthenticationFailException extends IllegalArgumentException {

    public AuthenticationFailException(String msg) {
        super(msg);
    }
}
