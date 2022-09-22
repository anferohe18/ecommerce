package com.applaudo.andres.ecommerce.dto;

import com.applaudo.andres.ecommerce.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Integer id;
    @NotNull
    private String type;
    @NotNull
    private String address;
    private UserEntity user;
}
