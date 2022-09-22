package com.applaudo.andres.ecommerce.mapper;

import com.applaudo.andres.ecommerce.dto.checkoutDto.CheckoutDto;
import com.applaudo.andres.ecommerce.entity.CheckoutEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CheckoutMapper {

    public CheckoutEntity cartDto2CartEntity(CheckoutDto cartDto){
        CheckoutEntity cart = new CheckoutEntity();
        cart.setUser(cartDto.getUser());
        cart.setProduct(cartDto.getProduct());
        cart.setQuantity(cartDto.getQuantity());
        cart.setDate(cartDto.getDate());
        cart.setDeliveryAddress(cartDto.getDeliveryAddress());
        return cart;
    }

    public CheckoutDto cartEntity2CartDto(CheckoutEntity checkoutEntity){
        CheckoutDto cart = new CheckoutDto();
        cart.setId(checkoutEntity.getId());
        cart.setUser(checkoutEntity.getUser());
        cart.setProduct(checkoutEntity.getProduct());
        cart.setQuantity(checkoutEntity.getQuantity());
        cart.setDate(checkoutEntity.getDate());
        cart.setDeliveryAddress(checkoutEntity.getDeliveryAddress());
        return cart;
    }

    public List<CheckoutDto> cartEntityList2CartDtoList(List<CheckoutEntity> cartEntities){
        List<CheckoutDto>cartDto = new ArrayList<>();
        for(CheckoutEntity car:cartEntities){
            cartDto.add(cartEntity2CartDto(car));
        }
        return cartDto;
    }
}
