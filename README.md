# Gestion des Ordres de Fabrication

Application full-stack de gestion des ordres de fabrication industrielle.

## Stack technique

| Couche | Technologie |
|--------|-------------|
| Backend | Spring Boot 3, Java 17, Spring Security, JWT |
| Frontend | Angular 17 |
| Base de données | MySQL 8 |
| Conteneurisation | Docker, Docker Compose |
| Orchestration | Kubernetes |
| CI/CD | GitHub Actions |

## Structure du projet

```
├── backend/        # Spring Boot API REST
├── frontend/       # Angular SPA
├── docker/         # Dockerfiles + docker-compose
├── k8s/            # Manifests Kubernetes
└── .github/        # Workflows CI/CD
```

## Lancer le projet avec Docker

```bash
docker compose -f docker/docker-compose.yml up --build
```

| Service | URL |
|---------|-----|
| Frontend | http://localhost:4200 |
| Backend API | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| phpMyAdmin | http://localhost:8081 |

## Lancer en local (sans Docker)

### Backend
```bash
cd backend
./mvnw spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
ng serve
```

## Variables d'environnement

| Variable | Valeur par défaut |
|----------|-------------------|
| SPRING_DATASOURCE_URL | jdbc:mysql://mysql:3306/projetjee |
| SPRING_DATASOURCE_USERNAME | root |
| SPRING_DATASOURCE_PASSWORD | root |
