package com.applaudo.andres.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice()
public class RestExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(CartItemNotExistException.class)
    public ResponseEntity<Object> handleCartItemNotExistException(CartItemNotExistException msg) {
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, msg.getMessage()));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(OrderNotFoundException msg){
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, msg.getMessage()));
    }
    @ExceptionHandler(AuthenticationFailException.class)
    public ResponseEntity<Object> handleAuthenticationFailException(AuthenticationFailException msg){
        return buildResponseEntity(new ErrorResponse(HttpStatus.FORBIDDEN, msg.getMessage()));
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<Object> handlePaymentNotFoundException(PaymentNotFoundException msg){
        return buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST,msg.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException msg){
        return buildResponseEntity(new ErrorResponse(HttpStatus.NOT_FOUND, msg.getMessage()));
    }

}
