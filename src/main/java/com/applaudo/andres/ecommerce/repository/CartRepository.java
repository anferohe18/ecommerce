package com.applaudo.andres.ecommerce.repository;

import com.applaudo.andres.ecommerce.entity.CartEntity;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {

    List<CartEntity> findByUser_email(String email);
    List<CartEntity> deleteByUser_email(String email);
}
