-- MySQL Script generated by MySQL Workbench
-- Tue Jun 27 01:28:51 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Banana App
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Banana App
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Banana App` ;
USE `Banana App` ;

-- -----------------------------------------------------
-- Table `Banana App`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Banana App`.`usuario` (
  `idu` INT(11) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(15) NOT NULL,
  `foto` VARCHAR(45) NOT NULL,
  `video` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idu`),
  UNIQUE INDEX `idu_UNIQUE` (`idu` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Banana App`.`proyecto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Banana App`.`proyecto` (
  `idp` INT(11) NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(45) NOT NULL,
  `responsable` INT(11) NOT NULL,
  `fecha de inicio` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha final` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `descripcion` VARCHAR(250) NOT NULL,
  `nota` VARCHAR(250) NOT NULL,
  `status` INT(11) NOT NULL,
  `progreso` INT(11) NOT NULL,
  PRIMARY KEY (`idp`),
  UNIQUE INDEX `idp_UNIQUE` (`idp` ASC),
  INDEX `progreso_idx` (`progreso` ASC),
  CONSTRAINT `responsabe`
    FOREIGN KEY (`idp`)
    REFERENCES `Banana App`.`usuario` (`idu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `progreso`
    FOREIGN KEY (`progreso`)
    REFERENCES `Banana App`.`proyecto` (`idp`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Banana App`.`tarea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Banana App`.`tarea` (
  `idt` INT(11) NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `fecha de inicio` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha final` DATETIME NOT NULL DEFAULT 'CURRENT_TIMESTAMP',
  `status` INT(11) NOT NULL,
  `progreso` INT(11) NOT NULL,
  `responsable` INT(11) NOT NULL,
  `proyecto` INT(11) NOT NULL,
  PRIMARY KEY (`idt`),
  UNIQUE INDEX `idt_UNIQUE` (`idt` ASC),
  INDEX `progreso_idx` (`progreso` ASC),
  CONSTRAINT `proyecto`
    FOREIGN KEY (`idt`)
    REFERENCES `Banana App`.`proyecto` (`idp`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `responsabe`
    FOREIGN KEY (`idt`)
    REFERENCES `Banana App`.`usuario` (`idu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `progreso`
    FOREIGN KEY (`progreso`)
    REFERENCES `Banana App`.`proyecto` (`idp`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Banana App`.`progreso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Banana App`.`progreso` (
  `idpro` INT(11) NOT NULL,
  `estado` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`idpro`),
  UNIQUE INDEX `idpro_UNIQUE` (`idpro` ASC),
  CONSTRAINT `idpro`
    FOREIGN KEY (`idpro`)
    REFERENCES `Banana App`.`proyecto` (`idp`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Banana App`.`table1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Banana App`.`table1` (
)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;