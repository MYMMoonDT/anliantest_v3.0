-- MySQL dump 10.13  Distrib 5.6.10, for osx10.7 (x86_64)
--
-- Host: localhost    Database: anliantest_db1
-- ------------------------------------------------------
-- Server version	5.6.10

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
-- Dumping data for table `authority_group`
--

LOCK TABLES `authority_group` WRITE;
/*!40000 ALTER TABLE `authority_group` DISABLE KEYS */;
INSERT INTO `authority_group` VALUES (1,'市场部',1),(2,'评价部',2),(3,'检测部',3),(4,'行政部',4),(5,'质控部',5),(6,'总经理',6),(7,'技术负责人',7),(8,'质量负责人',8);
/*!40000 ALTER TABLE `authority_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `authority_group_item`
--

LOCK TABLES `authority_group_item` WRITE;
/*!40000 ALTER TABLE `authority_group_item` DISABLE KEYS */;
INSERT INTO `authority_group_item` VALUES (1,1),(1,2),(2,2),(1,3),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8);
/*!40000 ALTER TABLE `authority_group_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `authority_item`
--

LOCK TABLES `authority_item` WRITE;
/*!40000 ALTER TABLE `authority_item` DISABLE KEYS */;
INSERT INTO `authority_item` VALUES (1,'auth_1'),(2,'auth_2'),(3,'auth_3'),(4,'auth_4'),(5,'auth_5'),(6,'auth_6'),(7,'auth_7'),(8,'auth_8');
/*!40000 ALTER TABLE `authority_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `employee_authority_group`
--

LOCK TABLES `employee_authority_group` WRITE;
/*!40000 ALTER TABLE `employee_authority_group` DISABLE KEYS */;
INSERT INTO `employee_authority_group` VALUES (1,'','市场部',9,1),(3,'','评价部',6,2),(4,'','评价部',7,2),(5,'','评价部',8,2),(6,'','评价部',10,2),(7,'','评价部',16,2),(8,'','检测部',4,3),(9,'','检测部',5,3),(10,'','检测部',17,3),(11,'','行政部',18,4),(12,'','质控部',19,5),(13,'','总经理',15,6),(14,'','技术负责人',3,7),(15,'','质量负责人',20,8),(21,'','总经理',11,6),(22,'\0','评价部',11,2),(23,'\0','市场部',11,1);
/*!40000 ALTER TABLE `employee_authority_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `employee_authority_group_item`
--

LOCK TABLES `employee_authority_group_item` WRITE;
/*!40000 ALTER TABLE `employee_authority_group_item` DISABLE KEYS */;
INSERT INTO `employee_authority_group_item` VALUES (1,'',1,1),(2,'',1,2),(3,'',2,3),(4,'',2,4),(5,'',2,5),(6,'',2,6),(7,'',2,7),(8,'',3,8),(9,'',3,9),(10,'',3,10),(11,'',4,11),(12,'',5,12),(13,'',6,13),(14,'',7,14),(15,'\0',8,15);
/*!40000 ALTER TABLE `employee_authority_group_item` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-08 12:11:07