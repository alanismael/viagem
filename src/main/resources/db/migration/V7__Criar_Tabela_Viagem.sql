CREATE TABLE IF NOT EXISTS `viagem`.`viagem` (
  `codigo` INT(11) NOT NULL AUTO_INCREMENT,
  `datainicio` DATE NULL,
  `datafinal` DATE NULL,
  `descricao` VARCHAR(200) NULL,
  `situacao` VARCHAR(20) NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;