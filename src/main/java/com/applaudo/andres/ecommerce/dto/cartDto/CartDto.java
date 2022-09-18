package com.applaudo.andres.ecommerce.dto.cartDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CartDto {

    private List<CartItemDto> cartItems;
    private Double totalCost;

}
