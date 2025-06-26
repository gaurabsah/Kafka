package com.PaymentProcessorService.consumer;

import com.PaymentProcessorService.dto.OrderStatusDTO;
import com.PaymentProcessorService.io.ProcessedMessage;
import com.PaymentProcessorService.io.ProcessedMessageRepository;
import com.PaymentProcessorService.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class PaymentConsumer {

    private final OrderService orderService;

    private final ProcessedMessageRepository messageRepo;

    public PaymentConsumer(OrderService orderService, ProcessedMessageRepository messageRepo) {
        this.orderService = orderService;
        this.messageRepo = messageRepo;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    @Transactional("transactionManager")
    public void listen(@Payload OrderStatusDTO eventData, @Header("messageId") String messageId) {

        if (messageRepo.existsByMessageId(messageId)) {
            log.info("Duplicate message detected. Skipping processing for messageId: {}", messageId);
            return;
        }

        log.info("listening Event Data: {}",eventData);
        orderService.createOrUpdateOrderStatus(eventData);
        messageRepo.save(new ProcessedMessage(messageId));
    }
}

