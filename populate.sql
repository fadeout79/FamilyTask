INSERT INTO person (name, dateOfBirth, imagePath) VALUES ('Marilune', '2007-12-20','marilune.jpg');
INSERT INTO person (name, dateOfBirth) VALUES ('Thiéry', '2009-09-20');
INSERT INTO person (name, dateOfBirth) VALUES ('Maud', '1999-06-20');
INSERT INTO person (name, dateOfBirth) VALUES ('Mégane', '2012-07-13');
INSERT INTO person (name, dateOfBirth, isParent) VALUES ('Dominique', '1977-07-07', TRUE);
INSERT INTO person (name, dateOfBirth, isParent) VALUES ('Ian', '1979-07-30', TRUE);
INSERT INTO tasks (personId, summary, description, recurrence, points, fixDay, startDate) VALUES (1, 'Vider le lave-vaisselle', 'Vider le lave-vaisselle lorsqu&quote;il est plein', 0, 5, 0, '2015-09-20');
INSERT INTO tasks (personId, summary, description, recurrence, points, fixDay, startDate, image) VALUES (1, 'Se brosser les cheveux', 'Se brosser les cheveux matin et soir', 1, 2, 0, '2015-09-20', 'se-brosser-cheveux.png');
INSERT INTO tasks (personId, summary, description, recurrence, points, fixDay, startDate) VALUES (2, 'Mettre la table', 'Mettre la table avant le souper', 1, 5, 0, '2015-09-20');
INSERT INTO tasks (personId, summary, description, recurrence, points, fixDay, startDate) VALUES (3, 'Sortir les vidanges', 'Sortir les vidanges les lundi soirs', 1, 5, 0, '2015-09-20');
INSERT INTO todoTasks (	tasksId, nextDate, done ) VALUES (1, '2015-09-18', TRUE);
INSERT INTO todoTasks (	tasksId, nextDate ) VALUES (2, '2015-09-18');
INSERT INTO todoTasks (	tasksId, nextDate ) VALUES (3, '2015-09-18');
INSERT INTO todoTasks (	tasksId, nextDate ) VALUES (4, '2015-09-18');
INSERT INTO reward (summary, description, points) VALUES ('Temps Maman', 'Passer 1 heure avec maman', 5);
INSERT INTO reward (summary, description, points) VALUES ('Délice glacé', 'Boisson glacée (slush, cappucino, etc.', 10);
INSERT INTO reward (summary, description, points) VALUES ('Croustilles', 'Petit sac de croustilles', 2);
