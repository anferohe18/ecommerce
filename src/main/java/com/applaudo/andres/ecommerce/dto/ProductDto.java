package com.applaudo.andres.ecommerce.dto;

import com.applaudo.andres.ecommerce.entity.CheckoutEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private Integer availableQuantity;
    @NotNull
    private Double price;

    private List<CheckoutEntity> carts;

    public ProductDto(Integer id, String name, Integer availableQuantity, Double price){
        this.id = id;
        this.name = name;
        this.availableQuantity = availableQuantity;
        this.price = price;
    }
}
