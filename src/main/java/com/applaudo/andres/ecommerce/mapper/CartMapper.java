package com.applaudo.andres.ecommerce.mapper;

import com.applaudo.andres.ecommerce.dto.cartDto.CartDto2;
import com.applaudo.andres.ecommerce.entity.CartEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CartMapper {

    public CartEntity cartDto2CartEntity(CartDto2 cartDto){
        CartEntity cart = new CartEntity();
        cart.setUser(cartDto.getUser());
        cart.setProduct(cartDto.getProduct());
        cart.setQuantity(cartDto.getQuantity());
        cart.setDate(cartDto.getDate());
        return cart;
    }

    public CartDto2 cartEntity2CartDto(CartEntity cartEntity){
        CartDto2 cart = new CartDto2();
        cart.setId(cartEntity.getId());
        cart.setUser(cartEntity.getUser());
        cart.setProduct(cartEntity.getProduct());
        cart.setQuantity(cartEntity.getQuantity());
        cart.setDate(cartEntity.getDate());
        return cart;
    }

    public List<CartDto2> cartEntityList2CartDtoList(List<CartEntity> cartEntities){
        List<CartDto2>cartDto = new ArrayList<>();
        for(CartEntity car:cartEntities){
            cartDto.add(cartEntity2CartDto(car));
        }
        return cartDto;
    }
}
