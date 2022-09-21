package com.applaudo.andres.ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    private List<OrderItemsEntity> orderItems;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String deliveryAddress;




}
