INSERT INTO TB_ROLE (role) VALUES('ROLE_ADMINISTRADOR');

INSERT INTO TB_ROLE (role) VALUES('ROLE_GERENTE');

INSERT INTO TB_ROLE (role) VALUES('ROLE_ANALISTA');

INSERT INTO TB_USER (username, password, id_role) VALUES ('admin', 'admin', 1);

INSERT INTO TB_USER (username, password, id_role) VALUES ('gerente', 'gerente', 2);

INSERT INTO TB_USER (username, password, id_role) VALUES ('analista', 'analista', 3);