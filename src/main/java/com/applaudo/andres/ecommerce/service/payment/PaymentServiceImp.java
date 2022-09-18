package com.applaudo.andres.ecommerce.service.payment;

import com.applaudo.andres.ecommerce.entity.PaymentMethod;
import com.applaudo.andres.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentMethod createPaymentMethod(String paymentName) {
        PaymentMethod paymentMethod = new PaymentMethod(paymentName);
        return paymentRepository.save(paymentMethod);
    }

    @Override
    public PaymentMethod getPaymentMethod(Integer paymentId) {
        return paymentRepository.getReferenceById(paymentId);
    }

    @Override
    public PaymentMethod updatePaymentMethod(Integer paymentId, Integer newPaymentId) {
        Optional<PaymentMethod> paymentOptional= paymentRepository.findById(paymentId);
        if(paymentOptional.isPresent()){
            PaymentMethod paymentActual = paymentOptional.get();
            paymentActual.setId(newPaymentId);
            return paymentActual;
        }
            return null;
    }
}
