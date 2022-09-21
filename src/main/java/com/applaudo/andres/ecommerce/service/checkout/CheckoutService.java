package com.applaudo.andres.ecommerce.service.checkout;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.AddToCartDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CartDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CheckoutDto;


import java.util.List;

public interface CheckoutService {

    CheckoutDto addToCart(AddToCartDto cartDto);

    CartDto listCartItems();

    CheckoutDto updateCartItem(AddToCartDto cartDto);

    CheckoutDto deleteCartItem(Integer itemId);

    List<CheckoutDto>setDeliveryAddress(String typeAddress);

    CheckoutDto updateAddress(AddressDto address);

    List<CheckoutDto> deleteUserCartItems(UserDto user);
}
