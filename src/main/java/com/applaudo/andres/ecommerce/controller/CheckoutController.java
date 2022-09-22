package com.applaudo.andres.ecommerce.controller;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.AddToCartDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CartDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CheckoutDto;
import com.applaudo.andres.ecommerce.service.checkout.CheckoutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Create a shopping cart with the selected products.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "cart created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CheckoutDto.class))}),
            @ApiResponse(responseCode = "401", description = "User is not authorized to access the resource",
                    content = @Content)})
    @PostMapping("/")
    public ResponseEntity<CheckoutDto> addToCart(@RequestBody AddToCartDto addToCartDto) {
        CheckoutDto cart = checkoutService.addToCart(addToCartDto);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


    @Operation(summary = "Get the Cart items by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the cart",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CheckoutDto.class))}),
            @ApiResponse(responseCode = "401", description = "User is not authorized to access the resource",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content)})
    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems() {
        CartDto cartDto = checkoutService.listCartItems();
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }


    @Operation(summary = "Update the quantity of the item in your checkout cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CheckoutDto.class))}),
            @ApiResponse(responseCode = "401", description = "User is not authorized to access the resource",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Cart not found",
                    content = @Content)})
    @PutMapping("/")
    public ResponseEntity<CheckoutDto> updateCartItem(@RequestBody @Valid AddToCartDto cartDto) {
        return new ResponseEntity<>(checkoutService.updateCartItem(cartDto), HttpStatus.OK);
    }


    @Operation(summary = "Allows you to choose one of your addresses for the delivery of the order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Established address",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CheckoutDto.class))}),
            @ApiResponse(responseCode = "401", description = "User is not authorized to access the resource",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Cart not found",
                    content = @Content)})
    @PostMapping("/setting-address")
    public ResponseEntity<List<CheckoutDto>> setDeliveryAddress(@RequestParam String typeAddress) {
        return new ResponseEntity<>(checkoutService.setDeliveryAddress(typeAddress), HttpStatus.OK);
    }


    @Operation(summary = "Allows you to modify the delivery address of your order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CheckoutDto.class))}),
            @ApiResponse(responseCode = "401", description = "User is not authorized to access the resource",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Cart not found",
                    content = @Content)})
    @PatchMapping("/")
    public ResponseEntity<CheckoutDto> changeDeliveryAddress(@RequestBody @Valid AddressDto address) {
        return new ResponseEntity<>(checkoutService.updateAddress(address), HttpStatus.OK);
    }


    @Operation(summary = "Allows you to delete a shopping cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cart was deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CheckoutDto.class))}),
            @ApiResponse(responseCode = "401", description = "User is not authorized to access the resource",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Cart not found",
                    content = @Content)})
    @DeleteMapping("/")
    public ResponseEntity<CheckoutDto> deleteCartItem(@RequestParam @Valid Integer itemId) {
        return new ResponseEntity<>(checkoutService.deleteCartItem(itemId), HttpStatus.NO_CONTENT);
    }
}
