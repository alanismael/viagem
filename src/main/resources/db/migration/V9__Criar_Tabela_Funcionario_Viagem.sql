CREATE TABLE IF NOT EXISTS `viagem`.`funcionario_viagem` (
  `funcionario` INT(11) NOT NULL,
  `viagem` INT(11) NOT NULL,
  PRIMARY KEY (`funcionario`, `viagem`),
  INDEX `fk_funcionario_viagem_funcionario1_idx` (`funcionario` ASC),
  INDEX `fk_funcionario_viagem_viagem1_idx` (`viagem` ASC),
  CONSTRAINT `fk_funcionario_viagem_funcionario1`
    FOREIGN KEY (`funcionario`)
    REFERENCES `viagem`.`funcionario` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_funcionario_viagem_viagem1`
    FOREIGN KEY (`viagem`)
    REFERENCES `viagem`.`viagem` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;