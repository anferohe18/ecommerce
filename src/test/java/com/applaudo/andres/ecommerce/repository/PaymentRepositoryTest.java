package com.applaudo.andres.ecommerce.repository;

import com.applaudo.andres.ecommerce.entity.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("Testing the Payment repository")
class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    private PaymentMethod paymentMethod;

    @BeforeEach
    void setUp() {
        paymentMethod = PaymentMethod.builder()
                .methodName("cash")
                .build();
    }

    @Test
    void shouldCreate_PaymentMethod(){
        PaymentMethod paymentSaved = paymentRepository.save(paymentMethod);
        assertThat(paymentSaved, is(equalTo(paymentMethod)));
    }

    @Test
    void ShouldGet_PaymentMethodById(){
        PaymentMethod paymentSaved = paymentRepository.save(paymentMethod);
        Optional<PaymentMethod> paymentOptional = paymentRepository.findById(paymentSaved.getId());
        PaymentMethod paymentFound = paymentOptional.get();
        assertThat(paymentFound,samePropertyValuesAs(paymentSaved));

    }

    @Test
    void ShouldDelete_PaymentMethod(){
        PaymentMethod paymentSaved = paymentRepository.save(paymentMethod);
        paymentRepository.deleteById(paymentSaved.getId());
        Optional<PaymentMethod> paymentOptional = paymentRepository.findById(paymentSaved.getId());
        assertEquals(paymentOptional, Optional.empty());

    }
}