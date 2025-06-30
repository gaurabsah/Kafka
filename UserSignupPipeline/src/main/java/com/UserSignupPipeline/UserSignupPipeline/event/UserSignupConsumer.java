package com.UserSignupPipeline.UserSignupPipeline.event;

import com.UserSignupPipeline.UserSignupPipeline.dto.UserSignupDTO;
import com.UserSignupPipeline.UserSignupPipeline.service.UserSignupService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserSignupConsumer {

    private final ObjectMapper objectMapper;
    private final UserSignupService service;

    public UserSignupConsumer(UserSignupService service) {
        this.objectMapper = new ObjectMapper();
        this.service = service;
    }

    @KafkaListener(topics = "user.signup.v1", groupId = "user-signup-consumer")
    public void listen(String message) {
        try {
            JsonNode root = objectMapper.readTree(message);
            UserSignupDTO dto = UserSignupDTO.builder()
                    .name(root.get("name").asText())
                    .email(root.get("email").asText())
                    .phoneNumber(root.has("phoneNumber") ? root.get("phoneNumber").asText() : null)
                    .build();
            UserSignupDTO signupDTO = service.save(dto);
            log.info("user Signup: {}",signupDTO);
        } catch (Exception e) {
            log.error("Failed to consume signup event: " + e.getMessage());
        }
    }
}