package com.applaudo.andres.ecommerce.repository;

import com.applaudo.andres.ecommerce.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("Testing the User repository")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = UserEntity.builder()
                .name("andres")
                .lastName("rodriguez")
                .email("andresr427@gmail.com")
                .password("123456")
                .phone("3121234567")
                .address(null)
                .paymentMethod(null)
                .build();
    }

    @Test
    void existsByEmail() {
        userRepository.save(userEntity);
        boolean userExists = userRepository.existsByEmail(userEntity.getEmail());
        assertThat(userExists,is(equalTo(true)));
    }

    @Test
    void findByEmail() {
        userRepository.save(userEntity);
        UserEntity userFound = userRepository.findByEmail(userEntity.getEmail());
        assertThat(userFound,samePropertyValuesAs(userEntity));
    }
}