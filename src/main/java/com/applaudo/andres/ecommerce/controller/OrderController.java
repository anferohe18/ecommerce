package com.applaudo.andres.ecommerce.controller;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.dto.checkoutDto.CheckoutDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.dto.orderDto.OrderDtoFull;
import com.applaudo.andres.ecommerce.service.order.OrderService;
import com.applaudo.andres.ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;


    @PostMapping("/adding")
    public ResponseEntity<OrderDtoFull> placeOrder(@RequestParam("paymentId") Integer paymentId){
        OrderDtoFull order = orderService.placeOrder(paymentId);
        return  new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/getting")
    public ResponseEntity<List<OrderDtoFull>> getOrderByUser(){
        return new ResponseEntity<>(orderService.getOrderByUser(),HttpStatus.OK);
    }

    // get orderitems for an order
    @GetMapping("/{id}")
    public ResponseEntity<OrderDtoFull> getOrderById(@PathVariable("id") Integer id){
            OrderDtoFull order = orderService.getOrderById(id);
            return new ResponseEntity<>(order,HttpStatus.OK);

    }


}
