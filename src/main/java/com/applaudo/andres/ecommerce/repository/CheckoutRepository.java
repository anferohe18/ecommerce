package com.applaudo.andres.ecommerce.repository;

import com.applaudo.andres.ecommerce.entity.CheckoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckoutRepository extends JpaRepository<CheckoutEntity, Integer> {

    List<CheckoutEntity> findAllByUser_email(String email);
    CheckoutEntity findByUser_email(String email);
    List<CheckoutEntity> deleteByUser_email(String email);
}
