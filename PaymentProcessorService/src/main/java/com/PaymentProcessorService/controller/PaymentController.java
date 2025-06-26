package com.PaymentProcessorService.controller;

import com.PaymentProcessorService.dto.PaymentRequest;
import com.PaymentProcessorService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/initiate")
    public ResponseEntity<String> initiatePayment(@RequestBody PaymentRequest request) {
        paymentService.processPayment(request);
        return ResponseEntity.ok("Payment initiation sent");
    }
}