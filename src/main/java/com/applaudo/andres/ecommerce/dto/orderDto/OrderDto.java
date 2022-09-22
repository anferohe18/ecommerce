package com.applaudo.andres.ecommerce.dto.orderDto;

import com.applaudo.andres.ecommerce.entity.UserEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderDto {

    private Integer id;
    @NotNull
    private UserEntity userId;
    @NotNull
    private String deliveryAddress;
}
