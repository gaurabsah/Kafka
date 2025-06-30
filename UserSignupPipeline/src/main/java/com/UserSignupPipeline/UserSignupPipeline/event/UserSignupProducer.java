package com.UserSignupPipeline.UserSignupPipeline.event;

import com.UserSignupPipeline.UserSignupPipeline.dto.UserSignupDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserSignupProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public UserSignupProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void sendSignupEvent(UserSignupDTO dto) {
        try {
            String json = objectMapper.writeValueAsString(dto);
            kafkaTemplate.send("user.signup.v1", json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize UserSignupDTO", e);
        }
    }
}