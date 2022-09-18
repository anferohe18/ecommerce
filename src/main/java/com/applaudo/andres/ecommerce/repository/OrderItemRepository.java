package com.applaudo.andres.ecommerce.repository;

import com.applaudo.andres.ecommerce.entity.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemsEntity, Integer> {
}
