-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: database_chodoido_ute
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `buy`
--

DROP TABLE IF EXISTS `buy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buy` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` enum('CHO_XAC_NHAN','DANG_GIAO','THANH_CONG') DEFAULT NULL,
  `time_buy` date DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  `id_user_buy` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiijmrt70f2gl5radv8toqvq6a` (`id_product`),
  KEY `FKgg9e1g4tvdns5wm5sravrgy1q` (`id_user_buy`),
  CONSTRAINT `FKgg9e1g4tvdns5wm5sravrgy1q` FOREIGN KEY (`id_user_buy`) REFERENCES `user` (`id`),
  CONSTRAINT `FKiijmrt70f2gl5radv8toqvq6a` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buy`
--

LOCK TABLES `buy` WRITE;
/*!40000 ALTER TABLE `buy` DISABLE KEYS */;
/*!40000 ALTER TABLE `buy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follower`
--

DROP TABLE IF EXISTS `follower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follower` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_user` bigint DEFAULT NULL,
  `id_user_follower` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKevqabq4adwsupvfqyrtiw4hko` (`id_user`),
  KEY `FKbbef37d6xm2qhkuhk8hhgp20n` (`id_user_follower`),
  CONSTRAINT `FKbbef37d6xm2qhkuhk8hhgp20n` FOREIGN KEY (`id_user_follower`) REFERENCES `user` (`id`),
  CONSTRAINT `FKevqabq4adwsupvfqyrtiw4hko` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follower`
--

LOCK TABLES `follower` WRITE;
/*!40000 ALTER TABLE `follower` DISABLE KEYS */;
/*!40000 ALTER TABLE `follower` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `count` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_new` bit(1) NOT NULL,
  `post_product_status` enum('DA_AN','DA_BAN','DA_DUYET','DA_TUCHOI') DEFAULT NULL,
  `price` double DEFAULT NULL,
  `time_post` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `id_category` bigint DEFAULT NULL,
  `id_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5cxv31vuhc7v32omftlxa8k3c` (`id_category`),
  KEY `FKid3isj76xjkdr11j0kt10c4l7` (`id_user`),
  CONSTRAINT `FK5cxv31vuhc7v32omftlxa8k3c` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`),
  CONSTRAINT `FKid3isj76xjkdr11j0kt10c4l7` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_img`
--

DROP TABLE IF EXISTS `product_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_img` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `url_img` varchar(255) DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8ftr7ehor4vnc9tp0onlmucga` (`id_product`),
  CONSTRAINT `FK8ftr7ehor4vnc9tp0onlmucga` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_img`
--

LOCK TABLES `product_img` WRITE;
/*!40000 ALTER TABLE `product_img` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_video`
--

DROP TABLE IF EXISTS `product_video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_video` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `video_url` varchar(255) DEFAULT NULL,
  `id_product` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm3pdx7oy4wyxerk6jk2qriuhh` (`id_product`),
  CONSTRAINT `FKm3pdx7oy4wyxerk6jk2qriuhh` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_video`
--

LOCK TABLES `product_video` WRITE;
/*!40000 ALTER TABLE `product_video` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `facebook` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `gmail` varchar(255) DEFAULT NULL,
  `local` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `point` bigint DEFAULT NULL,
  `product_lost` bigint DEFAULT NULL,
  `product_success` bigint DEFAULT NULL,
  `role` enum('ADMIN','USER') DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `zalo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-19  3:58:12
