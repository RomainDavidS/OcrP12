DELETE FROM user_entreprise;
DELETE FROM organization;
DELETE FROM fonction;
DELETE FROM site;
DELETE FROM type_site;
DELETE FROM roles_privileges;
DELETE FROM role;
DELETE FROM privilege;

ALTER SEQUENCE hibernate_sequence RESTART WITH 100;