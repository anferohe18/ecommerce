package com.applaudo.andres.ecommerce.repository;

import com.applaudo.andres.ecommerce.entity.AddressEntity;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
    AddressEntity findByAddressTypeAndUser(String type, UserEntity user);
    List<AddressEntity> findByUser(UserEntity user);
}
