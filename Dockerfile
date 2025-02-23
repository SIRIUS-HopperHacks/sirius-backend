# Stage 1: Gradle 빌드
FROM gradle:7.6-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle build

# Stage 2: 최종 런타임
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 10000
CMD ["java", "-jar", "app.jar"]

# Run on terminal: Build Image, Run(Check) Container, Deploy Image
# docker build -t seongminsohn/sirius:latest .
# docker run -d -p 10000:10000 --name sirius_app seongminsohn/sirius:latest
# docker push seongminsohn/sirius:latest
