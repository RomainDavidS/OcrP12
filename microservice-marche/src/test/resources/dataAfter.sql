DELETE FROM indemnisation;
DELETE FROM marche ;
DELETE FROM type_marche;

INSERT INTO type_marche
(id, gender, name) VALUES

(1, 'Pose C' , '10 000 C'),
(2, 'Pose C' , '20 000 C'),
(3, 'Pose K','Pose K'),
(4, 'Recyclage', 'Recyclage'),
(5, 'Saturation' , 'Saturation'),
(6, 'Visite Qualité' , 'VQD AL_01');



INSERT INTO marche(
	id, client, date_closed, date_end, date_start, e_travaux, fournisseur, id_organization, id_prestataire, id_ref1, id_ref2, id_site, id_sous_traitant, name, numero, type_marche_id)
	VALUES
	(1,785896, null, '2021-12-31 00:00:00', '2018-01-01 00:00:00', 'EC4ARCAL08', 124556, 1, 3, 2, 3, 1, 2, 'C_04_A', 'EC4ARCAL08', 2 ),
	(2,785896, null, '2021-12-31 00:00:00', '2018-01-01 00:00:00', 'EC4ARCAL08', 124556, 1, 3, 2, 3, 1, 2, 'C_03_A', 'EC4ARCAL08', 2 );

INSERT INTO indemnisation(
	id, action, amount, client_name, closed_date, edp_comment, entreprise_comment, pdl, reclamation, reclamation_date, return_edp_date, send_edp_date, status, marche_id)
	VALUES (1, '', 100.00, 'client', '2019-11-01 00:00:00', 'comment edp', 'comment entreprise',  '12345647897', 'reclamation', '2019-09-01 00:00:00', '2019-09-15 00:00:00', '2019-09-01 00:00:00', 'En cours', 1);

ALTER SEQUENCE hibernate_sequence RESTART WITH 100;