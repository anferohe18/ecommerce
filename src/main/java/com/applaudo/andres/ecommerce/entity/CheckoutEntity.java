package com.applaudo.andres.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CheckoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;
    private Integer quantity;
    private Date date;
    private String deliveryAddress;

    public CheckoutEntity(ProductEntity product, Integer quantity, UserEntity user){
        this.product = product;
        this.quantity = quantity;
        this.user = user;
        this.date = new Date();
    }
}
