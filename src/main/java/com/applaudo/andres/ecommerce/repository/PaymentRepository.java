package com.applaudo.andres.ecommerce.repository;

import com.applaudo.andres.ecommerce.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentMethod, Integer> {
}
