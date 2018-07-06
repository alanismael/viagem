CREATE TABLE IF NOT EXISTS `viagem`.`telefone` (
  `numero` VARCHAR(15) NOT NULL,
  `comarca` INT(11) NOT NULL,
  PRIMARY KEY (`numero`, `comarca`),
  INDEX `fk_telefone_comarca1_idx` (`comarca` ASC),
  CONSTRAINT `fk_telefone_comarca1`
    FOREIGN KEY (`comarca`)
    REFERENCES `viagem`.`comarca` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;