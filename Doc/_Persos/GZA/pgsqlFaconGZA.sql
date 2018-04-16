-- -----------------------------------------------------
-- Schema moneythoring
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS moneythoring CASCADE;
CREATE SCHEMA moneythoring;

-- -----------------------------------------------------
-- Table moneythoring.Client
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.client (
  id SERIAL NOT NULL,
  username VARCHAR(50) UNIQUE NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(50) NOT NULL,
  isActivated BOOL NOT NULL,
  activationKey VARCHAR(50) NULL,
  salt VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.Category
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.category (
  id SERIAL NOT NULL,
  name VARCHAR(50) NOT NULL,
  colour VARCHAR(50) NOT NULL,
  isDefault BOOL NOT NULL DEFAULT FALSE,
  client_id INT NOT NULL REFERENCES moneythoring.client (id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.Budget
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.budget (
  id SERIAL NOT NULL,
  name VARCHAR(50) NOT NULL,
  amount FLOAT8 NOT NULL,
  isShared BOOL NOT NULL DEFAULT FALSE,
  isRecurrent BOOL NOT NULL,
  startingDate DATE NOT NULL,
  endingDate DATE NOT NULL,
  gap INT NULL,
  client_id INT NOT NULL REFERENCES moneythoring.client (id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.BankAccount
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.bankAccount (
  id SERIAL NOT NULL,
  name VARCHAR(50) NOT NULL,
  nameBank VARCHAR(50) NULL,
  typeAccount VARCHAR(100) NOT NULL,
  amount FLOAT8 NOT NULL,
  isDefault BOOL NOT NULL,
  isVisible BOOL NOT NULL,
  client_id INT NOT NULL REFERENCES moneythoring.client (id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.IOTransaction
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.iOTransaction (
  id SERIAL NOT NULL,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(255) NULL,
  dateTransaction DATE NOT NULL,
  amount FLOAT8 NOT NULL,
  currency VARCHAR(50) NOT NULL,
  isIncome BOOL NOT NULL,
  category_id INT NOT NULL REFERENCES moneythoring.category (id) ON DELETE NO ACTION ON UPDATE CASCADE,
  bankAccount_id INT NOT NULL REFERENCES moneythoring.bankAccount (id) ON DELETE CASCADE ON UPDATE CASCADE,
  budget_id INT NULL REFERENCES moneythoring.budget (id) ON DELETE SET NULL ON UPDATE CASCADE,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.Recurrence
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.recurrence (
  id SERIAL NOT NULL,
  gap INT NOT NULL,
  nextDate DATE NOT NULL,
  iOTransaction_id INT NOT NULL REFERENCES moneythoring.iOTransaction (id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.Debt
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.debt (
  id SERIAL NOT NULL,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(255) NULL,
  amount FLOAT8 NOT NULL,
  isIncome BOOL NOT NULL,
  expirationDate DATE NOT NULL,
  client_id INT NOT NULL REFERENCES moneythoring.client (id) ON DELETE CASCADE ON UPDATE CASCADE,
  client_id1 INT NULL REFERENCES moneythoring.client (id) ON DELETE NO ACTION ON UPDATE CASCADE,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.SharedBudget
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.sharedBudget (
  client_id INT NOT NULL REFERENCES moneythoring.client (id),
  budget_id INT NOT NULL REFERENCES moneythoring.budget (id),
  PRIMARY KEY (client_id, budget_id)
);

-- -----------------------------------------------------
-- Table moneythoring.CategoriesBudget
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.categoriesBudget (
  category_id INT NOT NULL REFERENCES moneythoring.category (id) ON DELETE CASCADE ON UPDATE CASCADE,
  budget_id INT NOT NULL REFERENCES moneythoring.budget (id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (Category_id, Budget_id)
 );