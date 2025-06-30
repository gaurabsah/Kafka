package com.PaymentProcessorService.service;

import com.PaymentProcessorService.dto.PaymentRequest;
import com.PaymentProcessorService.entity.Payment;
import com.PaymentProcessorService.repository.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void processPayment(){
        System.out.println("My first Unit Test");
        PaymentRequest request = new PaymentRequest();
        request.setPaymentId("1");
        request.setAmount(500);
        request.setOrderId("1");

        Payment payment = new Payment();
        payment.setPaymentId(request.getPaymentId());
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setStatus("SUCCESS");
        payment.setTimestamp(LocalDateTime.now());

        Mockito.when(paymentRepository.save(payment)).thenReturn(payment);
        paymentService.processPayment(request);

        Assertions.assertEquals(payment.getPaymentId(),request.getPaymentId());
    }

}