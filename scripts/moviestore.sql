-- MySQL dump 10.13  Distrib 5.5.25, for Win64 (x86)
--
-- Host: localhost    Database: moviestore
-- ------------------------------------------------------
-- Server version       5.5.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `bill_end_date` datetime DEFAULT NULL,
  `bill_id` int(11) DEFAULT NULL,
  `bill_start_date` datetime NOT NULL,
  `comments` varchar(255) NOT NULL,
  `due_date` datetime NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `version` int(11) DEFAULT NULL,
  `movie_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kmvjth05ntggk9ji14lsqassw` (`movie_user`),
  CONSTRAINT `FK_kmvjth05ntggk9ji14lsqassw` FOREIGN KEY (`movie_user`) REFERENCES `movie_user` (`id
`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_type`
--

DROP TABLE IF EXISTS `member_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_fee` double DEFAULT NULL,
  `total_movies` int(11) DEFAULT NULL,
  `type_name` varchar(30) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_type`
--

LOCK TABLES `member_type` WRITE;
/*!40000 ALTER TABLE `member_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `member_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membership_info`
--

DROP TABLE IF EXISTS `membership_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membership_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `membership_number` varchar(255) NOT NULL,
  `outstanding_movies` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `member_type` bigint(20) DEFAULT NULL,
  `movie_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_r91yavp7fxo595jamfox9svak` (`member_type`),
  KEY `FK_vha6p3bam8j0x3t1nbqwm2xu` (`movie_user`),
  CONSTRAINT `FK_vha6p3bam8j0x3t1nbqwm2xu` FOREIGN KEY (`movie_user`) REFERENCES `movie_user` (`id`
),
  CONSTRAINT `FK_r91yavp7fxo595jamfox9svak` FOREIGN KEY (`member_type`) REFERENCES `member_type` (`
id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membership_info`
--

LOCK TABLES `membership_info` WRITE;
/*!40000 ALTER TABLE `membership_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `membership_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `banner` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `movie_name` varchar(255) NOT NULL,
  `number_of_copies` int(11) DEFAULT NULL,
  `release_date` datetime NOT NULL,
  `rent_fee` double DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'Disney','Action','2013-11-14 00:00:00','Hunger Game',4,'2013-11-22 0
0:00:00',3,'Hunger game',0);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_rental`
--

DROP TABLE IF EXISTS `movie_rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_rental` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comments` varchar(255) NOT NULL,
  `due_date` datetime NOT NULL,
  `rented_date` datetime NOT NULL,
  `returned_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `movie` bigint(20) DEFAULT NULL,
  `movie_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6uqpdjxf5apmic72r2s9fn98p` (`movie`),
  KEY `FK_6ngdl7jtwnsyxrr7sg52nuso4` (`movie_user`),
  CONSTRAINT `FK_6ngdl7jtwnsyxrr7sg52nuso4` FOREIGN KEY (`movie_user`) REFERENCES `movie_user` (`id
`),
  CONSTRAINT `FK_6uqpdjxf5apmic72r2s9fn98p` FOREIGN KEY (`movie`) REFERENCES `movie` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_rental`
--

LOCK TABLES `movie_rental` WRITE;
/*!40000 ALTER TABLE `movie_rental` DISABLE KEYS */;
INSERT INTO `movie_rental` VALUES (1,'adfaf','2013-11-14 00:00:00','2013-11-05 00:00:00','2013-11-1
2 00:00:00',NULL,0,1,1);
/*!40000 ALTER TABLE `movie_rental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_user`
--

DROP TABLE IF EXISTS `movie_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_line1` varchar(100) NOT NULL,
  `address_line2` varchar(100) NOT NULL,
  `c_state` varchar(255) NOT NULL,
  `cellphone` varchar(15) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `email` varchar(40) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `password` varchar(15) NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  `user_name` varchar(30) NOT NULL,
  `user_role` varchar(15) NOT NULL,
  `user_state` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `zip_code` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_user`
--

LOCK TABLES `movie_user` WRITE;
/*!40000 ALTER TABLE `movie_user` DISABLE KEYS */;
INSERT INTO `movie_user` VALUES (1,'4949','','ca','3923409234','Fremont','USA','2013-11-09 00:00:00
','user@movie.com','user','last','user1234',NULL,'user1','member','active',0,'94535');
/*!40000 ALTER TABLE `movie_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;