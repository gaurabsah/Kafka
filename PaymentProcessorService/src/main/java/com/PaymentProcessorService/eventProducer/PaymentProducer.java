package com.PaymentProcessorService.eventProducer;

import com.PaymentProcessorService.dto.OrderStatusDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class PaymentProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    public PaymentProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEventToKafka(String topic, String status) {
        String messageId = UUID.randomUUID().toString();
        OrderStatusDTO dto = new OrderStatusDTO(status);
        log.info("Sending kafka event: {}", dto);

        kafkaTemplate.executeInTransaction(kt -> {
            kt.send(
                    MessageBuilder.withPayload(dto)
                            .setHeader(KafkaHeaders.TOPIC, topic != null ? topic : topicName)
                            .setHeader("messageId", messageId)
                            .build()
            );
            return true;
        });

        log.info("Sent event with messageId={}", messageId);
    }
}

