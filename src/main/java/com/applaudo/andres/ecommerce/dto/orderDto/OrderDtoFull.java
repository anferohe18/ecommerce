package com.applaudo.andres.ecommerce.dto.orderDto;

import com.applaudo.andres.ecommerce.dto.cartDto.CartDto;
import com.applaudo.andres.ecommerce.entity.OrderItemsEntity;
import com.applaudo.andres.ecommerce.entity.PaymentMethod;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDtoFull {

    private Integer id;

    private Date date;

    private Double totalPrice;

    private List<OrderItemsEntity> orderItems;

    private UserEntity user;

}
