package com.applaudo.andres.ecommerce.service.order;

import com.applaudo.andres.ecommerce.dto.AddressDto;
import com.applaudo.andres.ecommerce.dto.userDto.UserDto;
import com.applaudo.andres.ecommerce.dto.orderDto.OrderDtoFull;

import java.util.List;

public interface OrderService {
    OrderDtoFull placeOrder(Integer paymentId);
    List<OrderDtoFull> getOrderByUser();
    OrderDtoFull getOrderById(Integer id);
}
