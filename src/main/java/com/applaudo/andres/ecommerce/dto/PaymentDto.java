package com.applaudo.andres.ecommerce.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class PaymentDto {
    private int id;
    @NotNull
    private String paymentMethod;

}
