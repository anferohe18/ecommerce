package com.applaudo.andres.ecommerce.controller;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.AddToCartDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CartDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CheckoutDto;
import com.applaudo.andres.ecommerce.dto.orderDto.OrderDtoFull;
import com.applaudo.andres.ecommerce.service.checkout.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping("/")
    public ResponseEntity<CheckoutDto> addToCart(@RequestBody AddToCartDto addToCartDto){
        CheckoutDto cart = checkoutService.addToCart(addToCartDto);
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(){
        CartDto cartDto = checkoutService.listCartItems();
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @PutMapping("/put")
    public ResponseEntity<CheckoutDto> updateCartItem(@RequestBody @Valid AddToCartDto cartDto){
        return new ResponseEntity<>(checkoutService.updateCartItem(cartDto), HttpStatus.OK);
    }

    @PostMapping("/setting-address")
    public ResponseEntity<List<CheckoutDto>> setDeliveryAddress(@RequestParam String typeAddress){
        return new ResponseEntity<>(checkoutService.setDeliveryAddress(typeAddress),HttpStatus.OK);
    }

    @PatchMapping("/")
    public ResponseEntity<CheckoutDto> changeDeliveryAddress(@RequestBody @Valid AddressDto address){
        return new ResponseEntity<>(checkoutService.updateAddress(address),HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<CheckoutDto> deleteCartItem(@RequestParam @Valid Integer itemId){
        return new ResponseEntity<>(checkoutService.deleteCartItem(itemId),HttpStatus.NO_CONTENT);
    }
}
