package com.applaudo.andres.ecommerce.service.cart;

import com.applaudo.andres.ecommerce.dto.ProductDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.dto.cartDto.AddToCartDto;
import com.applaudo.andres.ecommerce.dto.cartDto.CartDto;
import com.applaudo.andres.ecommerce.dto.cartDto.CartDto2;
import com.applaudo.andres.ecommerce.entity.CartEntity;

import java.util.List;

public interface CartService {

    CartDto2 addToCart(AddToCartDto cartDto, Integer id);

    CartDto listCartItems(UserDto user);

    CartDto2 updateCartItem(AddToCartDto cartDto, UserDto user, ProductDto product);

    CartDto2 deleteCartItem(Integer id);

    CartDto2 deleteCartItems(Integer userId);

    List<CartDto2> deleteUserCartItems(UserDto user);
}
