# ================================
# STAGE 1 : Build avec Maven
# ================================
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Dossier de travail dans le conteneur
WORKDIR /app

# Copier pom.xml et télécharger les dépendances (cache Docker)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier le code source et compiler
COPY src ./src
RUN mvn clean package -DskipTests

# ================================
# STAGE 2 : Run avec Java léger
# ================================
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copier le .jar depuis le stage de build
COPY --from=build /app/target/*.jar app.jar

# Port exposé
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
