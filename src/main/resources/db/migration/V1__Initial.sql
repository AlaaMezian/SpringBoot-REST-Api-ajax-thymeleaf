CREATE TABLE `admin_user` (
  `admin_user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NULL,
  `enc_password` VARCHAR(45) NULL,
  `user_email` VARCHAR(45) NULL,
  `is_active` ENUM('Y', 'N') NULL,
  PRIMARY KEY (`admin_user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'The table to hold the Admin Users and their login details';

CREATE TABLE `user` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `user_email` VARCHAR(45) NULL,
  `enc_password` VARCHAR(45) NULL,
  `reg_date` DATETIME NULL,
  `mobile_number` VARCHAR(15) NULL,
  `archive_flag` ENUM('Y', 'N') NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COMMENT = 'the table hold the application user information';

CREATE TABLE `categories` (
  `category_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(45) NOT NULL,
  `added_date` DATE NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE INDEX `category_name_UNIQUE` (`category_name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'this table describe the categories in the application';


CREATE TABLE `items` (
  `item_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `item_name` VARCHAR(45) NULL,
  `category_id` INT NULL,
  `item_title` VARCHAR(50) NULL,
  `item_description` VARCHAR(255) NULL,
  `price` VARCHAR(45) NULL,
  PRIMARY KEY (`item_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'this table describe each item and its category';


ALTER TABLE `items` 
ADD COLUMN `added_date` DATE NULL AFTER `price`;

ALTER TABLE `items` 
CHANGE COLUMN `category_id` `category_id` INT UNSIGNED NULL AFTER `added_date`,
ADD INDEX `fk_categories_idx` (`category_id` ASC);
ALTER TABLE `items` 
ADD CONSTRAINT `fk_categories`
  FOREIGN KEY (`category_id`)
  REFERENCES `categories` (`category_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `items` 
DROP COLUMN `item_name`;

CREATE TABLE `booking` (
  `booking_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `booking_date` DATETIME NULL,
  `user_id` INT(10) UNSIGNED NULL,
  `item_id` INT(10) UNSIGNED NULL,
  PRIMARY KEY (`booking_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'this table describe the booking information such as data and time for each booking ';

ALTER TABLE `booking` 
ADD INDEX `fk_user_idx` (`user_id` ASC),
ADD INDEX `fk_item_idx` (`item_id` ASC);
ALTER TABLE `booking` 
ADD CONSTRAINT `fk_user`
  FOREIGN KEY (`user_id`)
  REFERENCES `user` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_item`
  FOREIGN KEY (`item_id`)
  REFERENCES `items` (`item_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

CREATE TABLE `images_table` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(100) NULL,
  `type` VARCHAR(10) NULL,
  `pic` BLOB NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'this table is specified for images';

ALTER TABLE `user` 
CHANGE COLUMN `archive_flag` `is_active` ENUM('Y', 'N') NULL DEFAULT 'Y' ;

ALTER TABLE `categories` 
RENAME TO  `category` ;


ALTER TABLE `images_table` 
RENAME TO  `images` ;


ALTER TABLE `user` 
CHANGE COLUMN `mobile_number` `mobile_number` VARCHAR(15) NOT NULL ;

ALTER TABLE `user` 
CHANGE COLUMN `enc_password` `enc_password` VARCHAR(255) NOT NULL ;

ALTER TABLE `user` 
CHANGE COLUMN `user_email` `user_email` VARCHAR(45) NOT NULL ;


ALTER TABLE `user` 
DROP COLUMN `username`,
DROP INDEX `username_UNIQUE` ;

INSERT INTO `category` (`category_id`, `category_name`, `added_date`) VALUES ('1', 'Pigments', '2018-05-08 13:53:06');
UPDATE `category` SET `added_date`='2018-05-08 13:53:06' WHERE `category_id`='1';

ALTER TABLE `user` 
ADD COLUMN `user_name` VARCHAR(255) NULL AFTER `is_active`;

ALTER TABLE `user` 
CHANGE COLUMN `enc_password` `password` VARCHAR(255) NOT NULL ;


