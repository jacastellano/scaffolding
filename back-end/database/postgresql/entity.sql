CREATE TABLE entity.entity
	(
		id bigint NOT NULL DEFAULT nextval('entity.entity_id_seq'::regclass),
		create_date timestamp without time zone,
		delete_date timestamp without time zone,
		description character varying(255) COLLATE pg_catalog."default",
		title character varying(255) COLLATE pg_catalog."default",
		update_date timestamp without time zone,
		CONSTRAINT entity_pkey PRIMARY KEY (id)
	)
	WITH (
		OIDS = FALSE
	)
	TABLESPACE pg_default;

	ALTER TABLE entity.entity
		OWNER to postgres;