DROP TABLE IF EXISTS CategoriesBudget;
DROP TABLE IF EXISTS SharedBudget;
DROP TABLE IF EXISTS Debt;
DROP TABLE IF EXISTS Recurrence;
DROP TABLE IF EXISTS "Transaction";
DROP TABLE IF EXISTS BankAccount;
DROP TABLE IF EXISTS Budget;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS "User";

-- -----------------------------------------------------
-- Table `moneythoring`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "User"(
  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  username VARCHAR(50) UNIQUE NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(50) NOT NULL,
  isActivated BOOLEAN NOT NULL,
  activationKey VARCHAR(50) NULL,
  salt VARCHAR(50) NOT NULL
  );


-- -----------------------------------------------------
-- Table `moneythoring`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "Category" (
  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,
  name VARCHAR(50) NOT NULL,
  colour VARCHAR(50) NOT NULL,
  isDefault BOOLEAN NOT NULL DEFAULT false,
  User_id INTEGER NOT NULL REFERENCES "User"(id)
 );

-- -----------------------------------------------------
-- Table `moneythoring`.`Budget`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Budget (
  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(50) NOT NULL,
  amount REAL NOT NULL,
  isShared BOOLEAN NOT NULL DEFAULT false,
  isRecurrent BOOLEAN NOT NULL,
  startingDate DATE NOT NULL,
  endingDate DATE NOT NULL,
  gap INTEGER NULL,
  User_id INTEGER NOT NULL REFERENCES "User" (id));


-- -----------------------------------------------------
-- Table `moneythoring`.`BankAccount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS BankAccount(
  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(50) NOT NULL,
  nameBank VARCHAR(50) NULL,
  typeAccount VARCHAR(100) NOT NULL,
  amount REAL NOT NULL,
  isDefault BOOLEAN NOT NULL,
  User_id INTEGER NOT NULL REFERENCES "User" (id));


-- -----------------------------------------------------
-- Table `moneythoring`.`Transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS "Transaction"(
  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(255) NULL,
  dateTransaction DATE NOT NULL,
  amount REAL NOT NULL,
  currency VARCHAR(50) NOT NULL,
  isIncome BOOLEAN NOT NULL,
  category_id INTEGER NOT NULL REFERENCES "Category"(id),
  BankAccount_id INTEGER NOT NULL REFERENCES BankAccount(id),
  Budget_id INTEGER NULL REFERENCES Budget(id)
  );

-- -----------------------------------------------------
-- Table `moneythoring`.`Recurrence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Recurrence (
  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  gap INTEGER NOT NULL,
  nextDate DATE NOT NULL,
  Transaction_id INTEGER NOT NULL REFERENCES "Transaction"(id));


-- -----------------------------------------------------
-- Table `moneythoring`.`Debt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Debt (
  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  name VARCHAR(50) NOT NULL,
  description VARCHAR(255) NULL,
  amount REAL NOT NULL,
  isIncome BOOLEAN NOT NULL,
  expirationDate DATE NOT NULL,
  User_id INTEGER NOT NULL REFERENCES "User" (id), --voir les nom des rellations
  User_id1 INTEGER NULL REFERENCES "User" (id)); --voir les nom des );


-- -----------------------------------------------------
-- Table `moneythoring`.`SharedBudget`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS SharedBudget (
  User_id INTEGER NOT NULL REFERENCES "User"(id),
  Budget_id INTEGER NOT NULL REFERENCES Budget(id),
  PRIMARY KEY (User_id, Budget_id));


-- -----------------------------------------------------
-- Table `moneythoring`.`CategoriesBudget`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CategoriesBudget (
  Category_id INTEGER NOT NULL REFERENCES "Category"(id),
  Budget_id INTEGER NOT NULL REFERENCES Budget (id),
  PRIMARY KEY (Category_id, Budget_id))