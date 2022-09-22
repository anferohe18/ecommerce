package com.applaudo.andres.ecommerce.dto.orderDto;

import com.applaudo.andres.ecommerce.entity.OrderItemEntity;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDtoFull {

    private Integer id;

    private Date date;

    private Double totalPrice;

    private List<OrderItemEntity> orderItems;

    private UserEntity user;

    private String deliveryAddress;

}
