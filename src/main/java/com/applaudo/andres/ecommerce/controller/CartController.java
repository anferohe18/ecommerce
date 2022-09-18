package com.applaudo.andres.ecommerce.controller;

import com.applaudo.andres.ecommerce.dto.ProductDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.dto.cartDto.AddToCartDto;
import com.applaudo.andres.ecommerce.dto.cartDto.CartDto;
import com.applaudo.andres.ecommerce.dto.cartDto.CartDto2;
import com.applaudo.andres.ecommerce.service.cart.CartService;
import com.applaudo.andres.ecommerce.service.product.ProductService;
import com.applaudo.andres.ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<CartDto2> addToCart(@RequestBody AddToCartDto addToCartDto,@RequestParam("id")Integer id){

        CartDto2 cart = cartService.addToCart(addToCartDto, id);
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam Integer idUser){
        UserDto user = userService.findUserById(idUser);
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @PutMapping("/put")
    public ResponseEntity<CartDto2> updateCartItem(@RequestBody @Valid AddToCartDto cartDto, @RequestParam("id")Integer id){
        UserDto user = userService.findUserById(id);
        ProductDto product = productService.getProduct(cartDto.getProductId());
        return new ResponseEntity<>(cartService.updateCartItem(cartDto,user,product), HttpStatus.OK);
    }

        @DeleteMapping("/")
    public ResponseEntity<CartDto2> deleteCartItem(@RequestParam("id")Integer userId){
        return new ResponseEntity<>(cartService.deleteCartItem(userId),HttpStatus.NO_CONTENT);
    }
}
