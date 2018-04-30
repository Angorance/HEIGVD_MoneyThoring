-- -----------------------------------------------------
-- Schema moneythoring
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS moneythoring CASCADE;
CREATE SCHEMA moneythoring;

-- -----------------------------------------------------
-- Table moneythoring.Client
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.Client (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  username VARCHAR(50) UNIQUE NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(250) NOT NULL,
  isActivated BOOLEAN NOT NULL,
  activationKey VARCHAR(50) NULL,
  salt VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.Category
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.Category (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name VARCHAR(50) NOT NULL,
  colour VARCHAR(50) NOT NULL,
  isDefault BOOLEAN DEFAULT FALSE,
  Client_id INT NOT NULL REFERENCES moneythoring.Client (id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.Budget
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.Budget (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name VARCHAR(50) NOT NULL,
  amount DOUBLE NOT NULL,
  isShared BOOLEAN DEFAULT FALSE,
  isRecurrent BOOLEAN NOT NULL,
  startingDate DATE NOT NULL,
  endingDate DATE NOT NULL,
  gap INT NULL,
  Client_id INT NOT NULL REFERENCES moneythoring.Client (id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.BankAccount
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.BankAccount (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name VARCHAR(50) NOT NULL,
  nameBank VARCHAR(50) NULL,
  typeAccount VARCHAR(100) NOT NULL,
  amount DOUBLE NOT NULL,
  isDefault BOOLEAN NOT NULL,
  isVisible BOOLEAN NOT NULL,
  Client_id INT NOT NULL REFERENCES moneythoring.Client (id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.IOTransaction
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.IOTransaction (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name VARCHAR(50) NOT NULL,
  description VARCHAR(255) NULL,
  date DATE NOT NULL,
  amount DOUBLE NOT NULL,
  currency VARCHAR(50) NOT NULL,
  isIncome BOOLEAN NOT NULL,
  Category_id INT NOT NULL REFERENCES moneythoring.Category (id) ON DELETE NO ACTION ON UPDATE CASCADE,
  BankAccount_id INT NOT NULL REFERENCES moneythoring.BankAccount (id) ON DELETE CASCADE ON UPDATE CASCADE,
  Budget_id INT NULL REFERENCES moneythoring.Budget (id) ON DELETE SET NULL ON UPDATE CASCADE,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.Recurrence
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.Recurrence (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  gap INT NOT NULL,
  nextDate DATE NOT NULL,
  IOTransaction_id INT NOT NULL REFERENCES moneythoring.IOTransaction (id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.Debt
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.Debt (
  id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name VARCHAR(50) NOT NULL,
  description VARCHAR(255) NULL,
  amount DOUBLE NOT NULL,
  isIncome BOOLEAN NOT NULL,
  expirationDate DATE NOT NULL,
  Client_id INT NOT NULL REFERENCES moneythoring.Client (id) ON DELETE CASCADE ON UPDATE CASCADE,
  Client_id1 INT NULL REFERENCES moneythoring.Client (id) ON DELETE NO ACTION ON UPDATE CASCADE,
  PRIMARY KEY (id)
);

-- -----------------------------------------------------
-- Table moneythoring.SharedBudget
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.SharedBudget (
  Client_id INT NOT NULL REFERENCES moneythoring.Client (id) ON DELETE NO ACTION ON UPDATE CASCADE,
  Budget_id INT NOT NULL REFERENCES moneythoring.Budget (id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (Client_id, Budget_id)
);

-- -----------------------------------------------------
-- Table moneythoring.CategoriesBudget
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS moneythoring.CategoriesBudget (
  Category_id INT NOT NULL REFERENCES moneythoring.Category (id) ON DELETE CASCADE ON UPDATE CASCADE,
  Budget_id INT NOT NULL REFERENCES moneythoring.Budget (id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (Category_id, Budget_id)
 );