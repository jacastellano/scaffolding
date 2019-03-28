
-- ENTITY DATA MODEL


-- SEQUENCE: common.typology_id_seq

-- DROP SEQUENCE common.typology_id_seq;

CREATE SEQUENCE common.typology_id_seq;

ALTER SEQUENCE common.typology_id_seq
    OWNER TO postgres;


-- Table: common.typology

-- DROP TABLE common.typology;

CREATE TABLE common.typology
(
    id bigint NOT NULL DEFAULT nextval('common.typology_id_seq'::regclass),
    code character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default",
    create_date date NOT NULL,
    create_user bigint NOT NULL,
    update_date date NOT NULL,
    update_user bigint NOT NULL,
    delete_date date,
    CONSTRAINT typology_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE common.typology
    OWNER to postgres;


-- SEQUENCE: entity.entity_id_seq

-- DROP SEQUENCE entity.entity_id_seq;

CREATE SEQUENCE entity.entity_id_seq;

ALTER SEQUENCE entity.entity_id_seq
    OWNER TO postgres;


-- Table: entity.entity

-- DROP TABLE entity.entity;

CREATE TABLE entity.entity
(
    id bigint NOT NULL DEFAULT nextval('entity.entity_id_seq'::regclass),
    code character varying COLLATE pg_catalog."default" NOT NULL,
    title character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default",
    entity_type_id bigint,
    create_date date NOT NULL,
    create_user bigint NOT NULL,
    update_date date NOT NULL,
    update_user bigint NOT NULL,
    delete_date date,
    CONSTRAINT entity_pkey PRIMARY KEY (id),
    CONSTRAINT entity_entity_type_id_fkey FOREIGN KEY (entity_type_id)
        REFERENCES common.typology (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE entity.entity
    OWNER to postgres;