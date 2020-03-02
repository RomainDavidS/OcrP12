INSERT INTO organization
(id, name) VALUES
(1, 'AGENCE ALPES'),
(2, 'AGENCE SIRHO');

INSERT INTO type_site
(id, name) VALUES
(1, 'AGENCE');

INSERT INTO site
(id, name,id_type_site) VALUES
(1, 'AGENCE Alpes - Annecy',1);

INSERT INTO fonction
(id, name) VALUES
(1, 'Référent C');

INSERT INTO privilege
(id, name,libelle)VALUES
(1, 'READ_PRIVILEGE','Lecteur'),
(2, 'WRITE_PRIVILEGE','Editeur');

INSERT INTO role
(id, name,libelle) VALUES
(1, 'ROLE_WEBMASTER','Webmaster'),
(2, 'ROLE_ADMIN','Administrateur'),
(3, 'ROLE_USER','Référent Agence'),
(4, 'ROLE_VISITOR','Visiteur');

INSERT INTO roles_privileges
( role_id,privilege_id)
VALUES
(1, 1 ),
(1, 2 ),
(2, 1 ),
(2, 2 ),
(3, 1 ),
(3, 2 ),
(4, 1 );

INSERT INTO user_entreprise(
	id, account_no_expired, account_no_locked, cell_phone, date_create, email, enabled, first_name, last_connection, last_name, nni, office_phone, password, token_no_expired, id_fonction, id_organization, id_role, id_site)
	VALUES
	(1, true , true , '0600000', '2019-12-16 09:00:00', 'h1111@agence.fr',
	 true , 'romain', '2019-12-16 09:00:00', 'david', 'h11111', '070000', '$2a$10$mVLweGs6HLxItvuYt5W21e9sr7sgkqeuk6q.3pke4HEHJZry4fWSO',
	 true ,1, 1, 1, 1),
	 (2, true , true , '0600000', '2019-12-16 09:00:00', 'h22222@agence.fr',
	 true , 'Cédric', '2019-12-16 09:00:00', 'H', 'h22222', '070000', '$2a$10$mVLweGs6HLxItvuYt5W21e9sr7sgkqeuk6q.3pke4HEHJZry4fWSO',
	 true ,1, 1, 1, 1),
	 (3, true , true , '0600000', '2019-12-16 09:00:00', 'f33333@agence.fr',
	 true , 'Gaelle', '2019-12-16 09:00:00', 'B', 'f33333', '070000', '$2a$10$mVLweGs6HLxItvuYt5W21e9sr7sgkqeuk6q.3pke4HEHJZry4fWSO',
	 true ,1, 1, 1, 1);

ALTER SEQUENCE hibernate_sequence RESTART WITH 100;