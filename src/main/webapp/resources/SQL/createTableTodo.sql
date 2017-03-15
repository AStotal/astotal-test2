DROP TABLE `Todo`;
CREATE TABLE `test`.`Todo` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(25) NOT NULL,
  `isDone` BIT DEFAULT 0,
  `createdDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`));