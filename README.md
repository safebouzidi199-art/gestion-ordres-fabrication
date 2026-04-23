# Gestion des Ordres de Fabrication

Application web de gestion de production industrielle (employés, machines, produits, ordres de fabrication) déployée avec une chaîne DevOps complète de bout en bout.

[![CI Pipeline](https://github.com/safebouzidi199-art/gestion-ordres-fabrication/actions/workflows/ci.yml/badge.svg)](https://github.com/safebouzidi199-art/gestion-ordres-fabrication/actions/workflows/ci.yml)

---

## Stack technique

| Couche | Technologie |
|--------|-------------|
| Backend | Spring Boot 4.0.5 · Java 17 · Spring Security · JWT |
| Frontend | Angular 21 · TypeScript · Nginx |
| Base de données | MySQL 8.0 |
| Conteneurisation | Docker · Docker Compose |
| Orchestration | Kubernetes (Minikube) |
| GitOps / CD | ArgoCD |
| CI | GitHub Actions |
| Qualité code | SonarCloud · JaCoCo |
| Sécurité | Trivy · OWASP Dependency Check · GitHub Secrets |
| Monitoring | Prometheus · Grafana |

---

## Architecture

```
┌──────────────────────────────────────────────────────────────┐
│                        Kubernetes Cluster                     │
│                                                              │
│  ┌─────────────┐   HTTP    ┌──────────────┐   JDBC  ┌──────┐│
│  │  Angular    │ ────────► │ Spring Boot  │ ──────► │MySQL ││
│  │  (Nginx:80) │           │  (:8080)     │         │(:3306││
│  └─────────────┘           └──────────────┘         └──────┘│
│                                    │                         │
│                          /actuator/prometheus                 │
│                                    │                         │
│                            ┌───────────────┐                 │
│                            │  Prometheus   │──► Grafana:3000 │
│                            └───────────────┘                 │
└──────────────────────────────────────────────────────────────┘
                              ▲
                    ArgoCD (GitOps sync)
                              ▲
                    GitHub (k8s/ manifests)
                              ▲
                    GitHub Actions (CI/CD)
```

---

## Pipeline CI/CD

```
git push ──► GitHub Actions
               ├── Job 1 : Backend
               │     ├── mvn install
               │     ├── mvn test (H2 in-memory)
               │     ├── JaCoCo coverage
               │     └── SonarCloud analysis
               │
               ├── Job 2 : Frontend
               │     ├── npm ci
               │     ├── ESLint
               │     └── ng build --prod
               │
               ├── Job 3 : Security (après jobs 1 & 2)
               │     ├── OWASP Dependency Check
               │     └── Trivy image scan (CRITICAL = fail)
               │
               └── Job 4 : Docker Push (main uniquement)
                     ├── docker build & push backend
                     ├── docker build & push frontend
                     ├── update k8s/ image tags
                     └── git commit → ArgoCD auto-sync
```

---

## Lancer avec Docker Compose

```bash
# Copier et configurer les variables d'environnement
cp .env.example .env

# Démarrer toute la stack
docker compose up -d

# Vérifier les logs
docker compose logs -f backend
```

| Service | URL |
|---------|-----|
| Frontend | http://localhost:4200 |
| Backend API | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| Métriques | http://localhost:8080/actuator/prometheus |
| Prometheus | http://localhost:9090 |
| Grafana | http://localhost:3000 (admin / admin) |

---

## Déploiement Kubernetes

```bash
# 1. Démarrer Minikube
minikube start

# 2. Créer le namespace
kubectl apply -f k8s/namespace.yml

# 3. Créer les secrets (ne pas committer les vraies valeurs)
kubectl create secret generic projetjee-secrets \
  --from-literal=mysql-root-password=<PASSWORD> \
  --from-literal=jwt-secret=<JWT_SECRET> \
  -n projetjee

# 4. Déployer toutes les ressources
kubectl apply -f k8s/

# 5. Vérifier le déploiement
kubectl get pods -n projetjee
```

### ArgoCD (GitOps)

```bash
# Installer ArgoCD
kubectl create namespace argocd
kubectl apply -n argocd -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml

# Appliquer l'application (sync automatique activé)
kubectl apply -f k8s/argocd-app.yml
```

---

## GitHub Secrets requis

| Secret | Description |
|--------|-------------|
| `DOCKERHUB_USERNAME` | Nom d'utilisateur Docker Hub |
| `DOCKERHUB_TOKEN` | Token d'accès Docker Hub |
| `SONAR_TOKEN` | Token SonarCloud |
| `SONAR_PROJECT_KEY` | Clé du projet SonarCloud |
| `SONAR_ORGANIZATION` | Organisation SonarCloud |

---

## Développement local

**Backend :**
```bash
mvn spring-boot:run
```

**Frontend :**
```bash
cd frontend
npm install --legacy-peer-deps
ng serve
```

---

## Endpoints de monitoring

| Endpoint | Description |
|----------|-------------|
| `GET /actuator/health` | État de l'application |
| `GET /actuator/prometheus` | Métriques Prometheus |
| `GET /actuator/metrics` | Métriques détaillées |
| `GET /swagger-ui.html` | Documentation API |
