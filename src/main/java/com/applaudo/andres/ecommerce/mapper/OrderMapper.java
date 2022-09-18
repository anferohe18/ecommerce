package com.applaudo.andres.ecommerce.mapper;
import com.applaudo.andres.ecommerce.dto.orderDto.OrderDtoFull;
import com.applaudo.andres.ecommerce.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class OrderMapper {

    public OrderEntity orderDto2OrderEntity(OrderDtoFull orderDto){
        OrderEntity order = new OrderEntity();
        order.setDate(orderDto.getDate());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setOrderItems(orderDto.getOrderItems());
        order.setUser(orderDto.getUser());
        return order;
    }

    public OrderDtoFull orderEntity2OrderDto(OrderEntity orderEntity){
        OrderDtoFull order = new OrderDtoFull();
        order.setId(orderEntity.getId());
        order.setDate(orderEntity.getDate());
        order.setTotalPrice(orderEntity.getTotalPrice());
        order.setOrderItems(orderEntity.getOrderItems());
        order.setUser(orderEntity.getUser());
        return order;
    }

    public List<OrderDtoFull> orderEntityList2OrderDtoList(List<OrderEntity> orderEntities){
        List<OrderDtoFull> orderDto = new ArrayList<>();
        for(OrderEntity order:orderEntities){
            orderDto.add(orderEntity2OrderDto(order));
        }
        return orderDto;
    }
}
