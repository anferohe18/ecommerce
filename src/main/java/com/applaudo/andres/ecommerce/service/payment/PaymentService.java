package com.applaudo.andres.ecommerce.service.payment;
import com.applaudo.andres.ecommerce.entity.PaymentMethod;
import org.springframework.stereotype.Service;


public interface PaymentService {

    PaymentMethod createPaymentMethod(String paymentName);
    PaymentMethod getPaymentMethod(Integer paymentId);
    PaymentMethod updatePaymentMethod(Integer paymentId,Integer newPaymentId );

}
