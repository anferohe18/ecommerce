package com.applaudo.andres.ecommerce.dto.orderDto;

import com.applaudo.andres.ecommerce.entity.OrderEntity;
import com.applaudo.andres.ecommerce.entity.ProductEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderItemsDto {

    private Integer id;
    private @NotNull Double price;
    private @NotNull Integer quantity;
    private @NotNull OrderEntity orderId;
    private @NotNull ProductEntity productId;
}
