
# PostgreSQL

* Install and Setup [PostgreSQL](https://www.postgresql.org/) v11.1

* Use your favorite tool for admin Postgres, we suggest [pgAdmin](https://www.pgadmin.org/)

* Create database

    ```
    CREATE DATABASE scaffoldingdb;
    ```

* Create schemas

    ```
    CREATE SCHEMA comun;
    CREATE SCHEMA entity;
    ```
* Create Entity table

    ```
    CREATE TABLE entity."ENTITY"
    (
        "ID" integer NOT NULL DEFAULT nextval('entity."ENTITY_ID_seq"'::regclass),
        "TITLE" character varying(50) COLLATE pg_catalog."default" NOT NULL,
        "DESCRIPTION" text COLLATE pg_catalog."default",
        "CREATE_DATE" timestamp without time zone NOT NULL,
        "UPDATE_DATE" timestamp without time zone,
        "DELETE_DATE" timestamp without time zone,
        CONSTRAINT "ENTITY_PK" PRIMARY KEY ("ID")
    )
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

    ALTER TABLE entity."ENTITY"
        OWNER to postgres;
    
    COMMENT ON TABLE entity."ENTITY"
        IS 'Main entity table';
    ```
