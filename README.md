# Green Spaces Management : Projet Android - Master E-SERVICES

## Introduction

L’application de Gestion du parc de Lille 1 a été programmé avec le langage Kotlin.
Kotlin est un langage supporté officiellement par Android et a été créé par Google.


## Base de données 

Pour cette application, j’ai décidé d’utiliser la base de données locale « Anko SQLite» qui 
permet de simplifier le travail avec les bases de données SQLite.
Cette base contient une table dont vous trouvez ci-dessous la description.
Table : MaintenanceTasks 
- type : String 
- longitude : String 
- latitude : String 
- address : String 
- description : String

Cette table permet d’enregistrer les tâches d’entretien que l’utilisateur va ajouter. 
Une tâche d’entretien contient un type spécifique, une position représentée par une longitude 
et une latitude, une adresse qui est optionnelle et une description qui est aussi optionelle. 

## Architecture du projet 

L’application est structurée via un modèle MVC (Modèle-Vue-Contrôleur). 
Cette structure est très répandue dans le monde des applications mobiles, car il permet de structurer 
l’application en trois grands axes : 
- le modèle qui contient les données à afficher, 
- la vue qui représente l’interface graphique, 
- et le contrôleur qui traite les actions de l’utilisateurs.

## Problèmes rencontrés 

L’application a été réalisé entièrement. La liste des tâches d’entretiens est présente. 
Il est possible aussi d’ajouter un problème, par contre après avoir cliqué sur le bouton « Localiser » et avoir accepter
la localisation il faut re appuyer sur « Localiser » pour voir afficher la position ainsi que l’adresse généré automatiquement.
Un autre problème constaté est que la base de données ne se rempli et ne se vide pas instantanément, il faut soit relancer 
l’application soit ajouter une nouvelle tâche.