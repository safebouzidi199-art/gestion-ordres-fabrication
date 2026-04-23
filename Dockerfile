# ---- Build stage ----
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
# Download dependencies first (layer cache)
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

# ---- Runtime stage ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Create non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
COPY --from=builder /app/target/ProjetJee-0.0.1-SNAPSHOT.jar app.jar
RUN chown appuser:appgroup app.jar
USER appuser
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
