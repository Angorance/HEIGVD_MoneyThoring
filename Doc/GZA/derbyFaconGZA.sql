-- -----------------------------------------------------
-- Schema moneythoring
-- -----------------------------------------------------
DROP TABLE moneythoring.categoriesBudget;
DROP TABLE moneythoring.sharedBudget;
DROP TABLE moneythoring.debt;
DROP TABLE moneythoring.recurrence;
DROP TABLE moneythoring.iOTransaction;
DROP TABLE moneythoring.bankAccount;
DROP TABLE moneythoring.budget;
DROP TABLE moneythoring.category;
DROP TABLE moneythoring.client;
DROP SCHEMA moneythoring RESTRICT ;
CREATE SCHEMA moneythoring;

-- -----------------------------------------------------
-- Table moneythoring.Client
-- -----------------------------------------------------
CREATE TABLE moneythoring.client (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
  username VARCHAR(50) UNIQUE NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(50) NOT NULL,
  isActivated BOOLEAN NOT NULL,
  activationKey VARCHAR(50),
  salt VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.Category
-- -----------------------------------------------------
CREATE TABLE moneythoring.category (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
  name VARCHAR(50) NOT NULL,
  colour VARCHAR(50) NOT NULL,
  isDefault BOOLEAN NOT NULL DEFAULT FALSE,
  client_id INT NOT NULL REFERENCES moneythoring.client (id) ON DELETE CASCADE,
PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.Budget
-- -----------------------------------------------------
CREATE TABLE moneythoring.budget (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
  name VARCHAR(50) NOT NULL,
  amount FLOAT NOT NULL,
  isShared BOOLEAN NOT NULL DEFAULT FALSE,
  isRecurrent BOOLEAN NOT NULL,
  startingDate DATE NOT NULL,
  endingDate DATE NOT NULL,
  gap INT,
  client_id INTEGER NOT NULL REFERENCES moneythoring.client (id) ON DELETE CASCADE,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.BankAccount
-- -----------------------------------------------------
CREATE TABLE moneythoring.bankAccount (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
  name VARCHAR(50) NOT NULL,
  nameBank VARCHAR(50),
  typeAccount VARCHAR(100) NOT NULL,
  amount FLOAT NOT NULL,
  isDefault BOOLEAN NOT NULL,
  isVisible BOOLEAN NOT NULL,
  client_id INT NOT NULL REFERENCES moneythoring.client (id) ON DELETE CASCADE,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.IOTransaction
-- -----------------------------------------------------
CREATE TABLE moneythoring.iOTransaction (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
  name VARCHAR(50) NOT NULL,
  description VARCHAR(255),
  dateTransaction DATE NOT NULL,
  amount FLOAT NOT NULL,
  currency VARCHAR(50) NOT NULL,
  isIncome BOOLEAN NOT NULL,
  category_id INTEGER NOT NULL REFERENCES moneythoring.category (id),
  bankAccount_id INTEGER NOT NULL REFERENCES moneythoring.bankAccount (id),
  budget_id INTEGER REFERENCES moneythoring.budget (id),
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.Recurrence
-- -----------------------------------------------------
CREATE TABLE moneythoring.recurrence (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
  gap INT NOT NULL,
  nextDate DATE NOT NULL,
  iOTransaction_id INT NOT NULL REFERENCES moneythoring.iOTransaction (id) ON DELETE CASCADE,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.Debt
-- -----------------------------------------------------
CREATE TABLE moneythoring.debt (
  id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),
  name VARCHAR(50) NOT NULL,
  description VARCHAR(255),
  amount FLOAT NOT NULL,
  isIncome BOOLEAN NOT NULL,
  expirationDate DATE NOT NULL,
  client_id INTEGER NOT NULL REFERENCES moneythoring.client (id),
  client_id1 INTEGER REFERENCES moneythoring.client (id),
PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.SharedBudget
-- -----------------------------------------------------
CREATE TABLE moneythoring.sharedBudget (
  client_id INT NOT NULL REFERENCES moneythoring.client (id),
  budget_id INT NOT NULL REFERENCES moneythoring.budget (id),
PRIMARY KEY (client_id, budget_id)
);

-- -----------------------------------------------------
-- Table moneythoring.CategoriesBudget
-- -----------------------------------------------------
CREATE TABLE moneythoring.categoriesBudget (
  category_id INTEGER NOT NULL REFERENCES moneythoring.category (id),
  budget_id INTEGER NOT NULL REFERENCES moneythoring.budget (id),
PRIMARY KEY (Category_id, Budget_id)
);


