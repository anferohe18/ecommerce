package com.applaudo.andres.ecommerce.dto.checkoutDto;

import com.applaudo.andres.ecommerce.entity.CheckoutEntity;
import com.applaudo.andres.ecommerce.entity.ProductEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartItemDto {

    private Integer ID;
    @NotNull
    private Integer quantity;
    @NotNull
    private ProductEntity product;

    public CartItemDto(CheckoutEntity entity){
        this.setID(entity.getId());
        this.setQuantity(entity.getQuantity());
        this.setProduct(entity.getProduct());
    }
}
