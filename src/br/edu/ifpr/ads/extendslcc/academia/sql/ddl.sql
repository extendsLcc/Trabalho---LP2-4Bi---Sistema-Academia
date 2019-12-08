/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  LCC
 * Created: 01/12/2019
 */

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema academia
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema academia
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `academia` DEFAULT CHARACTER SET utf8 ;
USE `academia` ;

-- -----------------------------------------------------
-- Table `academia`.`Titulacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academia`.`Titulacao` (
  `idTitulacao` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idTitulacao`),
  UNIQUE INDEX `idTitulacao_UNIQUE` (`idTitulacao` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academia`.`Instrutor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academia`.`Instrutor` (
  `idInstrutor` INT NOT NULL AUTO_INCREMENT,
  `rg` VARCHAR(50) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `nascimento` DATE NULL,
  `Titulacao_idTitulacao` INT NOT NULL,
  PRIMARY KEY (`idInstrutor`),
  UNIQUE INDEX `idInstrutor_UNIQUE` (`idInstrutor` ASC),
  INDEX `fk_Instrutor_Titulacao1_idx` (`Titulacao_idTitulacao` ASC),
  CONSTRAINT `fk_Instrutor_Titulacao1`
    FOREIGN KEY (`Titulacao_idTitulacao`)
    REFERENCES `academia`.`Titulacao` (`idTitulacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academia`.`Telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academia`.`Telefone` (
  `idTelefone` INT NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(20) NOT NULL,
  `tipo` varchar( 30 ) NOT NULL,
  `Instrutor_idInstrutor` INT NOT NULL,
  PRIMARY KEY (`idTelefone`),
  UNIQUE INDEX `idTelefone_UNIQUE` (`idTelefone` ASC),
  INDEX `fk_Telefone_Instrutor_idx` (`Instrutor_idInstrutor` ASC),
  CONSTRAINT `fk_Telefone_Instrutor`
    FOREIGN KEY (`Instrutor_idInstrutor`)
    REFERENCES `academia`.`Instrutor` (`idInstrutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academia`.`Atividade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academia`.`Atividade` (
  `idAtividade` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idAtividade`),
  UNIQUE INDEX `idatividade_UNIQUE` (`idAtividade` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academia`.`Turma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academia`.`Turma` (
  `idTurma` INT NOT NULL AUTO_INCREMENT,
  `horario` TIME NOT NULL,
  `duracao` TIME NOT NULL,
  `dataInicio` DATE NOT NULL,
  `dataFim` DATE NOT NULL,
  `Atividade_idAtividade` INT NOT NULL,
  `Instrutor_idInstrutor` INT NOT NULL,
  PRIMARY KEY (`idTurma`),
  UNIQUE INDEX `idTurma_UNIQUE` (`idTurma` ASC),
  INDEX `fk_Turma_Atividade1_idx` (`Atividade_idAtividade` ASC),
  INDEX `fk_Turma_Instrutor1_idx` (`Instrutor_idInstrutor` ASC),
  CONSTRAINT `fk_Turma_Atividade1`
    FOREIGN KEY (`Atividade_idAtividade`)
    REFERENCES `academia`.`Atividade` (`idAtividade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Turma_Instrutor1`
    FOREIGN KEY (`Instrutor_idInstrutor`)
    REFERENCES `academia`.`Instrutor` (`idInstrutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academia`.`Aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academia`.`Aluno` (
  `idAluno` INT NOT NULL AUTO_INCREMENT,
  `dataMatricula` DATE NOT NULL,
  `nome` VARCHAR(120) NULL,
  `endereco` VARCHAR(200) NULL,
  `telefone` VARCHAR(20) NULL,
  `nascimento` DATE NULL,
  `altura` FLOAT NULL,
  `peso` FLOAT NULL,
  `Turma_idTurma` INT NULL,
  PRIMARY KEY (`idAluno`),
  UNIQUE INDEX `idAluno_UNIQUE` (`idAluno` ASC),
  INDEX `fk_Aluno_Turma1_idx` (`Turma_idTurma` ASC),
  CONSTRAINT `fk_Aluno_Turma1`
    FOREIGN KEY (`Turma_idTurma`)
    REFERENCES `academia`.`Turma` (`idTurma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academia`.`Matricula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academia`.`Matricula` (
  `Turma_idTurma` INT NOT NULL,
  `Aluno_idAluno` INT NOT NULL,
  PRIMARY KEY (`Turma_idTurma`, `Aluno_idAluno`),
  INDEX `fk_Turma_has_Aluno_Aluno1_idx` (`Aluno_idAluno` ASC),
  INDEX `fk_Turma_has_Aluno_Turma1_idx` (`Turma_idTurma` ASC),
  CONSTRAINT `fk_Turma_has_Aluno_Turma1`
    FOREIGN KEY (`Turma_idTurma`)
    REFERENCES `academia`.`Turma` (`idTurma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Turma_has_Aluno_Aluno1`
    FOREIGN KEY (`Aluno_idAluno`)
    REFERENCES `academia`.`Aluno` (`idAluno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `academia`.`Chamada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `academia`.`Chamada` (
  `idChamada` INT NOT NULL AUTO_INCREMENT,
  `data` DATE NULL,
  `presente` TINYINT NULL,
  `Matricula_Turma_idTurma` INT NOT NULL,
  `Matricula_Aluno_idAluno` INT NOT NULL,
  PRIMARY KEY (`idChamada`),
  UNIQUE INDEX `idChamada_UNIQUE` (`idChamada` ASC),
  INDEX `fk_Chamada_Matricula1_idx` (`Matricula_Turma_idTurma` ASC, `Matricula_Aluno_idAluno` ASC),
  CONSTRAINT `fk_Chamada_Matricula1`
    FOREIGN KEY (`Matricula_Turma_idTurma` , `Matricula_Aluno_idAluno`)
    REFERENCES `academia`.`Matricula` (`Turma_idTurma` , `Aluno_idAluno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
