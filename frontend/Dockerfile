# ================================
# STAGE 1 : Build Angular
# ================================
FROM node:20-alpine AS build

WORKDIR /app

# Copier package.json et installer les dépendances
COPY package*.json .
RUN npm install

# Copier le code source et compiler
COPY . .
RUN npm run build

# ================================
# STAGE 2 : Servir avec Nginx
# ================================
FROM nginx:alpine

# Copier les fichiers compilés vers Nginx
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html

# Config Nginx pour Angular Router
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
