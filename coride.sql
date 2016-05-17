CREATE DATABASE  IF NOT EXISTS `coride` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `coride`;
-- MySQL dump 10.13  Distrib 5.6.27, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: coride
-- ------------------------------------------------------
-- Server version	5.6.27-0ubuntu0.15.04.1

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
-- Table structure for table `Location`
--

DROP TABLE IF EXISTS `Location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Location` (
  `email` varchar(255) NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Location`
--

LOCK TABLES `Location` WRITE;
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;
INSERT INTO `Location` VALUES ('1234',12.8436667,77.6628516),('123@gmail.com',NULL,NULL),('abc@gmail.com',12.8470713,77.6612938),('null',12.8435138,77.6630669),('qwert',12.8436656,77.66286),('rrttt',12.83,77.69);
/*!40000 ALTER TABLE `Location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill` (
  `bill_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `ride_id` int(11) NOT NULL,
  `driver_id` int(11) NOT NULL,
  `amount` float NOT NULL,
  `distance` float NOT NULL,
  `timestamp` datetime NOT NULL,
  PRIMARY KEY (`bill_id`),
  UNIQUE KEY `bill_id_UNIQUE` (`bill_id`)
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
-- Table structure for table `carpool_transaction`
--

DROP TABLE IF EXISTS `carpool_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carpool_transaction` (
  `ride_id` int(11) NOT NULL,
  `offer_id` int(11) NOT NULL,
  PRIMARY KEY (`ride_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carpool_transaction`
--

LOCK TABLES `carpool_transaction` WRITE;
/*!40000 ALTER TABLE `carpool_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `carpool_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `citycab`
--

DROP TABLE IF EXISTS `citycab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `citycab` (
  `ride_id` int(11) NOT NULL,
  `distance` float NOT NULL,
  `source` varchar(150) NOT NULL,
  `destination` varchar(150) NOT NULL,
  `tolerance` int(11) NOT NULL,
  `gender` binary(1) NOT NULL,
  `user_mobile` varchar(45) DEFAULT NULL,
  `driver_mobile` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT 'open',
  PRIMARY KEY (`ride_id`),
  UNIQUE KEY `ride_id_UNIQUE` (`ride_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `citycab`
--

LOCK TABLES `citycab` WRITE;
/*!40000 ALTER TABLE `citycab` DISABLE KEYS */;
/*!40000 ALTER TABLE `citycab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `driver` (
  `driver_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `license_no` varchar(50) NOT NULL,
  `pan` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `phone_no` varchar(45) DEFAULT NULL,
  `vehicle_no` varchar(45) DEFAULT NULL,
  `model` varchar(45) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `vehicle_id` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `regid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`driver_id`),
  UNIQUE KEY `driver_id_UNIQUE` (`driver_id`),
  UNIQUE KEY `license_no_UNIQUE` (`license_no`),
  UNIQUE KEY `pan_UNIQUE` (`pan`),
  UNIQUE KEY `phone_no_UNIQUE` (`phone_no`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (29,'prabhakar','chittoor','asff','dfgh','1234567890','8500557505','dghj','dfhh',5,NULL,'zxc@gmail.com',12.8435727,77.6627845,'APA91bGXQB_m7OiTxEhlzb5VnXimxu3t1yPizcHfPLmzuyyK6exwLJW-60CzVrqNUf__Xb7Kx0gFAmwAtH2njKtW-dt2u97GeM32NM4OKDCo4GP4dbfhKy1rEN_rDTxG4b6I_0DN5gnE'),(30,'prabha','banglore','asdd','swty','1234567','8500557589','sdfh','sdgh',4,NULL,'asdd@gmb.com',12.84472959,77.66255921,'');
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `group_id` int(11) NOT NULL,
  `ride_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `long_ride`
--

DROP TABLE IF EXISTS `long_ride`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `long_ride` (
  `ride_id` int(11) NOT NULL,
  `offer_id` int(11) NOT NULL,
  PRIMARY KEY (`ride_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `long_ride`
--

LOCK TABLES `long_ride` WRITE;
/*!40000 ALTER TABLE `long_ride` DISABLE KEYS */;
/*!40000 ALTER TABLE `long_ride` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `request_no` varchar(45) NOT NULL,
  `driver_mobile` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT 'open',
  PRIMARY KEY (`notification_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer`
--

DROP TABLE IF EXISTS `offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offer` (
  `offer_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `type` varchar(20) NOT NULL,
  `source` varchar(150) NOT NULL,
  `destination` varchar(150) NOT NULL,
  `tolerance` int(11) NOT NULL,
  `charge` int(11) NOT NULL,
  `gender_preference` binary(1) NOT NULL,
  PRIMARY KEY (`offer_id`),
  UNIQUE KEY `offer_id_UNIQUE` (`offer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer`
--

LOCK TABLES `offer` WRITE;
/*!40000 ALTER TABLE `offer` DISABLE KEYS */;
/*!40000 ALTER TABLE `offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `sex` tinyint(1) NOT NULL DEFAULT '1',
  `birthyear` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
INSERT INTO `people` VALUES (10,'prabhakar',0,2010);
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_mobile` varchar(45) NOT NULL,
  `source` varchar(45) DEFAULT NULL,
  `destination` varchar(45) DEFAULT NULL,
  `tolerance` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `req_no` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT 'open',
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,'8500557504','bangalore','Mysore','15','Female','1','open'),(2,'8500557504','bangalore','Mysore','15','Female','1','open'),(3,'8500557504','bangalore','Mysore','15','Female','1','open'),(4,'8500557504','bangalore','Mysore','15','Female','1','open'),(5,'8500557504','bangalore','Mysore','15','Female','1','open'),(6,'8500557504','bangalore','Mysore','15','Female','1','open'),(7,'8500557504','bangalore','Mysore','15','Female','1','open'),(8,'8500557504','bangalore','Mysore','15','Female','1','open'),(9,'8500557504','bangalore','Mysore','15','Female','2','open'),(10,'8500557504','bangalore','Mysore','15','Female','1','open'),(11,'8500557504','bangalore','Mysore','15','Female','1','open'),(12,'8500557504','bangalore','Mysore','15','Female','2','open'),(13,'8500557504','bangalore','Mysore','15','Female','1','open'),(14,'8500557504','bangalore','Mysore','15','Female','1','open');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `ride_id` int(11) NOT NULL,
  `driver_id` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `review_text` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  UNIQUE KEY `review_id_UNIQUE` (`review_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ride`
--

DROP TABLE IF EXISTS `ride`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ride` (
  `ride_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  `timestamp` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ride_id`),
  UNIQUE KEY `ride_id_UNIQUE` (`ride_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ride`
--

LOCK TABLES `ride` WRITE;
/*!40000 ALTER TABLE `ride` DISABLE KEYS */;
/*!40000 ALTER TABLE `ride` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(120) DEFAULT NULL,
  `gender` varchar(45) NOT NULL,
  `phone_no` varchar(10) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `regid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `phone_no_UNIQUE` (`phone_no`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (31,'raja','Tamilnadu','Male','8500557504','12345','raja@gmail.com','APA91bHX7JmGpQpkULsM34kDydVyAVliw69hL5S8bo_KFnZjX6JyZw_6wDY0ePRNkRNbt_dT4E9zUuUCF_Y4cdfOz4SdOARdwF-pfGvbOtkQpiXGsB3ZWNrNJKcog_KXxomP7UWmtK8f'),(33,'bharath','gg','Any','9486216194','12345','bar@gmail.com','APA91bHn3zLYjofUgPuh4uFkVbps_HDUcvmphiZNadqWIvYwjIFrkzo7LglW42PN4bpTD38gHZDxYdctmP-9SSVJ8vqfT8tQ3XqJWx74JjbxnVcoshCTY30WVQh4AaiW2i_NMWGwvXPs'),(34,'p','asd','Male','8500557555','098765','adf@cgh.com','APA91bGXQB_m7OiTxEhlzb5VnXimxu3t1yPizcHfPLmzuyyK6exwLJW-60CzVrqNUf__Xb7Kx0gFAmwAtH2njKtW-dt2u97GeM32NM4OKDCo4GP4dbfhKy1rEN_rDTxG4b6I_0DN5gnE'),(35,'prabhakar','adf','Male','8500557502','12345','asd@ffh.com','APA91bGXQB_m7OiTxEhlzb5VnXimxu3t1yPizcHfPLmzuyyK6exwLJW-60CzVrqNUf__Xb7Kx0gFAmwAtH2njKtW-dt2u97GeM32NM4OKDCo4GP4dbfhKy1rEN_rDTxG4b6I_0DN5gnE');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
  `vehicle_id` int(11) NOT NULL AUTO_INCREMENT,
  `reg_no` varchar(45) NOT NULL,
  `model_no` varchar(45) NOT NULL,
  `capacity` int(11) NOT NULL,
  PRIMARY KEY (`vehicle_id`),
  UNIQUE KEY `vehicle_id_UNIQUE` (`vehicle_id`),
  UNIQUE KEY `reg_no_UNIQUE` (`reg_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-14  0:24:43
