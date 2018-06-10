CREATE TABLE IF NOT EXISTS `viagem`.`funcionario` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(100) NULL,
  `endereco` VARCHAR(100) NULL,
  `telefone` VARCHAR(14) NULL,
  `celular` VARCHAR(14) NULL,
  `email` VARCHAR(60) NULL,
  `matricula` VARCHAR(10) NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;