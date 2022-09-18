package com.applaudo.andres.ecommerce.dto;

import com.applaudo.andres.ecommerce.entity.CartEntity;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.List;

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
