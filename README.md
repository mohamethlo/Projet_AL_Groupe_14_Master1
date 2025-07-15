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
- **Administrateur** : pourra gérer les utilisateurs via un service SOAP sécurisé

---


### Structure du projet

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
|└── application-client/
|   ├── controller/
|   │   └── UtilisateurController.java
|   ├── model/
|   │   └── Utilisateur.java
|   ├── view/
|   │   ├── LoginView.java
|   │   ├── PanelAjoutUtilisateur.java
|   │   ├── PanelListeUtilisateurs.java
|   │   └── AdminDashboardView.java 
|   ├── Main.java
|    └── pom.xml


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

### Gestion des images

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

### Contrôle de l'accès (React)

- Visiteurs : navigation libre sans connexion
- Éditeurs : accès privé via `ProtectedRoute`
- Authentification persistée avec `AuthContext` et `localStorage`

---


## Application client (Service SOAP + Java Swing)

### Objectif

Ce projet est une application cliente en **Java Swing** qui permet à des administrateurs de **gérer les utilisateurs** (admins et éditeurs) d’un site web via un **service SOAP** développé avec **Spring Boot**.  
L'application respecte une architecture **MVC**, et propose une interface utilisateur ergonomique pour :
- Se connecter
- Ajouter des utilisateurs
- Afficher la liste des éditeurs
- Modifier ou supprimer un utilisateur

Structure du client

---

### Architecture

#### Client (Front-end)
- **Technologie** : Java Swing (avec MVC)
- **Structure** :
  - `view` : interfaces graphiques (LoginView, PanelListeUtilisateurs, PanelAjoutUtilisateur etc…)
  - `controller` : consommation du service SOAP
  - `model` : classes représentant les objets métier (Utilisateur, etc.)
- **Communication** : via `wsimport` généré depuis le WSDL exposé par le back-end

#### Serveur (Back-end)
- **Technologie** : Spring Boot
- **Service SOAP** exposé à l’URL : `http://localhost:8081/ws/utilisateurWsdl.wsdl`
- **Fonctionnalités exposées** :
  - `CreateUtilisateur`
  - `GetUtilisateur`
  - `UpdateUtilisateur`
  - `DeleteUtilisateur`
  - `GetAllUtilisateurs` (ajouté pour la liste complète)

---

### Fonctionnalités Implémentées

#### Authentification (LoginView)
- L’utilisateur entre son **email** et son **mot de passe**.
- Le client interroge le service SOAP avec `GetUtilisateurRequest`.
- Si l’utilisateur est trouvé :
  - Il est redirigé vers le dashboard **Admin** ou **Éditeur**, selon son rôle.
  - Sinon, un message d’erreur s’affiche.

#### Dashboard Admin
- Une fois connecté, un administrateur accède :
  - à la **liste des éditeurs** (`PanelListeUtilisateurs`)
  - à l’interface d’**ajout d’utilisateur** (`PanelAjoutUtilisateur`)

#### Liste des utilisateurs
- Affichage dans une `JTable` de :
  - Prénom, nom, email, rôle
  - Actions possibles : **modifier** ou **supprimer**
- Données récupérées via l’appel `GetAllUtilisateursRequest`

#### Ajout d’utilisateur
- Formulaire avec les champs :
  - Nom, prénom, email, mot de passe, rôle
- Données envoyées via `CreateUtilisateurRequest`
- Affichage d’un message de succès ou d’erreur selon le cas

#### Supprimer / Modifier
- Un clic sur les boutons de la colonne "Action" dans la table permet :
  - de supprimer un utilisateur (`DeleteUtilisateurRequest`)
  - de modifier un utilisateur via une popup (`UpdateUtilisateurRequest`)

---

### Technologies Utilisées

#### Côté client
- Java 17
- Swing pour l’interface
- Maven
- `wsimport` pour la génération des classes SOAP

#### Côté serveur
- Spring Boot 3.5.3
- Spring Web Services
- JPA (base de données)
- JAXB, JAX-WS pour SOAP
- MySQL pour la persistance des utilisateurs
- JWT pour sécurité (authentification à venir)

---

### Lancement du projet

#### Backend
```bash
cd service-web
mvn spring-boot:run 

```
```bash
cd application-client
mvn clean install
mvn exec:java -Dexec.mainClass="Main"

```