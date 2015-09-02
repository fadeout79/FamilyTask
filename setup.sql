DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS taskPoints;
DROP TABLE IF EXISTS rewards;
DROP TABLE IF EXISTS spentRewards;
DROP VIEW IF EXISTS accumulatedPoints;
DROP VIEW IF EXISTS spentPoints;

CREATE TABLE person (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	dateOfBirth DATE,
	isParent BOOLEAN DEFAULT FALSE,
	PRIMARY KEY (id)
);

CREATE TABLE tasks (
	id INT NOT NULL AUTO_INCREMENT,
	personId INT NULL DEFAULT NULL,
	summary VARCHAR(50) NULL DEFAULT NULL,
	description VARCHAR(255) NULL DEFAULT NULL,
	isRedundant BOOLEAN NULL DEFAULT FALSE,
	points INT NULL DEFAULT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE taskPoints (
	id INT NOT NULL AUTO_INCREMENT,
	personId INT NOT NULL,
	tasksId INT NOT NULL,
	multiplicationFactor DOUBLE NOT NULL,
	dateEarned TIMESTAMP NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE rewards (
	id INT NOT NULL AUTO_INCREMENT,
	summary VARCHAR(50) NOT NULL,
	description VARCHAR(255) NOT NULL,
	points INT (10) NULL,
	PRIMARY KEY (id)
);

CREATE TABLE spentRewards (	
	id INT NOT NULL AUTO_INCREMENT,
	personId INT (10) NULL,
	rewardsId INT (10) NULL,
	PRIMARY KEY (id)
);

CREATE VIEW accumulatedPoints AS
	SELECT Sum(points), taskPoints.personId
	FROM tasks 
	INNER JOIN taskPoints
	WHERE tasks.id = taskPoints.tasksId
	GROUP BY taskPoints.personId;
	
CREATE VIEW spentPoints AS
	SELECT Sum(points), spentRewards.personId 
	FROM rewards 
	INNER JOIN spentRewards
	WHERE rewards.id = spentRewards.rewardsId
	GROUP BY spentRewards.personId;
	      


