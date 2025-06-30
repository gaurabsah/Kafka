# Payment Processor Service

This is a Spring Boot microservice designed to process payment requests, publish payment status events to Kafka, and update order status based on those events. It includes:

- Kafka Producer/Consumer integration
- JSON serialization
- Transactional messaging (Kafka + Database)
- Idempotency with message deduplication using messageId
- PostgreSQL as a persistent store

---

## 🚀 Features

- ✅ Save payment info in PostgreSQL
- ✅ Send payment status (`OrderStatusDTO`) to Kafka with a unique `messageId`
- ✅ Consume Kafka messages and update order status
- ✅ Prevent duplicate Kafka message processing
- ✅ Kafka + DB transactions using Spring's `@Transactional`

---

## ⚙️ Tech Stack

- Java 17+
- Spring Boot 3+
- Spring Kafka
- PostgreSQL
- Docker Compose (for Kafka & DB)
- Kafka Transactions + Idempotent Producers

---

## 📦 Kafka Topic

- **Topic Name:** `payment-topic`
- **Message Type:** JSON `OrderStatusDTO`
- **Headers Used:** `messageId` (for deduplication)

---

## 📑 Endpoints

### `POST /api/payments`

```json
{
  "paymentId": "abc123",
  "orderId": "order-789",
  "amount": 5000.0
}
