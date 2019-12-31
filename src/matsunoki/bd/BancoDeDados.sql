/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Tais
 * Created: 22/11/2019
 */

CREATE DATABASE  IF NOT EXISTS `matsunoki`;

USE `matsunoki`;

/* Cria a tabela 'produto' somente se não existir */
CREATE TABLE IF NOT EXISTS `matsunoki`.`produto` (
  `idproduto` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(255) NOT NULL,
  `preco` DECIMAL(6,2) NOT NULL,
  `caminho_imagem` VARCHAR(512) NULL,
  PRIMARY KEY (`idproduto`),
  UNIQUE INDEX `idproduto_UNIQUE` (`idproduto` ASC) VISIBLE);

/* Cria a tabela 'cliente' somente se não existir */
CREATE TABLE IF NOT EXISTS `matsunoki`.`cliente` (
  `idcliente` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `dataNascimento` VARCHAR(10) NULL,
  `cpf` VARCHAR(14) NULL,
  `email` VARCHAR(255) NULL,
  PRIMARY KEY (`idcliente`),
  UNIQUE INDEX `idcliente_UNIQUE` (`idcliente` ASC) VISIBLE);

/* Cria a tabela 'endereco' somente se não existir */
CREATE TABLE IF NOT EXISTS `matsunoki`.`endereco` (
  `idendereco` INT NOT NULL AUTO_INCREMENT,
  `idcliente` INT NOT NULL,
  `logradouro` VARCHAR(255) NULL,
  `numero` INT NULL,
  `complemento` VARCHAR(100) NULL,
  `bairro` VARCHAR(255) NULL,
  `cidade` VARCHAR(255) NULL,
  `estado` VARCHAR(2) NULL,
  `cep` VARCHAR(9) NULL,
  PRIMARY KEY (`idendereco`),
  UNIQUE INDEX `idendereco_UNIQUE` (`idendereco` ASC) VISIBLE,
  INDEX `fk_cliente_endereco_idx` (`idcliente` ASC) VISIBLE,
  CONSTRAINT `fk_cliente_endereco`
    FOREIGN KEY (`idcliente`)
    REFERENCES `matsunoki`.`cliente` (`idcliente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

/* Cria a tabela 'usuario' somente se não existir */
CREATE TABLE IF NOT EXISTS `matsunoki`.`usuario` (
  `idusuario` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NOT NULL,
  `cpf` VARCHAR(14) NULL,
  `funcao` VARCHAR(100) NULL,
  `login` VARCHAR(50) NULL,
  `senha` VARCHAR(50) NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE INDEX `idusuario_UNIQUE` (`idusuario` ASC) VISIBLE);

INSERT INTO `matsunoki`.`usuario`
(`nome`,`cpf`,`funcao`,`login`,`senha`)
VALUES
('Matsunoki', '123.123.456-90','Proprietário', 'matsunoki', 'matsunoki');