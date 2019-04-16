INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES(nextval('kredit_seq'), 'Kes kredit', 'KK','Dinarski i evro kes krediti');
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES(nextval('kredit_seq'), 'Krediti za refinansiranje', 'KZR','Dinarski i evro krediti za refinansiranje');
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES(nextval('kredit_seq'), 'Potrošački krediti', 'PK','Dinarski i evro potrosacki krediti');
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES(nextval('kredit_seq'), 'Auto krediti', 'AK','Dinarski i evro auto krediti');
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES(nextval('kredit_seq'), 'Stambeni krediti', 'SK','Dinarski i evro auto krediti');
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES(nextval('kredit_seq'), 'Krediti za renoviranje', 'KR','Dinarski i evro krediti za renoviranje');
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES(-100, 'Naziv TEST', 'Oznaka TEST', 'Opis TEST');

INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES(nextval('tip_racuna_seq'), 'Tekuci racun', 'TR', 'Tekuci racun');
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES(nextval('tip_racuna_seq'), 'Ziro racun', 'ZR', 'Ziro racun');
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES(nextval('tip_racuna_seq'), 'Racuni orocenog depozita', 'ROD', 'Racuni orocenog deopzita');
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES(nextval('tip_racuna_seq'), 'Stedni racuni', 'SR', 'Stedni racuni');
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES(nextval('tip_racuna_seq'), 'Namenski racuni', 'NR', 'Namenski racuni');
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES(nextval('tip_racuna_seq'), 'Devizni racuni', 'DR', 'Devizni racuni');
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES(-100, 'Naziv TEST', 'Oznaka TEST', 'Opis TEST');

INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (nextval('klijent_seq'), 'Nadja', 'Jovanovic', 12564478, 5);
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (nextval('klijent_seq'), 'Vanja', 'Vukadinovic', 596325485, 1);
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (nextval('klijent_seq'), 'Ana', 'Prokopic', 125963222, 3);
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (nextval('klijent_seq'), 'Sladjana', 'Popadic', 323596674, 6);
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (nextval('klijent_seq'), 'Sara', 'Lazic', 3655996, 4);
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES (nextval('klijent_seq'), 'Vanja', 'Radovic', 887456952, 2);

INSERT INTO "racun"("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
VALUES (nextval('racun_seq'), 'Studentski racun', 'STR', 'Racuni za studente', 1,2);
INSERT INTO "racun"("id", "naziv", "oznaka", "opis", "tip_racuna", "klijent")
VALUES (nextval('racun_seq'), 'Racun za nezaposlene', 'RZN', 'Racuni za nezaposlene', 4,5);