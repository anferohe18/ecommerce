package com.applaudo.andres.ecommerce.service.payment;

import com.applaudo.andres.ecommerce.dto.PaymentDto;
import com.applaudo.andres.ecommerce.entity.PaymentMethod;
import com.applaudo.andres.ecommerce.exceptions.PaymentNotFoundException;
import com.applaudo.andres.ecommerce.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing the payment service")
class PaymentServiceImpTest {

    @Mock
    private PaymentRepository paymentRepository;

    private PaymentServiceImp paymentServiceImp;

    PaymentMethod paymentMethodEntity;
    PaymentDto paymentDto;


    @BeforeEach
    void setUp() {
        paymentServiceImp = new PaymentServiceImp(paymentRepository);

        paymentMethodEntity = PaymentMethod.builder()
                .id(1)
                .methodName("cash")
                .build();

        paymentDto = PaymentDto.builder()
                .id(1)
                .paymentMethod("cash")
                .build();
    }

    @Test
    void createPaymentMethod() {
        when(paymentRepository.save(any(PaymentMethod.class))).thenReturn(paymentMethodEntity);
        PaymentMethod paymentMethodSaved = paymentServiceImp.createPaymentMethod(paymentDto.getPaymentMethod());

        assertThat(paymentMethodSaved, is(equalTo(paymentMethodEntity)));
        verify(paymentRepository, times(1)).save(any(PaymentMethod.class));
    }

    @Test
    void getPaymentMethod() {
        when(paymentRepository.findById(anyInt())).thenReturn(Optional.ofNullable(paymentMethodEntity));
        PaymentMethod paymentMethodFound = paymentServiceImp.getPaymentMethod(paymentMethodEntity.getId());

        assertThat(paymentMethodFound, is(equalTo(paymentMethodEntity)));
        verify(paymentRepository, times(1)).findById(anyInt());
    }

    @Test
    void shouldThrow_PaymentNotFoundException_IfPaymentDoesNotExist(){
        when(paymentRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(PaymentNotFoundException.class, () ->{
            paymentServiceImp.getPaymentMethod(anyInt());
        });
    }

    @Test
    void updatePaymentMethod() {
        PaymentMethod newPaymentMethod = PaymentMethod.builder()
                .id(2)
                .methodName("debit card")
                .build();
        when(paymentRepository.findById(anyInt())).thenReturn(Optional.ofNullable(paymentMethodEntity));

        PaymentMethod paymentMethodUpdated = paymentServiceImp.updatePaymentMethod(paymentMethodEntity.getId(), newPaymentMethod.getId());
        paymentMethodEntity.setId(newPaymentMethod.getId());

        assertThat(paymentMethodUpdated.getId(),is(equalTo(newPaymentMethod.getId())));
    }
}