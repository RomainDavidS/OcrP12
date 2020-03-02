DELETE FROM fonction_prestataire;
DELETE FROM employee;
DELETE FROM site_prestataire;
DELETE FROM entreprise;
DELETE FROM site_office;
DELETE FROM call_center;

INSERT INTO entreprise(
	id, adress_postale_adress, adress_postale_commune, adress_postale_complement, enabled, name, siret)
	VALUES
	(1, '7 CHE DE LA DHUY', '38240 MEYLAN', '', true , 'SERA', '48986932100021'),
	(2, '39 BD ORNANO', '93200 SAINT-DENIS', '39-47-PLEYAD', true , 'SOLUTIONS 30 SE', '79524592700038'),
	(3, '15 TRA DES BRUCS', '06560 VALBONNE', 'ZAC NUMERO 1 LES BOUILLIDES', true , 'CPCP TELECOM', '43389172800126');

INSERT INTO call_center(
	id, enabled, name, opening_time, phone)
	VALUES (1, true, 'Call center S30', 'de 8h à 20h du lundi au samedi', '0800941000');

INSERT INTO site_prestataire(
	id, call_center_id, adress_local_adress, adress_local_commune, adress_local_complement, adress_postale_adress, adress_postale_commune, adress_postale_complement, code_zs, enabled, name,entreprise_id)
	VALUES
	(1,1, '103 rue des alpes', '74300 THIEZ', '', '103 rue des alpes', '74300 THIEZ', '','7000023', true , 'Thiez',2);

INSERT INTO fonction_prestataire(
	id, name)
	VALUES
	(1, 'Poseur'),
	(2, 'Poseur Référent');
ALTER SEQUENCE hibernate_sequence RESTART WITH 100;





