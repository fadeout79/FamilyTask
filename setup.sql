DROP TABLE IF EXISTS family;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS todoTasks;
DROP TABLE IF EXISTS taskPoints;
DROP TABLE IF EXISTS reward;
DROP TABLE IF EXISTS spentRewards;
DROP TABLE IF EXISTS taskPackages;
DROP VIEW IF EXISTS accumulatedPoints;
DROP VIEW IF EXISTS spentPoints;
DROP VIEW IF EXISTS activeTasks;

CREATE TABLE family (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE person (
	id INT NOT NULL AUTO_INCREMENT,
	familyId INT NOT NULL,
	name VARCHAR(50) NOT NULL,
	dateOfBirth DATE,
	isParent BOOLEAN DEFAULT FALSE,
	imagePath VARCHAR(255) NULL,
	PRIMARY KEY (id)
);

CREATE TABLE tasks (
	id INT NOT NULL AUTO_INCREMENT,
	personId INT NULL DEFAULT NULL,
	summary VARCHAR(50) NULL DEFAULT NULL,
	description VARCHAR(255) NULL DEFAULT NULL,
	recurrence INT NULL DEFAULT 0,
	points INT NULL DEFAULT NULL,
	fixDay BOOLEAN DEFAULT FALSE,
	startDate DATE DEFAULT NULL,
	image VARCHAR(255) NULL,
	PRIMARY KEY (id)
);

CREATE TABLE todoTasks (
	id INT NOT NULL AUTO_INCREMENT,
	tasksId INT NOT NULL,
	nextDate DATE DEFAULT NULL,
	done BOOLEAN DEFAULT FALSE,
	doneByPersonId INT NULL DEFAULT 0,
	checked BOOLEAN DEFAULT FALSE,
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

CREATE TABLE reward (
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

CREATE TABLE taskPackage (
	id INT NOT NULL AUTO_INCREMENT,
	tasksId INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE VIEW activeTasks AS 
	SELECT todoTasks.id, todoTasks.tasksId, summary, description, points, personId, done, image
	FROM tasks 
	INNER JOIN todoTasks 
	WHERE tasks.id = todoTasks.tasksId AND NOT (done AND checked);
	
CREATE VIEW accumulatedPoints AS
	SELECT Sum(points), taskPoints.personId
	FROM tasks 
	INNER JOIN taskPoints
	WHERE tasks.id = taskPoints.tasksId
	GROUP BY taskPoints.personId;
	
CREATE VIEW spentPoints AS
	SELECT Sum(points), spentRewards.personId 
	FROM reward 
	INNER JOIN spentRewards
	WHERE reward.id = spentRewards.rewardsId
	GROUP BY spentRewards.personId;
	      


