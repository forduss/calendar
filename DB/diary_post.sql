-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: diary
-- ------------------------------------------------------
-- Server version	8.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_no` int NOT NULL AUTO_INCREMENT,
  `board_no` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `userid` varchar(8) DEFAULT NULL,
  `hit_cnt` int DEFAULT NULL,
  PRIMARY KEY (`post_no`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,3,'관리자','관리자','2024-08-16','2024-08-16','admin',3),(2,2,'디자인 1안','디자인 1안 입니다.','2024-08-16','2024-08-16','admin',4),(3,2,'디자인 2안','디자인 2안 입니다','2024-08-16','2024-08-16','admin',2),(4,1,'test1','test1','2024-08-16','2024-08-16','admin',0),(5,1,'test2','test2','2024-08-16','2024-08-16','admin',0),(6,1,'test3','test3','2024-08-16','2024-08-16','admin',0),(7,1,'test4','test4','2024-08-16','2024-08-16','admin',0),(8,1,'test5','test5','2024-08-16','2024-08-16','admin',0),(9,1,'test6','test6','2024-08-16','2024-08-16','admin',0),(10,1,'test7','test7','2024-08-16','2024-08-16','admin',0),(11,1,'test8','test8','2024-08-16','2024-08-16','admin',0),(12,1,'test9','test9','2024-08-16','2024-08-16','admin',0),(13,1,'test10','test10','2024-08-16','2024-08-16','admin',0);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-22 17:41:00
