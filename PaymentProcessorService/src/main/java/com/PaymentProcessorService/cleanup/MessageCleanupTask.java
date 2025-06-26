package com.PaymentProcessorService.cleanup;

import com.PaymentProcessorService.io.ProcessedMessageRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageCleanupTask {

    private final ProcessedMessageRepository messageRepository;

    public MessageCleanupTask(ProcessedMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Runs every day at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanupOldMessages() {
        LocalDateTime expiryTime = LocalDateTime.now().minusDays(7); // Keep last 7 days
        messageRepository.deleteMessagesOlderThan(expiryTime);
    }
}
