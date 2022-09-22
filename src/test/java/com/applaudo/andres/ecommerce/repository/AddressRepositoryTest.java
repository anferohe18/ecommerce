package com.applaudo.andres.ecommerce.repository;

import com.applaudo.andres.ecommerce.entity.AddressEntity;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("Testing the Address repository")
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    private AddressEntity addressEntity;
    private AddressEntity addressEntity2;
    private UserEntity userEntity;


    @BeforeEach
    void setUp() {
        userEntity = UserEntity.builder()
                .name("Andres ")
                .lastName("Rodriguez")
                .email("andresr427@gmail.com")
                .password("12345")
                .phone("31234567876")
                .address(null)
                .paymentMethod(null)
                .build();
        addressEntity = AddressEntity.builder()
                .id(1)
                .address("calle 17-12-61")
                .user(userEntity)
                .build();
        addressEntity2 = AddressEntity.builder()
                .id(2)
                .address("calle 10-12-61")
                .user(userEntity)
                .build();
    }

    @Test
    void findByAddressTypeAndUser() {
        userRepository.save(userEntity);
        AddressEntity addressSaved = addressRepository.save(addressEntity);
        AddressEntity addressFound = addressRepository.findByAddressTypeAndUser(addressSaved.getAddressType(),userEntity);
        assertThat(addressFound,is(equalTo(addressSaved)));
    }

    @Test
    void findByUser() {
        userRepository.save(userEntity);
        addressRepository.save(addressEntity);
        addressRepository.save(addressEntity2);
        List<AddressEntity> addressEntityList = Arrays.asList(addressEntity,addressEntity2);
        List<AddressEntity> addressesFound = addressRepository.findByUser(userEntity);
        assertThat(addressesFound,hasSize(2));

    }
}