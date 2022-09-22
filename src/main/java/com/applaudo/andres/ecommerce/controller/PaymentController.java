package com.applaudo.andres.ecommerce.controller;


import com.applaudo.andres.ecommerce.dto.PaymentDto;
import com.applaudo.andres.ecommerce.entity.PaymentMethod;
import com.applaudo.andres.ecommerce.service.payment.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Allows you to find the orders of a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "PaymentMethod added",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaymentDto.class))}),
            @ApiResponse(responseCode = "401", description = "User is not authorized to access the resource",
                    content = @Content)})
    @PostMapping("/")
    public ResponseEntity<PaymentMethod> addPaymentMethod(@RequestBody String paymentName) {
        return new ResponseEntity<>(paymentService.createPaymentMethod(paymentName), HttpStatus.CREATED);
    }

}
