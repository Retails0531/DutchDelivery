-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: database-1.cicqmw0sdjwp.ap-northeast-2.rds.amazonaws.com    Database: dutchdelivery
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `user info`
--

DROP TABLE IF EXISTS `user info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user info` (
  `ID` varchar(20) NOT NULL,
  `post number` varchar(10) NOT NULL,
  `menu` varchar(30) NOT NULL,
  `chat number` varchar(10) NOT NULL,
  `quick match` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`,`post number`),
  KEY `uesr info_post number_fk_idx` (`post number`),
  KEY `user info_menu_fk_idx` (`menu`),
  KEY `user info_chat number_fk_idx` (`chat number`),
  CONSTRAINT `user info_chat number_fk` FOREIGN KEY (`chat number`) REFERENCES `post` (`chat number`) ON UPDATE CASCADE,
  CONSTRAINT `user info_id_fk` FOREIGN KEY (`ID`) REFERENCES `users` (`ID`) ON UPDATE CASCADE,
  CONSTRAINT `user info_menu_fk` FOREIGN KEY (`menu`) REFERENCES `menu` (`store number`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `user info_post number_fk` FOREIGN KEY (`post number`) REFERENCES `post` (`post number`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user info`
--

LOCK TABLES `user info` WRITE;
/*!40000 ALTER TABLE `user info` DISABLE KEYS */;
/*!40000 ALTER TABLE `user info` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-10 18:39:01
