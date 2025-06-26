package com.PaymentProcessorService.service;

import com.PaymentProcessorService.dto.PaymentRequest;
import com.PaymentProcessorService.entity.Payment;
import com.PaymentProcessorService.eventProducer.PaymentProducer;
import com.PaymentProcessorService.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentProducer paymentProducer;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Environment env;

    public PaymentService(PaymentRepository paymentRepository, PaymentProducer paymentProducer) {
        this.paymentRepository = paymentRepository;
        this.paymentProducer = paymentProducer;
    }

    @Transactional("transactionManager")
    public void processPayment(PaymentRequest request) {
//        check if payment already exist
        if (paymentRepository.findByPaymentId(request.getPaymentId()).isPresent()) {
            throw new IllegalStateException("Duplicate payment ID");
        }

        Payment payment = new Payment();
        payment.setPaymentId(request.getPaymentId());
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setStatus("SUCCESS");
        payment.setTimestamp(LocalDateTime.now());

        Payment newPayment = paymentRepository.save(payment);
        log.info("Payment saved in db: {}",payment);
        paymentProducer.sendEventToKafka(env.getProperty("spring.kafka.topic.name"), newPayment.getStatus());
    }
}