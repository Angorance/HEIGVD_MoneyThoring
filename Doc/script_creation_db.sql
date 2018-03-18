-- -----------------------------------------------------
-- Schema moneythoring
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema moneythoring
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `moneythoring`;
CREATE SCHEMA `moneythoring` DEFAULT CHARACTER SET utf8 ;
USE `moneythoring` ;

-- -----------------------------------------------------
-- Table `moneythoring`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `isActivated` TINYINT NOT NULL,
  `activationKey` VARCHAR(50) NULL,
  `salt` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`Category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `colour` VARCHAR(50) NOT NULL,
  `isDefault` TINYINT NOT NULL DEFAULT 0,
  `User_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Category_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_Category_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `moneythoring`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`Budget`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`Budget` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `amount` DOUBLE NOT NULL,
  `currency` VARCHAR(50) NOT NULL,
  `isShared` TINYINT NOT NULL DEFAULT 0,
  `isRecurrent` TINYINT NOT NULL,
  `startingDate` DATETIME NULL,
  `endingDate` DATETIME NULL,
  `gap` INT NULL,
  `User_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Budget_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_Budget_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `moneythoring`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`TypeAccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`TypeAccount` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`BankAccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`BankAccount` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `nameBank` VARCHAR(50) NULL,
  `amount` DOUBLE NOT NULL,
  `interest` DOUBLE NULL,
  `currency` VARCHAR(50) NOT NULL,
  `isDefault` TINYINT NOT NULL,
  `TypeAccount_id` INT NOT NULL,
  `User_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_BankAccount_TypeAccount_idx` (`TypeAccount_id` ASC),
  INDEX `fk_BankAccount_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_BankAccount_TypeAccount`
    FOREIGN KEY (`TypeAccount_id`)
    REFERENCES `moneythoring`.`TypeAccount` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BankAccount_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `moneythoring`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`Transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`Transaction` (
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
  INDEX `fk_Transaction_Category1_idx` (`Category_id` ASC),
  INDEX `fk_Transaction_BankAccount1_idx` (`BankAccount_id` ASC),
  INDEX `fk_Transaction_Budget1_idx` (`Budget_id` ASC),
  CONSTRAINT `fk_Transaction_Category1`
    FOREIGN KEY (`Category_id`)
    REFERENCES `moneythoring`.`Category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_BankAccount1`
    FOREIGN KEY (`BankAccount_id`)
    REFERENCES `moneythoring`.`BankAccount` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_Budget1`
    FOREIGN KEY (`Budget_id`)
    REFERENCES `moneythoring`.`Budget` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`Recurrence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`Recurrence` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `gap` INT NOT NULL,
  `nextDate` DATETIME NOT NULL,
  `Transaction_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Recurrence_Transaction1_idx` (`Transaction_id` ASC),
  CONSTRAINT `fk_Recurrence_Transaction1`
    FOREIGN KEY (`Transaction_id`)
    REFERENCES `moneythoring`.`Transaction` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`Debt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`Debt` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(255) NULL,
  `amount` DOUBLE NOT NULL,
  `interest` DOUBLE NOT NULL,
  `currency` VARCHAR(50) NOT NULL,
  `isIncome` TINYINT NOT NULL,
  `expirationDate` DATETIME NOT NULL,
  `User_id` INT NOT NULL,
  `User_id1` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Dept_User1_idx` (`User_id` ASC),
  INDEX `fk_Dept_User2_idx` (`User_id1` ASC),
  CONSTRAINT `fk_Dept_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `moneythoring`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Dept_User2`
    FOREIGN KEY (`User_id1`)
    REFERENCES `moneythoring`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`SharedBudget`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`SharedBudget` (
  `User_id` INT NOT NULL,
  `Budget_id` INT NOT NULL,
  PRIMARY KEY (`User_id`, `Budget_id`),
  INDEX `fk_User_has_Budget_Budget1_idx` (`Budget_id` ASC),
  INDEX `fk_User_has_Budget_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_User_has_Budget_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `moneythoring`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Budget_Budget1`
    FOREIGN KEY (`Budget_id`)
    REFERENCES `moneythoring`.`Budget` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Category_has_Budget_Budget1`
    FOREIGN KEY (`Budget_id`)
    REFERENCES `moneythoring`.`Budget` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Schema moneythoring
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema moneythoring
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `moneythoring`;
CREATE SCHEMA `moneythoring` DEFAULT CHARACTER SET utf8 ;
USE `moneythoring` ;

-- -----------------------------------------------------
-- Table `moneythoring`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `isActivated` TINYINT NOT NULL,
  `activationKey` VARCHAR(50) NULL,
  `salt` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`Category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `colour` VARCHAR(50) NOT NULL,
  `isDefault` TINYINT NOT NULL DEFAULT 0,
  `User_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Category_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_Category_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `moneythoring`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`Budget`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`Budget` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `amount` DOUBLE NOT NULL,
  `currency` VARCHAR(50) NOT NULL,
  `isShared` TINYINT NOT NULL DEFAULT 0,
  `isRecurrent` TINYINT NOT NULL,
  `startingDate` DATETIME NULL,
  `endingDate` DATETIME NULL,
  `gap` INT NULL,
  `User_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Budget_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_Budget_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `moneythoring`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`TypeAccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`TypeAccount` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`BankAccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`BankAccount` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `nameBank` VARCHAR(50) NULL,
  `amount` DOUBLE NOT NULL,
  `interest` DOUBLE NULL,
  `currency` VARCHAR(50) NOT NULL,
  `isDefault` TINYINT NOT NULL,
  `TypeAccount_id` INT NOT NULL,
  `User_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_BankAccount_TypeAccount_idx` (`TypeAccount_id` ASC),
  INDEX `fk_BankAccount_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_BankAccount_TypeAccount`
    FOREIGN KEY (`TypeAccount_id`)
    REFERENCES `moneythoring`.`TypeAccount` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BankAccount_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `moneythoring`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`Transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`Transaction` (
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
  INDEX `fk_Transaction_Category1_idx` (`Category_id` ASC),
  INDEX `fk_Transaction_BankAccount1_idx` (`BankAccount_id` ASC),
  INDEX `fk_Transaction_Budget1_idx` (`Budget_id` ASC),
  CONSTRAINT `fk_Transaction_Category1`
    FOREIGN KEY (`Category_id`)
    REFERENCES `moneythoring`.`Category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_BankAccount1`
    FOREIGN KEY (`BankAccount_id`)
    REFERENCES `moneythoring`.`BankAccount` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Transaction_Budget1`
    FOREIGN KEY (`Budget_id`)
    REFERENCES `moneythoring`.`Budget` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`Recurrence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`Recurrence` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `gap` INT NOT NULL,
  `nextDate` DATETIME NOT NULL,
  `Transaction_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Recurrence_Transaction1_idx` (`Transaction_id` ASC),
  CONSTRAINT `fk_Recurrence_Transaction1`
    FOREIGN KEY (`Transaction_id`)
    REFERENCES `moneythoring`.`Transaction` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`Debt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`Debt` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(255) NULL,
  `amount` DOUBLE NOT NULL,
  `interest` DOUBLE NOT NULL,
  `currency` VARCHAR(50) NOT NULL,
  `isIncome` TINYINT NOT NULL,
  `expirationDate` DATETIME NOT NULL,
  `User_id` INT NOT NULL,
  `User_id1` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Dept_User1_idx` (`User_id` ASC),
  INDEX `fk_Dept_User2_idx` (`User_id1` ASC),
  CONSTRAINT `fk_Dept_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `moneythoring`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Dept_User2`
    FOREIGN KEY (`User_id1`)
    REFERENCES `moneythoring`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moneythoring`.`SharedBudget`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `moneythoring`.`SharedBudget` (
  `User_id` INT NOT NULL,
  `Budget_id` INT NOT NULL,
  PRIMARY KEY (`User_id`, `Budget_id`),
  INDEX `fk_User_has_Budget_Budget1_idx` (`Budget_id` ASC),
  INDEX `fk_User_has_Budget_User1_idx` (`User_id` ASC),
  CONSTRAINT `fk_User_has_Budget_User1`
    FOREIGN KEY (`User_id`)
    REFERENCES `moneythoring`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Budget_Budget1`
    FOREIGN KEY (`Budget_id`)
    REFERENCES `moneythoring`.`Budget` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


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
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Category_has_Budget_Budget1`
    FOREIGN KEY (`Budget_id`)
    REFERENCES `moneythoring`.`Budget` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
