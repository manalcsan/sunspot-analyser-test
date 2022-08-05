SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sunspotanalyser
-- -----------------------------------------------------
CREATE USER 'admin'@'%' IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'%';

-- -----------------------------------------------------
-- Schema sunspotanalyser
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sunspotanalyser
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sunspotanalyser` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `sunspotanalyser` ;

-- -----------------------------------------------------
-- Table `grid`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `grid` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `size` SMALLINT(6) NOT NULL DEFAULT 3,
  `gridvalues` VARCHAR(999) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `scores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `scores` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idgrid` INT NOT NULL,
  `scores` VARCHAR(999) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_scores_grid`
    FOREIGN KEY (`idgrid`)
    REFERENCES `grid` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_scores_grid` ON `scores` (`idgrid` ASC);