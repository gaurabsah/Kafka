package com.PaymentProcessorService.io;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface ProcessedMessageRepository extends JpaRepository<ProcessedMessage, Long> {
	boolean existsByMessageId(String messageId);

	@Modifying
	@Transactional
	@Query("DELETE FROM ProcessedMessage m WHERE m.createdAt < :expiryTime")
	void deleteMessagesOlderThan(@Param("expiryTime") LocalDateTime expiryTime);
}

