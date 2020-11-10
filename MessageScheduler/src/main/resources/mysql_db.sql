CREATE TABLE `MessageSchedulerDB`.`messagedetails` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `message` VARCHAR(45) NOT NULL COMMENT '',
  `date` VARCHAR(45) NOT NULL COMMENT '',
  `time` VARCHAR(45) NOT NULL COMMENT '',
  `timezone` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '');
