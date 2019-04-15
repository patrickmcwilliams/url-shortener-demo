SET @@global.time_zone = '+00:00';
SET @@session.time_zone = '+00:00';

CREATE DATABASE IF NOT EXISTS `urlshortener`;
USE `urlshortener`;

CREATE TABLE IF NOT EXISTS `url` (
  `url_hash` varchar(10) NOT NULL,
  `url_full` varchar(2024) NOT NULL,
  PRIMARY KEY (`url_hash`)
) ENGINE=InnoDB;