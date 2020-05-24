CREATE TABLE TB_ROLE(
    id serial PRIMARY KEY,
    role VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE TB_USER(
    id serial PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    id_role INTEGER NOT NULL,
    CONSTRAINT tb_user_role_fkey FOREIGN KEY(id_role)
        REFERENCES TB_ROLE(id)
);

CREATE TABLE TB_OPERATOR(
    id serial PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    creation_date DATE NOT NULL,
    id_user INTEGER NOT NULL,
    CONSTRAINT tb_operator_user_fkey FOREIGN KEY(id_user)
        REFERENCES TB_USER(id)
);

CREATE TABLE TB_PERSON(
    id serial PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    document VARCHAR(50) UNIQUE NOT NULL,
    date_of_birth DATE NOT NULL,
    mothers_name VARCHAR(50),
    fathers_name VARCHAR(50),
    creation_date DATE NOT NULL,
    person_type INTEGER NOT NULL,
    id_operator INTEGER NOT NULL,
    CONSTRAINT tb_person_operator_fkey FOREIGN KEY(id_operator)
        REFERENCES TB_OPERATOR(id)
);

CREATE TABLE TB_PHONE(
    id serial PRIMARY KEY,
    ddd VARCHAR(3) NOT NULL,
    number VARCHAR(50) NOT NULL,
    type INTEGER,
    creation_date DATE NOT NULL,
    id_operator INTEGER NOT NULL,
    CONSTRAINT tb_phone_operator_fkey FOREIGN KEY(id_operator)
        REFERENCES TB_OPERATOR(id)
);

CREATE TABLE TB_PHONE_PERSON(
    id_phone INTEGER REFERENCES TB_PHONE (id),
    id_person INTEGER REFERENCES TB_PERSON (id),
    CONSTRAINT phone_person_pkey PRIMARY KEY (id_phone, id_person)
);