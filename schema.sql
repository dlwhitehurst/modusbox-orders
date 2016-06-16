-- **************************************************************************
-- ** Schema for modusbox-orders
-- **
-- ** written by David L. Whitehurst
-- ** on June 15, 2016
-- **
-- **
-- **************************************************************************

-- Create database

CREATE DATABASE modusbox;
use modusbox;

-- Create orders table
CREATE TABLE `modusbox`.`orders` (
  `orderId` INT NOT NULL AUTO_INCREMENT,
    `customerName` VARCHAR(45) NOT NULL,
      `placementDate` DATETIME NULL
      
-- create orderitems table
CREATE TABLE `modusbox`.`orderitems` (
  `orderItemId` INT NOT NULL AUTO_INCREMENT,
    `orderId` INT NOT NULL,
      `itemId` INT NOT NULL,
        `itemCount` INT NULL,
	  PRIMARY KEY (`orderItemId`));

-- Create items table
CREATE TABLE `modusbox`.`items` (
  `itemId` INT NOT NULL AUTO_INCREMENT,
    `itemName` VARCHAR(45) NOT NULL,
      `itemCost` VARCHAR(45) NOT NULL,
        PRIMARY KEY (`itemId`));i


