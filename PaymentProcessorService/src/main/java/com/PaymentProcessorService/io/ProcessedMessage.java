package com.PaymentProcessorService.io;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "processed_messages")
public class ProcessedMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String messageId;

	@Column(nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();


	public ProcessedMessage() {
	}

	public ProcessedMessage(String messageId) {
		this.messageId = messageId;
	}

	public ProcessedMessage(String messageId, LocalDateTime createdAt) {
		this.messageId = messageId;
		this.createdAt = createdAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
