package com.applaudo.andres.ecommerce.dto;

import com.applaudo.andres.ecommerce.entity.UserEntity;
import lombok.Data;

@Data
public class AddressDto {

    private Integer id;
    private String type;
    private String address;
    private UserEntity user;
}
