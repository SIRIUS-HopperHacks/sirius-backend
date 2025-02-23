# Stage 1: Gradle 빌드
FROM gradle:7.6-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle build -x test

# Stage 2: 최종 런타임
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 10000
CMD ["java", "-jar", "app.jar"]

# Terminal에서 입력 / git action ->  docker build -t sirius:latest .
