FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY target/UserSignupPipeline-*.jar UserSignupPipeline.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "UserSignupPipeline.jar"]