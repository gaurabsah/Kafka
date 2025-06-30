package com.UserSignupPipeline.UserSignupPipeline.controller;

import com.UserSignupPipeline.UserSignupPipeline.dto.UserSignupDTO;
import com.UserSignupPipeline.UserSignupPipeline.event.UserSignupProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signup")
public class UserSignupController {

    private final UserSignupProducer producer;

    public UserSignupController(UserSignupProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody UserSignupDTO dto) {
        producer.sendSignupEvent(dto);
        return ResponseEntity.ok("Signup event sent");
    }
}