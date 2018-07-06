CREATE TABLE IF NOT EXISTS `viagem`.`viagem_comarca` (
  `gestor` INT(11) NULL,
  `viagem` INT(11) NOT NULL,
  `comarca` INT(11) NOT NULL,
  PRIMARY KEY (`viagem`, `comarca`),
  INDEX `fk_viagem_comarca_viagem_idx` (`viagem` ASC),
  INDEX `fk_viagem_comarca_comarca1_idx` (`comarca` ASC),
  CONSTRAINT `fk_viagem_comarca_viagem`
    FOREIGN KEY (`viagem`)
    REFERENCES `viagem`.`viagem` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_viagem_comarca_comarca1`
    FOREIGN KEY (`comarca`)
    REFERENCES `viagem`.`comarca` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;