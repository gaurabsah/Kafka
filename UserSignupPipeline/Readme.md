# UserSignupPipeline

A Spring Boot microservice that processes user signup events using Kafka and stores them in PostgreSQL. It supports JSON schema evolution, deduplication based on email, and transactional safety to prevent race conditions.

---

## 🚀 Features

- ✅ Publish/consume Kafka events in JSON format
- ✅ Deduplicate users by email (update if exists, insert otherwise)
- ✅ Schema evolution with optional fields (e.g., `phoneNumber`)
- ✅ Atomic save/update logic using `@Transactional`
- ✅ PostgreSQL for persistent storage
- ✅ Confluent Schema Registry support (JSON)
- ✅ Fully Dockerized setup

---

## 🧱 Tech Stack

- Java 17
- Spring Boot 3
- Spring Kafka
- PostgreSQL
- Confluent Kafka & Schema Registry
- Docker + Docker Compose

---

## 📦 Architecture

Client → REST API (/api/signup)
→ Kafka Producer → [Kafka Topic]
→ Kafka Consumer → PostgreSQL

---
