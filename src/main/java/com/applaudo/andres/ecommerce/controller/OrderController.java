package com.applaudo.andres.ecommerce.controller;

import com.applaudo.andres.ecommerce.dto.CheckoutDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.dto.orderDto.OrderDtoFull;
import com.applaudo.andres.ecommerce.service.order.OrderService;
import com.applaudo.andres.ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;


    @PostMapping("/adding")
    public ResponseEntity<OrderDtoFull> placeOrder(@RequestParam("userId") Integer userId, @RequestParam("paymentId") Integer paymentId, @RequestBody String address ){
        UserDto user = userService.findUserById(userId);
        OrderDtoFull order = orderService.placeOrder(user, address, paymentId);
        return  new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/get")
    public List<OrderDtoFull> getOrderByUser(@RequestParam("userId") Integer userId){
        List<OrderDtoFull> orderDtoList = orderService.getOrderByUser(userId);
        return orderDtoList;
    }

    // get orderitems for an order
    @GetMapping("/{id}")
    public ResponseEntity<OrderDtoFull> getOrderById(@PathVariable("id") Integer id){
            OrderDtoFull order = orderService.getOrderById(id);
            return new ResponseEntity<>(order,HttpStatus.OK);

    }

}
