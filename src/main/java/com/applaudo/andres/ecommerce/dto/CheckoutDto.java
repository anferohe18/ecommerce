package com.applaudo.andres.ecommerce.dto;

import lombok.Data;

@Data
public class CheckoutDto {
    private String productName;
    private Integer  quantity;
    private Double price;
    private Integer productId;
    private Integer userId;
}
