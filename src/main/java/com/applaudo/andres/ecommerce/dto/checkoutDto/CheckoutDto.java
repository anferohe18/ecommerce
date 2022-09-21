package com.applaudo.andres.ecommerce.dto.checkoutDto;

import com.applaudo.andres.ecommerce.entity.ProductEntity;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutDto {

    private int id;
    @NotNull
    private UserEntity user;

    @NotNull
    private ProductEntity product;
    @NotNull
    private int quantity;

    @NotNull
    private float price;
    private Date date;

    private String deliveryAddress;

    public CheckoutDto(ProductEntity product, Integer quantity, UserEntity user){
        this.product = product;
        this.quantity = quantity;
        this.user = user;
    }
}
