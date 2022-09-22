package com.applaudo.andres.ecommerce.dto.userDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SigInDto {

    private String email;
    private String password;
}
