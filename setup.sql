CREATE TABLE 'person' (
	`id` INT (10) NULL DEFAULT NULL,
	`name` VARCHAR(50) NULL DEFAULT NULL,
	`dateOfBirth` TIMESTAMP NULL DEFAULT NULL
)

CREATE TABLE 'tasks' (
	`id` INT (10) NULL DEFAULT NULL,
	`personId` INT (10) NULL DEFAULT NULL,
	`summary` VARCHAR(50) NULL DEFAULT NULL,
	`description` VARCHAR(255) NULL DEFAULT NULL,
	`isRedundant` BOOLEAN NULL DEFAULT FALSE,
	`points` INT (10) NULL DEFAULT NULL
)

CREATE TABLE 'taskPoints' (
	`id` INT (10) NULL DEFAULT NULL,
	`personId` INT (10) NULL DEFAULT NULL,
	`tasksId` INT (10) NULL DEFAULT NULL,
	`multiplicationFactor` DOUBLE NULL DEFAULT NULL,
	`dateEarned` TIMESTAMP NULL DEFAULT NULL 
)

CREATE TABLE 'rewards' (
	`id` INT (10) NULL DEFAULT NULL,
	`summary` VARCHAR(50) NULL DEFAULT NULL,
	`description` VARCHAR(255) NULL DEFAULT NULL,
	`points` INT (10) NULL DEFAULT NULL
)

CREATE TABLE 'spentRewards' (	
	`id` INT (10) NULL DEFAULT NULL,
	`personId` INT (10) NULL DEFAULT NULL,
	`rewardsId` INT (10) NULL DEFAULT NULL
)


DELIMITER //
CREATE PROCEDURE availablePoints ( IN personId INT, 
								   OUT availablePoints INT
								   )
BEGIN
	DECLARE pointsWon INT;
	DECLARE pointsSpent INT;
	
	SELECT Sum(points) 
	FROM tasks 
	INNER JOIN taskPoints
	WHERE tasks.id = taskPoints.tasksId AND
	      taskPoints.personId = personId
	INTO pointsWon;
	
	SELECT Sum(points) 
	FROM rewards 
	INNER JOIN spentRewards
	WHERE rewards.id = spentRewards.rewardsId AND
	      spentRewards.personId = personId
	INTO pointsSpent;
	
	avalablePoints = pointsWon - pointsSpent;
END //
DELIMITER ;

