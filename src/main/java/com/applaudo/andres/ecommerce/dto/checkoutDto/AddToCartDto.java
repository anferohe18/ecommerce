package com.applaudo.andres.ecommerce.dto.checkoutDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartDto {

    private Integer id;
    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;

}
