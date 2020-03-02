DELETE FROM fonction_prestataire;
DELETE FROM employee;
DELETE FROM site_prestataire;
DELETE FROM entreprise;
DELETE FROM site_office;
DELETE FROM call_center;

ALTER SEQUENCE hibernate_sequence RESTART WITH 100;