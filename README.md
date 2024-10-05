# SkyPay Backend - Spring Boot Application

## Résumé

Ce projet est un backend pour l'application SkyPay, développé avec **Spring Boot** et **Java 17**. Il implémente un système de gestion de comptes bancaires, incluant des services de transaction et d'authentification par **JWT**. Le backend expose des services RESTful et utilise **MySQL** comme base de données relationnelle.

---

## Fonctionnalités

- **Authentification JWT** : Implémentation d'une authentification sécurisée basée sur JWT (JSON Web Token).
- **Gestion des Comptes** : Services permettant de créer, lire, mettre à jour et supprimer des comptes bancaires.
- **Gestion des Transactions** : Services pour effectuer des dépôts, des retraits et consulter l'historique des transactions.
- **Services RESTful** : Exposition d'une API REST pour la gestion des utilisateurs, comptes et transactions.
  
---

## Prérequis

- **Java 17** : Assurez-vous d'avoir installé Java 17. [Télécharger Java](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
- **Maven** : Utilisé pour la gestion des dépendances. [Télécharger Maven](https://maven.apache.org/download.cgi).
- **MySQL** : Base de données relationnelle. [Télécharger MySQL](https://dev.mysql.com/downloads/mysql/).

---

## Installation et Démarrage

### 1. Cloner le dépôt

git clone [https://github.com/M-ZELMATI/skypay-backend.git  ](https://github.com/M-ZELMATI/skypay-backend.git)
cd skypay-backend

### 2. Configurer la base de données

Assurez-vous que MySQL est installé et en cours d'exécution. Ensuite, créez une base de données nommée **skypay** :

CREATE DATABASE skypay;

### 3. Modifier les paramètres de la base de données

Dans le fichier `application.yml`, vous pouvez ajuster les détails de connexion à votre base de données MySQL :

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/skypay
    username: root
    password: tftftf

Assurez-vous que les informations de connexion correspondent à votre configuration locale.

### 4. Lancer l'application

mvn spring-boot:run

Cela démarrera le backend à l'adresse [http://localhost:8081](http://localhost:8081).

---

## Structure du Projet

Le projet est structuré de la manière suivante :

- **Controllers** : Les contrôleurs REST qui gèrent les requêtes HTTP pour les comptes et transactions.
- **Services** : La logique métier de l'application, comme les dépôts, retraits et authentification.
- **Modals** : Les entités JPA représentant les comptes et transactions dans la base de données.
- **Repositories** : Les interfaces JPA pour la gestion des entités.
- **Security** : Gestion de l'authentification JWT et des rôles utilisateur.

---

## Entités Principales
Account
Transaction

---

## Technologies Utilisées

- **Spring Boot** : Pour le développement rapide d'applications Java.
- **Java 17** : Version utilisée pour ce projet.
- **JWT** : Implémentation d'une sécurité basée sur des tokens pour l'authentification.
- **MySQL** : Base de données relationnelle pour stocker les informations sur les comptes et transactions.
- **JPA/Hibernate** : Gestion des entités et des transactions avec la base de données.

---


