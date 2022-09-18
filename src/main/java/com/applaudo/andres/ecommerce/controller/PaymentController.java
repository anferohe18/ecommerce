package com.applaudo.andres.ecommerce.controller;

import com.applaudo.andres.ecommerce.dto.PaymentDto;
import com.applaudo.andres.ecommerce.dto.ProductDto;
import com.applaudo.andres.ecommerce.entity.PaymentMethod;
import com.applaudo.andres.ecommerce.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/add")
    public ResponseEntity<PaymentMethod> addPaymentMethod(@RequestBody String paymentName ){
        return new ResponseEntity<>(paymentService.createPaymentMethod(paymentName), HttpStatus.CREATED);
    }

}
