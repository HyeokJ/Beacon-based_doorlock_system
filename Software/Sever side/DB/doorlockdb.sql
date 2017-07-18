-- MySQL dump 10.13  Distrib 5.6.36, for Win64 (x86_64)
--
-- Host: localhost    Database: doorlockdb
-- ------------------------------------------------------
-- Server version	5.6.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES UTF8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `destination`
--

DROP TABLE IF EXISTS `destination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `destination` (
  `Dst` varchar(30) NOT NULL,
  `Door1` varchar(30) DEFAULT NULL,
  `Door2` varchar(30) DEFAULT NULL,
  `Door3` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Dst`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination`
--

LOCK TABLES `destination` WRITE;
/*!40000 ALTER TABLE `destination` DISABLE KEYS */;
INSERT INTO `destination` VALUES ('medicine','door2','door4',NULL),('surgery','door1','door2','door3');
/*!40000 ALTER TABLE `destination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest_inf`
--

DROP TABLE IF EXISTS `guest_inf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guest_inf` (
  `Gnumber` varchar(30) NOT NULL,
  `IMEInum` varchar(30) DEFAULT NULL,
  `MyDst` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Gnumber`),
  KEY `MyDst` (`MyDst`),
  CONSTRAINT `guest_inf_ibfk_1` FOREIGN KEY (`MyDst`) REFERENCES `destination` (`Dst`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest_inf`
--

LOCK TABLES `guest_inf` WRITE;
/*!40000 ALTER TABLE `guest_inf` DISABLE KEYS */;
INSERT INTO `guest_inf` VALUES ('000001','12 345678 111111 0',NULL),('000002','22 333333 444444 5',NULL),('000003',NULL,NULL),('000004',NULL,NULL);
/*!40000 ALTER TABLE `guest_inf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token_inf`
--

DROP TABLE IF EXISTS `token_inf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token_inf` (
  `Snum` varchar(30) NOT NULL,
  `Gnum` varchar(30) DEFAULT NULL,
  `Secretkey` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Snum`),
  KEY `Gnum` (`Gnum`),
  CONSTRAINT `token_inf_ibfk_1` FOREIGN KEY (`Gnum`) REFERENCES `guest_inf` (`Gnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_inf`
--

LOCK TABLES `token_inf` WRITE;
/*!40000 ALTER TABLE `token_inf` DISABLE KEYS */;
INSERT INTO `token_inf` VALUES ('0000 0000 0000 0000','000001','1234567890'),('1111 1111 1111 1111',NULL,NULL),('2121 1212 2323 3232',NULL,NULL),('3333 3333 3333 3333',NULL,NULL);
/*!40000 ALTER TABLE `token_inf` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-18 18:42:35
