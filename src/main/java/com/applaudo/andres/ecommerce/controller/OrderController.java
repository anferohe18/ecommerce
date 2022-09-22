package com.applaudo.andres.ecommerce.controller;

import com.applaudo.andres.ecommerce.dto.orderDto.OrderDtoFull;
import com.applaudo.andres.ecommerce.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Allows you to start an order, with the products already chosen")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDtoFull.class))}),
            @ApiResponse(responseCode = "401", description = "User is not authorized to access the resource",
                    content = @Content)})
    @PostMapping("/adding")
    public ResponseEntity<OrderDtoFull> placeOrder(@RequestParam("paymentId") Integer paymentId) {
        OrderDtoFull order = orderService.placeOrder(paymentId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @Operation(summary = "Allows you to find the orders of a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDtoFull.class))}),
            @ApiResponse(responseCode = "401", description = "User is not authorized to access the resource",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content)})
    @GetMapping("/getting")
    public ResponseEntity<List<OrderDtoFull>> getOrderByUser() {
        return new ResponseEntity<>(orderService.getOrderByUser(), HttpStatus.OK);
    }


    @Operation(summary = "Allows you to find the orders of a user by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDtoFull.class))}),
            @ApiResponse(responseCode = "401", description = "User is not authorized to access the resource",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Order not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<OrderDtoFull> getOrderById(@PathVariable("id") Integer id) {
        OrderDtoFull order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);

    }
}
