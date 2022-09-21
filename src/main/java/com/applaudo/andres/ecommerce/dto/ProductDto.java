package com.applaudo.andres.ecommerce.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {

    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private Integer availableQuantity;
    @NotNull
    private Double price;
}
