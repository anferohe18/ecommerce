package com.applaudo.andres.ecommerce.service.order;

import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.dto.orderDto.OrderDtoFull;

import java.util.List;

public interface OrderService {
    OrderDtoFull placeOrder(UserDto user, String address, Integer paymentId);
    List<OrderDtoFull> getOrderByUser(Integer userId);
    OrderDtoFull getOrderById(Integer id);
}
