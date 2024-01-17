#user-manager
prerequis : docker ou postgres diponible sinon installer docker

Pour installer la base de donnée postgres dans le docker :
`docker compose up -d `


Lancer l'application avec ces variables d'environnement et mettre à jour leurs valeurs si nécessaire : 
POSTGRES_URL=jdbc:postgresql://localhost:5432/postgres
POSTGRES_USER=postgres
POSTGRES_PASSWORD=password
POSTGRES_SCHEMA=user_manager
DB_CONNECTION_TIMEOUT=20000
DB_POOL_SIZE=20
LOGGING_LEVEL=INFO
SHOW_SQL=true


# Paramètres Techniques

| Paramètre                    | Description                                             |
|------------------------------|---------------------------------------------------------|
| DB_CONNECTION_TIMEOUT        | Durée du timeout de la base de donnée en millisecondes  |
| DB_POOL_SIZE                 | Nombre maximum de pool de connexion à la base de donnée |
| POSTGRES_USER                | Utilisateur qui accède à la base de donnée              |
| POSTGRES_URL                 | Url de la base de donnée                                |
| POSTGRES_SCHEMA              | Schéma de la base de donnée                             |
| POSTGRES_PASSWORD            | Mot de passe de la base de donnée                       |