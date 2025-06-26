# Use Java 21 base image
FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/paymentProcessorService-*.jar payment-processor-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "payment-processor-service.jar"]