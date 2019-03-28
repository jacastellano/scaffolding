
# PostgreSQL

* Install and Setup [PostgreSQL](https://www.postgresql.org/) v11.1

* Use your favorite tool for admin Postgres, we suggest [pgAdmin](https://www.pgadmin.org/)

* Create database

    ```
    CREATE DATABASE scaffoldingdb
        WITH 
        OWNER = postgres
        ENCODING = 'UTF8'
        CONNECTION LIMIT = -1;
    ```

* Create schemas

    ```
    CREATE SCHEMA common
        AUTHORIZATION postgres;

    CREATE SCHEMA entity
        AUTHORIZATION postgres;
    ```
* Create Entity Data Model:
	Execute entity.sql

* Create Spring Oauth2 Data Model:
	Execute oauth.sql

* Add Spring Oauth2 Data Client:
	Execute oauth_clients.sql