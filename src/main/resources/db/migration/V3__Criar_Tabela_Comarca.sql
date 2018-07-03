CREATE TABLE IF NOT EXISTS `viagem`.`comarca` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL,
  `endereco` VARCHAR(100) NULL,
  `email` VARCHAR(60) NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;