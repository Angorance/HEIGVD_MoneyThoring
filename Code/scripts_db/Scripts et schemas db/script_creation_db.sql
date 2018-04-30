-- -----------------------------------------------------
-- Schema moneythoring
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `moneythoring`;
CREATE SCHEMA `moneythoring` DEFAULT CHARACTER SET utf8 ;
USE `moneythoring` ;

-- -----------------------------------------------------
-- Table `moneythoring`.`Client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`Client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(250) NOT NULL,
  `isActivated` TINYINT NOT NULL,
  `activationKey` VARCHAR(50) NULL,
  `salt` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)
);


-- -----------------------------------------------------
-- Table `moneythoring`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`Category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `colour` VARCHAR(50) NOT NULL,
  `isDefault` TINYINT NOT NULL DEFAULT 0,
  `Client_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Category_Client1_idx` (`Client_id` ASC),
  CONSTRAINT `fk_Category_Client1`
    FOREIGN KEY (`Client_id`)
    REFERENCES `moneythoring`.`Client` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table `moneythoring`.`Budget`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`Budget` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `amount` DOUBLE NOT NULL,
  `isShared` TINYINT NOT NULL DEFAULT 0,
  `isRecurrent` TINYINT NOT NULL,
  `startingDate` DATETIME NOT NULL,
  `endingDate` DATETIME NOT NULL,
  `gap` INT NULL,
  `Client_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Budget_Client1_idx` (`Client_id` ASC),
  CONSTRAINT `fk_Budget_Client1`
    FOREIGN KEY (`Client_id`)
    REFERENCES `moneythoring`.`Client` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table `moneythoring`.`BankAccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`BankAccount` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `nameBank` VARCHAR(50) NULL,
  `typeAccount` VARCHAR(100) NOT NULL,
  `amount` DOUBLE NOT NULL,
  `isDefault` TINYINT NOT NULL,
  `isVisible` TINYINT NOT NULL,
  `Client_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_BankAccount_Client1_idx` (`Client_id` ASC),
  CONSTRAINT `fk_BankAccount_Client1`
    FOREIGN KEY (`Client_id`)
    REFERENCES `moneythoring`.`Client` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table `moneythoring`.`IOTransaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`IOTransaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(255) NULL,
  `date` DATETIME NOT NULL,
  `amount` DOUBLE NOT NULL,
  `currency` VARCHAR(50) NOT NULL,
  `isIncome` TINYINT NOT NULL,
  `Category_id` INT NOT NULL,
  `BankAccount_id` INT NOT NULL,
  `Budget_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_IOTransaction_Category1_idx` (`Category_id` ASC),
  INDEX `fk_IOTransaction_BankAccount1_idx` (`BankAccount_id` ASC),
  INDEX `fk_IOTransaction_Budget1_idx` (`Budget_id` ASC),
  CONSTRAINT `fk_IOTransaction_Category1`
    FOREIGN KEY (`Category_id`)
    REFERENCES `moneythoring`.`Category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_IOTransaction_BankAccount1`
    FOREIGN KEY (`BankAccount_id`)
    REFERENCES `moneythoring`.`BankAccount` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_IOTransaction_Budget1`
    FOREIGN KEY (`Budget_id`)
    REFERENCES `moneythoring`.`Budget` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table `moneythoring`.`Recurrence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`Recurrence` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `gap` INT NOT NULL,
  `nextDate` DATETIME NOT NULL,
  `IOTransaction_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Recurrence_IOTransaction1_idx` (`IOTransaction_id` ASC),
  CONSTRAINT `fk_Recurrence_IOTransaction1`
    FOREIGN KEY (`IOTransaction_id`)
    REFERENCES `moneythoring`.`IOTransaction` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table `moneythoring`.`Debt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`Debt` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(255) NULL,
  `amount` DOUBLE NOT NULL,
  `isIncome` TINYINT NOT NULL,
  `expirationDate` DATETIME NOT NULL,
  `Client_id` INT NOT NULL,
  `Client_id1` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Dept_Client1_idx` (`Client_id` ASC),
  INDEX `fk_Debt_Client1_idx` (`Client_id1` ASC),
  CONSTRAINT `fk_Dept_Client1`
    FOREIGN KEY (`Client_id`)
    REFERENCES `moneythoring`.`Client` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Debt_Client1`
    FOREIGN KEY (`Client_id1`)
    REFERENCES `moneythoring`.`Client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table `moneythoring`.`SharedBudget`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`SharedBudget` (
  `Client_id` INT NOT NULL,
  `Budget_id` INT NOT NULL,
  PRIMARY KEY (`Client_id`, `Budget_id`),
  INDEX `fk_Client_has_Budget_Budget1_idx` (`Budget_id` ASC),
  INDEX `fk_Client_has_Budget_Client1_idx` (`Client_id` ASC),
  CONSTRAINT `fk_Client_has_Budget_Client1`
    FOREIGN KEY (`Client_id`)
    REFERENCES `moneythoring`.`Client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Client_has_Budget_Budget1`
    FOREIGN KEY (`Budget_id`)
    REFERENCES `moneythoring`.`Budget` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table `moneythoring`.`CategoriesBudget`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`CategoriesBudget` (
  `Category_id` INT NOT NULL,
  `Budget_id` INT NOT NULL,
  PRIMARY KEY (`Category_id`, `Budget_id`),
  INDEX `fk_Category_has_Budget_Budget1_idx` (`Budget_id` ASC),
  INDEX `fk_Category_has_Budget_Category1_idx` (`Category_id` ASC),
  CONSTRAINT `fk_Category_has_Budget_Category1`
    FOREIGN KEY (`Category_id`)
    REFERENCES `moneythoring`.`Category` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Category_has_Budget_Budget1`
    FOREIGN KEY (`Budget_id`)
    REFERENCES `moneythoring`.`Budget` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
 );