-- -- le mot de passe de chaque user est 12345
INSERT INTO USER (ID,ACTIVE,EMAIL,LAST_NAME,NAME,PASSWORD) VALUES (100,TRUE,'alain@a.a','Admin','Alain','$2a$10$XAOiM6rLRrzMfF4jNsQc7O8jurI4BORxgh6cEGV0k9gymUfjYZx9a');
INSERT INTO USER (ID,ACTIVE,EMAIL,LAST_NAME,NAME,PASSWORD) VALUES (200,TRUE,'ursula@a.a','User','Ursula','$2a$10$2rCrmX.jiEAF1cLdDJduBOAj2qiim6bu9mKF86I3YvrHwNJqvijYW');
INSERT INTO USER (ID,ACTIVE,EMAIL,LAST_NAME,NAME,PASSWORD) VALUES (300,TRUE,'maurice@a.a','Membre','Maurice','$2a$10$jUmvMaNIcH6xE3M8TRp6suTYNmBR08/SBXQ.7iiyTZCa3IWG1o0q6');
INSERT INTO USER (ID,ACTIVE,EMAIL,LAST_NAME,NAME,PASSWORD) VALUES (400,TRUE,'u2@a.a','User2','Ulrich','$2a$10$r5LYG4bcXnFQfXSUFXWLIu8rzSqdOfHPD/SZ9/jJj6aZSXjpAtAsu');
INSERT INTO USER (ID,ACTIVE,EMAIL,LAST_NAME,NAME,PASSWORD) VALUES (500,TRUE,'u3@a.a','User3','Urbain','$2a$10$KicK13YZIjKkmptOwXACg.85TD6d8zcfxN2WcgEWbku5asdgfdvcu');
INSERT INTO USER (ID,ACTIVE,EMAIL,LAST_NAME,NAME,PASSWORD) VALUES (600,TRUE,'m1@a.a','Membre1','Manu','$2a$10$6HdfSXRm2fhvLc6BJQEmPunFIc6hWY94X9/DLQUaHNvc5AAyq/vLO');
INSERT INTO USER (ID,ACTIVE,EMAIL,LAST_NAME,NAME,PASSWORD) VALUES (601,TRUE,'a@a.a','User','a','$2a$10$/H.hSoh2tn5PZ5Kdcsf1P.gnha7q3u.TQv4ghs/XnxNnd26Z5tuJm');

INSERT INTO ROLE (ROLE_ID,ROLE) VALUES (101,'ADMIN');
INSERT INTO ROLE (ROLE_ID,ROLE) VALUES (201,'USER');
INSERT INTO ROLE (ROLE_ID,ROLE) VALUES (301,'PERSONNEL');

INSERT INTO USER_ROLES (USER_ID,ROLES_ROLE_ID) VALUES (100,101);
INSERT INTO USER_ROLES (USER_ID,ROLES_ROLE_ID) VALUES (200,201);
INSERT INTO USER_ROLES (USER_ID,ROLES_ROLE_ID) VALUES (300,301);
INSERT INTO USER_ROLES (USER_ID,ROLES_ROLE_ID) VALUES (400,201);
INSERT INTO USER_ROLES (USER_ID,ROLES_ROLE_ID) VALUES (500,201);
INSERT INTO USER_ROLES (USER_ID,ROLES_ROLE_ID) VALUES (600,301);
INSERT INTO USER_ROLES (USER_ID,ROLES_ROLE_ID) VALUES (601,201);

INSERT INTO OUVRAGE (OUVRAGE_ID,OUVRAGE_AUTEUR,OUVRAGE_LOCALISATION,OUVRAGE_QUANTITE,OUVRAGE_REFERENCE,OUVRAGE_RESUME,OUVRAGE_STYLE,OUVRAGE_TITRE)
VALUES (1000,'Auteur 1000','Etage 1000 Casier 1000',1,'REF1000','C''est l''histoire d''un mec ...','Humour','The story of a guy');
INSERT INTO OUVRAGE (OUVRAGE_ID,OUVRAGE_AUTEUR,OUVRAGE_LOCALISATION,OUVRAGE_QUANTITE,OUVRAGE_REFERENCE,OUVRAGE_RESUME,OUVRAGE_STYLE,OUVRAGE_TITRE)
VALUES (2000,'Auteur 2000','Etage 2000 Casier 2000',2,'REF2000','C''est l''histoire de 2 mecs ...','Amour','The story of a guy 2');
INSERT INTO OUVRAGE (OUVRAGE_ID,OUVRAGE_AUTEUR,OUVRAGE_LOCALISATION,OUVRAGE_QUANTITE,OUVRAGE_REFERENCE,OUVRAGE_RESUME,OUVRAGE_STYLE,OUVRAGE_TITRE)
VALUES (3000,'Auteur 3000','Etage 3000 Casier 3000',3,'REF3000','C''est l''histoire de 3 mecs ...','Thriller','The story of a guy 3');
INSERT INTO OUVRAGE (OUVRAGE_ID,OUVRAGE_AUTEUR,OUVRAGE_LOCALISATION,OUVRAGE_QUANTITE,OUVRAGE_REFERENCE,OUVRAGE_RESUME,OUVRAGE_STYLE,OUVRAGE_TITRE)
VALUES (4000,'Auteur 4000','Etage 4000 Casier 4000',4,'REF4000','C''est l''histoire de 4 mecs ...','Amour','The story of a guy 4');
INSERT INTO OUVRAGE (OUVRAGE_ID,OUVRAGE_AUTEUR,OUVRAGE_LOCALISATION,OUVRAGE_QUANTITE,OUVRAGE_REFERENCE,OUVRAGE_RESUME,OUVRAGE_STYLE,OUVRAGE_TITRE)
VALUES (5000,'Auteur 5000','Etage 5000 Casier 5000',0,'REF5000','C''est l''histoire de 5 mecs ...','Humour','The story of a guy 5');

INSERT INTO EMPRUNT (EMPRUNT_ID,EMPRUNT_DATE_DEBUT,EMPRUNT_DATE_FIN,EMPRUNT_DATE_PROLONGATION,EMPRUNT_DATE_RETOUR,EMPRUNT_DATE_RELANCE,OUVRAGE_OUVRAGE_ID,USER_ID,EMPRUNT_PROLONGATION, EMPRUNT_RELANCE, EMPRUNT_RENDU )
VALUES (6000,'2020-01-02 14:00:00.000','2020-01-30 14:00:00.000',NULL,NULL,NULL,2000,200,FALSE,FALSE,FALSE);
INSERT INTO EMPRUNT (EMPRUNT_ID,EMPRUNT_DATE_DEBUT,EMPRUNT_DATE_FIN,EMPRUNT_DATE_PROLONGATION,EMPRUNT_DATE_RETOUR,EMPRUNT_DATE_RELANCE,OUVRAGE_OUVRAGE_ID,USER_ID,EMPRUNT_PROLONGATION, EMPRUNT_RELANCE, EMPRUNT_RENDU )
VALUES (7000,'2020-01-02 14:00:00.000','2020-01-30 14:00:00.000',NULL,'2020-03-03 14:00:00.000',NULL,3000,400,FALSE,TRUE,FALSE);
INSERT INTO EMPRUNT (EMPRUNT_ID,EMPRUNT_DATE_DEBUT,EMPRUNT_DATE_FIN,EMPRUNT_DATE_PROLONGATION,EMPRUNT_DATE_RETOUR,EMPRUNT_DATE_RELANCE,OUVRAGE_OUVRAGE_ID,USER_ID,EMPRUNT_PROLONGATION, EMPRUNT_RELANCE, EMPRUNT_RENDU )
VALUES (8000,'2020-01-02 14:00:00.000','2020-01-30 14:00:00.000','2020-02-27 14:00:00.000',NULL,NULL,4000,400,TRUE,FALSE,FALSE);
