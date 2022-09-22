package com.applaudo.andres.ecommerce.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date date;

    private Double totalPrice;

    @OneToMany()
    private List<OrderItemEntity> orderItems;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String deliveryAddress;




}
