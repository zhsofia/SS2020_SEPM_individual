-- insert initial test data
-- the id is hardcode to enable references between further test data
MERGE INTO owner (ID, NAME, CREATED_AT, UPDATED_AT)
VALUES (1, 'Yagura', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (2, 'Yato', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (3, 'Kim', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (4, 'Berta',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (5, 'Brut',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (6, 'Aang',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (7, 'Kaguya',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (8, 'Daenerys Stormborn of the House Targaryen, the Unburnt, Queen of the Andals and the First Men, Queen of Meereen, Khaleesi of the Great Grass Sea, Protector of the Realm, Lady Regent of the Seven Kingdoms, Breaker of Chains and Mother of Dragons',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (9, 'Khal Drogo',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (10, 'Heisenberg',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (11, 'Saol Goodman',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
       (12, 'Howl', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

MERGE INTO horse (ID,NAME,DESCRIPTION,RACE,RATING,BIRTH_DATE, CREATED_AT, UPDATED_AT,ID_OF_OWNER)
VALUES (1,'Isobu','Three-tailed beast','PAINT',4,'2006-11-02',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),1),
       (2,'Yuki','Nice katana','APPALOOSA',2,'2010-02-01',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),2),
       (3,'Fire','hot','ARABIAN',4,'2009-03-19',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),6),
       (4,'Water','nice','ARABIAN',4,'2009-03-12',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),6),
       (5,'Wind','cool','ARABIAN',4,'2009-03-13',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),6),
       (6,'Earth','strong','ARABIAN',4,'2009-03-29',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),6),
       (7,'Momo','has big ears','MORGAN',5,'2009-03-19',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),6),
       (8,'Appa','flying bison','MORGAN',5,'2009-03-19',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),6),
       (9, 'Madara','should had been the main villian...','PAINT',5,'2004-09-09',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),7),
       (10, 'John Snow','King in the North','ARABIAN',4,'2010-02-03',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),8),
       (11, 'Jessy', null,'PAINT',3,'2008-10-11',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),10),
       (12, 'Calcifer','Fire Demon','ARABIAN',4,'2004-10-11',CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(),12);