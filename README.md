# 📦 Gestion des Ordres de Fabrication

Une application full-stack complète pour la gestion des ordres de fabrication industrielle, avec architecture microservices, déploiement Kubernetes et pipeline CI/CD automatisé via GitOps.

---

## 🎯 Vue d'ensemble

Ce projet démontre les meilleures pratiques du développement moderne d'applications d'entreprise :

- **Architecture distribuée** : Frontend séparé du backend avec authentification JWT
- **Infrastructure as Code (IaC)** : Déploiement déclaratif via Kubernetes et ArgoCD
- **DevOps / GitOps** : Pipeline CI/CD automatisé avec synchronisation Git
- **Sécurité** : Spring Security, JWT, CORS configuré
- **Base de données** : MySQL avec schéma Hibernate généré automatiquement

---

## 🏗️ Architecture globale

```
┌─────────────────┐
│   GitHub Repo   │
└────────┬────────┘
         │ (push)
         ↓
┌─────────────────┐
│    ArgoCD       │ ← Synchronisation GitOps
└────────┬────────┘
         │
         ↓
┌─────────────────────────────────────┐
│    Kubernetes Cluster (Minikube)    │
│                                     │
│  ┌──────────┐  ┌──────────────┐  ┌─────────┐
│  │ Frontend │  │   Backend    │  │  MySQL  │
│  │Angular17 │  │ Spring Boot  │  │   8.0   │
│  │:30007    │  │ :8080        │  │:3306    │
│  └──────────┘  └──────────────┘  └─────────┘
└─────────────────────────────────────┘
```

---

## ⚙️ Stack technique

| Couche | Technologie | Version |
|--------|-------------|---------|
| **Frontend** | Angular | 17.x |
| **Backend** | Spring Boot | 3.x |
| **Langage** | Java | 17 |
| **Sécurité** | Spring Security + JWT | - |
| **BD** | MySQL | 8.0 |
| **Conteneurisation** | Docker | latest |
| **Orchestration** | Kubernetes | 1.28+ |
| **CI/CD** | GitHub Actions + ArgoCD | - |

---

## 🚀 Démarrage rapide

### Prérequis
- Docker & Docker Compose
- Kubernetes (Minikube) ou cluster Kubernetes
- Git
- Node.js 18+ (pour développement frontend)
- Java 17+ (pour développement backend)

### Option 1️⃣ : Docker Compose (Développement local)

```bash
# Cloner le repository
git clone https://github.com/ton-username/gestion-ordres-fabrication.git
cd gestion-ordres-fabrication

# Lancer l'application complète
docker compose -f docker/docker-compose.yml up --build
```

Accès :
- Frontend : http://localhost:4200
- Backend API : http://localhost:8080
- Swagger UI : http://localhost:8080/swagger-ui.html
- phpMyAdmin : http://localhost:8081

### Option 2️⃣ : Kubernetes (Production)

```bash
# Déployer sur Minikube
kubectl apply -f k8s/

# Vérifier les pods
kubectl get pods -n projetjee

# Port-forward pour accéder au backend
kubectl port-forward svc/backend -n projetjee 8080:8080

# Port-forward pour le frontend
kubectl port-forward svc/frontend -n projetjee 4200:80
```

### Option 3️⃣ : Développement local (sans Docker)

**Backend :**
```bash
cd backend
./mvnw spring-boot:run
```

**Frontend :**
```bash
cd frontend
npm install
ng serve
```

---

## 🔐 Authentification

L'application utilise **JWT (JSON Web Tokens)** pour l'authentification.

### Utilisateurs par défaut

| Username | Password | Rôle |
|----------|----------|------|
| `admin` | `admin123` | ADMIN |
| `responsable` | `resp123` | RESPONSABLE_PRODUCTION |

### Login

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

Réponse :
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "role": "ADMIN",
  "username": "admin"
}
```

### Utiliser le token

```bash
curl -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8080/api/ordres
```

---

## 📚 API Endpoints

### Ordres de Fabrication
- `GET /api/ordres` - Récupérer tous les ordres
- `POST /api/ordres` - Créer un nouvel ordre
- `GET /api/ordres/{id}` - Détails d'un ordre
- `PUT /api/ordres/{id}` - Modifier un ordre
- `DELETE /api/ordres/{id}` - Supprimer un ordre

### Produits
- `GET /api/produits` - Récupérer tous les produits
- `POST /api/produits` - Créer un produit
- `PUT /api/produits/{id}` - Modifier un produit
- `DELETE /api/produits/{id}` - Supprimer un produit

### Machines
- `GET /api/machines` - Récupérer toutes les machines
- `POST /api/machines` - Créer une machine
- `PUT /api/machines/{id}` - Modifier une machine
- `DELETE /api/machines/{id}` - Supprimer une machine

### Employés
- `GET /api/employes` - Récupérer tous les employés
- `POST /api/employes` - Embaucher un employé
- `PUT /api/employes/{id}` - Modifier un employé
- `DELETE /api/employes/{id}` - Supprimer un employé

**Documentation Swagger** : http://localhost:8080/swagger-ui.html

---

## 🗂️ Structure du projet

```
gestion-ordres-fabrication/
├── backend/
│   ├── src/
│   │   ├── main/java/com/example/demo/
│   │   │   ├── controllers/      # REST Controllers
│   │   │   ├── services/         # Logique métier
│   │   │   ├── entities/         # Modèles JPA
│   │   │   ├── repositories/     # Accès données
│   │   │   └── auth/             # Authentification JWT
│   │   └── resources/
│   │       └── application.yml   # Configuration Spring
│   ├── pom.xml
│   └── Dockerfile
├── frontend/
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/       # Composants Angular
│   │   │   ├── services/         # Services HTTP
│   │   │   └── models/           # Interfaces TypeScript
│   │   └── assets/
│   ├── angular.json
│   └── Dockerfile
├── k8s/
│   ├── namespace.yaml
│   ├── backend-deployment.yaml
│   ├── frontend-deployment.yaml
│   ├── mysql-deployment.yaml
│   └── services.yaml
├── docker/
│   └── docker-compose.yml
├── .github/
│   └── workflows/GitHub Actions CI/CD
└── README.md
```

---

## 🔁 Pipeline CI/CD (GitOps)

Notre approche **GitOps** avec **ArgoCD** garantit :

✅ **Synchronisation automatique** : Tout changement dans Git est automatiquement déployé  
✅ **Déclaratif** : L'état cible est défini dans Git, Kubernetes s'y conforme  
✅ **Traçabilité** : Historique complet des déploiements via Git commits  
✅ **Rollback facile** : Revert un commit = rollback instantané  
✅ **Auto-healing** : ArgoCD détecte les dérives et les corrige  

### Workflow

```
1. Developer pousse du code → GitHub
2. GitHub Actions lance les tests + build Docker
3. Images Docker poussées au registry
4. ArgoCD détecte le changement
5. ArgoCD applique les manifests Kubernetes
6. Application déployée automatiquement
```

---

## 🗄️ Modèle de données

```
┌──────────────┐        ┌──────────────┐
│  Utilisateur │        │   Produit    │
│──────────────│        │──────────────│
│ id (PK)      │        │ id (PK)      │
│ username     │        │ nom          │
│ password     │        │ type         │
│ nom          │        │ stock        │
│ role         │        │ fournisseur  │
└──────────────┘        └──────────────┘
                              ▲
                              │ (FK)
                              │
                    ┌─────────────────────┐
                    │ OrdreFabrication    │
                    │─────────────────────│
                    │ id (PK)             │
                    │ projet              │
                    │ quantite            │
                    │ date                │
                    │ etat (EN_ATTENTE,   │
                    │       EN_COURS,     │
                    │       TERMINE)      │
                    │ produit_id (FK)     │
                    └─────────────────────┘

┌──────────────┐        ┌──────────────┐
│   Machine    │        │   Employé    │
│──────────────│        │──────────────│
│ id (PK)      │        │ id (PK)      │
│ nom          │        │ nom          │
│ etat         │        │ poste        │
│ derniere_    │        │ machine_     │
│ maintenance  │        │ assignee_id  │
└──────────────┘        │ (FK)         │
                        └──────────────┘
```

---

## 📝 Variables d'environnement

### Backend (`application.yml`)
```yaml
spring:
  datasource:
    url: jdbc:mysql://mysql:3306/projetjee
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: false
  security:
    jwt:
      secret: your-secret-key-here
      expiration: 86400000  # 24 heures
```

### Frontend (environment.ts)
```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080'
};
```

---

## 🧪 Tests

### Backend (JUnit + Mockito)
```bash
cd backend
./mvnw test
```

### Frontend (Jasmine/Karma)
```bash
cd frontend
npm test
```

---

## 📊 Monitoring & Logs

### Vérifier les pods Kubernetes
```bash
kubectl get pods -n projetjee -w
```

### Logs du backend
```bash
kubectl logs -f deployment/backend -n projetjee
```

### Logs du frontend
```bash
kubectl logs -f deployment/frontend -n projetjee
```

### Logs MySQL
```bash
kubectl logs -f deployment/mysql -n projetjee
```

---

## 🔧 Configuration avancée

### Ajouter un nouvel utilisateur
```bash
# Via API
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "newuser",
    "password": "password123",
    "nom": "Jean Dupont",
    "role": "RESPONSABLE_PRODUCTION"
  }'
```

### Scaling Kubernetes
```bash
# Scaler les replicas du backend
kubectl scale deployment backend --replicas=3 -n projetjee
```

### Mise à jour d'une image Docker
```bash
# Modifier l'image dans k8s/backend-deployment.yaml
kubectl set image deployment/backend \
  backend=ton-registry/backend:v1.2.0 \
  -n projetjee
```

---

## 🐛 Dépannage

### Erreur 403 Forbidden
**Cause** : Token JWT manquant ou expiré  
**Solution** : Récupérez un nouveau token via `/auth/login`

### Problème de connexion MySQL
```bash
# Vérifier si MySQL est accessible
kubectl exec -it pod/mysql-xxx -n projetjee -- \
  mysql -u root -p -e "SELECT 1;"
```

### Port déjà utilisé
```bash
# Trouver et tuer le processus
lsof -i :8080
kill -9 <PID>
```

---

## 📚 Documentation supplémentaire

- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [Angular Official Docs](https://angular.io/docs)
- [Kubernetes Documentation](https://kubernetes.io/docs/)
- [ArgoCD Documentation](https://argo-cd.readthedocs.io/)
- [JWT.io](https://jwt.io/)

---

## 👥 Contribution

1. **Fork** le repository
2. **Créer** une branche (`git checkout -b feature/amazing-feature`)
3. **Commit** tes changements (`git commit -m 'Add amazing feature'`)
4. **Push** vers la branche (`git push origin feature/amazing-feature`)
5. **Ouvrir** une Pull Request

---

## 📄 Licence

Ce projet est sous licence **MIT**. Voir le fichier `LICENSE` pour plus de détails.

---

## 👤 Auteur

**Ton Nom**  
📧 Email : safebouzidi199@gmail.com 
🐙 GitHub : github.com/safebouzidi199-art

---

## 🎓 Points clés pour ta présentation professionnelle

### Ce qui différencie ce projet :

1. **GitOps Workflow** : Utilisation d'ArgoCD pour une synchronisation Git-to-Production automatique et déclarative
2. **Kubernetes Natif** : Configuration complète d'orchestration avec manifests YAML
3. **Sécurité JWT** : Authentification stateless et scalable
4. **Séparation Frontend/Backend** : Architecture microservices avec déploiements indépendants
5. **Observabilité** : Logs structurés et monitoring via Kubernetes
6. **Reproducibilité** : Infrastructure as Code - tout peut être redéployé en une commande



---

**Dernière mise à jour** : Avril 2026  
**Version** : 1.0.0  
**Status** : ✅ Production-ready
