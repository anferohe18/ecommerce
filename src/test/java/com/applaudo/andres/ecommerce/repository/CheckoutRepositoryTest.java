package com.applaudo.andres.ecommerce.repository;

import com.applaudo.andres.ecommerce.entity.CheckoutEntity;
import com.applaudo.andres.ecommerce.entity.ProductEntity;
import com.applaudo.andres.ecommerce.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("Testing the Checkout repository")
class CheckoutRepositoryTest {

    @Autowired
    private CheckoutRepository checkoutRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    private CheckoutEntity checkoutEntity;
    private UserEntity userEntity;
    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        productEntity = ProductEntity.builder()
                .name("coffee")
                .availableQuantity(10)
                .price(500.0)
                .build();

        userEntity = UserEntity.builder()
                .name("andres")
                .lastName("rodriguez")
                .email("andresr427@gmail.com")
                .password("123456")
                .phone("3123456543")
                .address(null)
                .paymentMethod(null)
                .build();

        checkoutEntity = CheckoutEntity.builder()
                .user(userEntity)
                .quantity(5)
                .product(productEntity)
                .date(new Date())
                .deliveryAddress("street 17 12-61")
                .build();
    }

    @Test
    void findAllByUser_email() {
        userRepository.save(userEntity);
        productRepository.save(productEntity);
        checkoutRepository.save(checkoutEntity);

        List<CheckoutEntity> checkoutList = checkoutRepository.findAllByUser_email(userEntity.getEmail());
        assertThat(checkoutList,hasSize(1));
        assertThat(checkoutList,contains(checkoutEntity));
    }

    @Test
    void findByUser_email() {
        userRepository.save(userEntity);
        productRepository.save(productEntity);
        checkoutRepository.save(checkoutEntity);

        CheckoutEntity checkout = checkoutRepository.findByUser_email(userEntity.getEmail());
        assertThat(checkout, samePropertyValuesAs(checkoutEntity));
    }

    @Test
    void deleteByUser_email() {
        userRepository.save(userEntity);
        productRepository.save(productEntity);
        checkoutRepository.save(checkoutEntity);

        List<CheckoutEntity> checkoutList = checkoutRepository.deleteByUser_email(userEntity.getEmail());
        assertThat(checkoutList,hasSize(1));
        assertThat(checkoutList,contains(checkoutEntity));


    }
}