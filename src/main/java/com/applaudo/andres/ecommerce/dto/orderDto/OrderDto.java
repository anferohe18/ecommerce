package com.applaudo.andres.ecommerce.dto.orderDto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderDto {

    private Integer id;
    @NotNull
    private Integer userId;
}
