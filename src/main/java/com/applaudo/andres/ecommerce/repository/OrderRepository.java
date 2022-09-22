package com.applaudo.andres.ecommerce.repository;

import com.applaudo.andres.ecommerce.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    List<OrderEntity> findByUser_idOrderByDate(Integer userId);
}
