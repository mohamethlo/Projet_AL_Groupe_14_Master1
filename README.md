# Projet de Site d’Actualité – Java + Spring Boot + React

## Technologies utilisées

- **Back-end** : Java 17, Spring Boot, Spring Security, Spring Data JPA, JWT, SOAP (à venir)
- **Front-end** : React.js, Axios, Bootstrap 5
- **Base de données** : MySQL
- **Outils** : Postman, Git, Maven

---

## Types d’utilisateurs

- **Visiteur** : peut consulter les articles et catégories librement
- **Éditeur** : doit s’authentifier (JWT) pour gérer les articles et catégories
- **Administrateur** (à venir) : pourra gérer les utilisateurs via un service SOAP sécurisé

---

## Authentification

- Implémentée avec **Spring Security + JWT**
- Endpoints principaux :
  - `POST /auth/register` – Enregistrement
  - `POST /auth/login` – Connexion (retourne un token JWT)
- Routes protégées selon rôle (éditeur ou admin)

---

## Fonctionnalités du back-end (Service web)

### Public
- `GET /api/articles` → Liste de tous les articles
- `GET /api/articles/{id}` → Détail d’un article
- `GET /api/articles/categorie/{id}` → Articles par catégorie
- `GET /api/categories` → Liste des catégories
- `GET /api/images/article/{id}` → Images liées à un article

### Protégé (Éditeur/Admin uniquement)
- `POST /api/articles` → Créer un article
- `PUT /api/articles/{id}` → Modifier un article
- `DELETE /api/articles/{id}` → Supprimer un article
- `POST /api/categories` → Ajouter une catégorie
- `PUT /api/categories/{id}` → Modifier une catégorie
- `DELETE /api/categories/{id}` → Supprimer une catégorie
- `POST /api/images/upload/{articleId}` → Upload image (BLOB)

---

## Gestion des images

- Upload via `multipart/form-data`
- Stockage en base de données (champ `LONGBLOB`)
- Affichage via endpoint d’accès direct (stream) : `GET /api/images/{id}/raw`

---

## Fonctionnalités du front-end (site web)

- Page d’accueil avec articles
- Page de détail d’un article + images
- Sidebar avec liste des catégories
- Page catégories (articles filtrés)
- Connexion éditeur (via token JWT)
- Interface éditeur :
  - Tableau de bord (CRUD articles)
  - Création / modification d’article avec upload d’image
  - Gestion des catégories (ajout, édition, suppression)
- Navigation conditionnelle selon le rôle

---

## Contrôle de l'accès (React)

- Visiteurs : navigation libre sans connexion
- Éditeurs : accès privé via `ProtectedRoute`
- Authentification persistée avec `AuthContext` et `localStorage`

---

## Structure du projet


├──actualite-app
|    service-web/
|   ├── src/
|   │   └── main/
|   │       ├── java/
|   │       │   └── com/esp/actualite/
|   │       │       ├── controllers/         # Contrôleurs REST
|   │       │       ├── models/              # Entités JPA
|   │       │       ├── repositories/        # Repositories Spring Data
|   │       │       ├── services/            # Services métier
|   │       │       └── ActualiteApplication.java
|   │       └── resources/
|   │           ├── application.properties   # Configuration DB, fichiers, CORS, etc.
|   │           └── static/uploads/          # (si images servies statiquement)
|   ├── pom.xml
|   └── README.md
|
|├──site-web/
|   ├── public/
|   │   └── uploads/              # Images stockées (si backend les sert statiquement)
|   ├── src/
|   │   ├── assets/               # Fichiers statiques
|   │   ├── components/           # Navbar, Footer, Sidebar, ArticleCard
|   │   ├── pages/                # Pages React : Accueil, Catégories, Article Détail
|   │   ├── routes/               # Routing configuration
|   │   ├── services/             # Appels API avec axios
|   │   ├── images/               # Logo de l’ESP
|   │   └── App.js                # Point d’entrée principal
|   ├── package.json
|   └── README.md
|

