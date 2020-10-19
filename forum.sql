-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: localhost    Database: forum
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `content` text,
  `entity_id` int(11) NOT NULL,
  `entity_type` int(11) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `user_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_user_id_fk` (`user_id`),
  CONSTRAINT `comment_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (3,'this is a comment',15,0,'2020-08-15 19:38:32',3),(4,'this is a comment again',15,0,'2020-08-15 19:38:47',3),(5,'this is my comment',15,0,'2020-08-15 19:53:46',7),(6,'this is my comment',15,0,'2020-08-15 19:53:46',7),(7,'this is my comment',15,0,'2020-08-15 20:05:18',7),(8,'this is my comment again',15,0,'2020-08-16 21:48:21',7),(9,'this is my comment again hhh',15,0,'2020-08-16 21:48:21',7),(10,'comment comment comment',15,0,'2020-08-16 21:48:21',7),(11,'comment comment comment',15,0,'2020-08-16 21:48:21',8),(12,'comment comment comment',15,0,'2020-08-16 21:48:21',8),(13,'comment comment comment',15,0,'2020-08-16 21:48:21',8),(14,'comment comment comment',15,0,'2020-08-16 21:48:21',8),(15,'comment comment comment',15,0,'2020-08-16 22:04:02',7),(16,'comment comment comment',15,0,'2020-08-16 22:04:02',7),(17,'comment comment comment',15,0,'2020-08-16 22:07:23',7),(18,'comment comment comment',15,0,'2020-08-16 22:07:23',7),(19,'comment comment comment',15,0,'2020-08-16 22:07:23',8),(20,'comment comment comment',15,0,'2020-08-16 22:07:23',8),(21,'comment comment comment',15,0,'2020-08-16 22:09:05',8),(22,'comment comment comment',15,0,'2020-08-16 22:09:05',8);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed`
--

DROP TABLE IF EXISTS `feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `data` tinytext,
  PRIMARY KEY (`id`),
  KEY `user_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed`
--

LOCK TABLES `feed` WRITE;
/*!40000 ALTER TABLE `feed` DISABLE KEYS */;
INSERT INTO `feed` VALUES (3,1,8,'2020-08-16 21:48:21','{\"questionId\":\"15\",\"questionTitle\":\"first question\",\"userId\":\"7\"}'),(4,1,8,'2020-08-16 21:48:21','{\"questionId\":\"15\",\"questionTitle\":\"first question\",\"userId\":\"7\"}'),(5,1,8,'2020-08-16 21:48:21','{\"questionId\":\"15\",\"questionTitle\":\"first question\",\"userId\":\"7\"}'),(6,1,8,'2020-08-16 21:48:21','{\"questionId\":\"15\",\"questionTitle\":\"first question\",\"userId\":\"8\"}'),(7,1,8,'2020-08-16 21:48:21','{\"questionId\":\"15\",\"questionTitle\":\"first question\",\"userId\":\"8\"}'),(8,1,8,'2020-08-16 21:48:21','{\"questionId\":\"15\",\"questionTitle\":\"first question\",\"userId\":\"8\"}'),(9,1,8,'2020-08-16 21:48:21','{\"questionId\":\"15\",\"questionTitle\":\"first question\",\"userId\":\"8\"}'),(10,1,7,'2020-08-16 22:07:23','{\"questionId\":\"15\",\"questionTitle\":\"first question\",\"userId\":\"7\"}'),(11,1,7,'2020-08-16 22:07:23','{\"questionId\":\"15\",\"questionTitle\":\"first question\",\"userId\":\"7\"}'),(12,1,7,'2020-08-16 22:07:23','{\"questionId\":\"15\",\"questionTitle\":\"first question\",\"userId\":\"7\"}'),(13,1,7,'2020-08-16 22:07:23','{\"questionId\":\"15\",\"questionTitle\":\"first question\",\"userId\":\"7\"}'),(14,1,8,'2020-08-16 22:07:23','{\"questionId\":\"15\",\"questionTitle\":\"first question\",\"userId\":\"8\"}'),(15,1,8,'2020-08-16 22:07:23','{\"questionId\":\"15\",\"questionTitle\":\"first question\",\"userId\":\"8\"}'),(16,1,8,'2020-08-16 22:09:05','{\"questionId\":\"15\",\"questionTitle\":\"first question\",\"userId\":\"8\"}'),(17,1,8,'2020-08-16 22:09:05','{\"questionId\":\"15\",\"questionTitle\":\"first question\",\"userId\":\"8\"}'),(18,1,2,'2020-08-16 22:33:36','test'),(19,1,2,'2020-08-16 22:33:59','test'),(20,1,2,'2020-09-02 22:50:44','test');
/*!40000 ALTER TABLE `feed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `from_id` int(10) unsigned DEFAULT NULL,
  `to_id` int(10) unsigned DEFAULT NULL,
  `content` text,
  `create_at` datetime DEFAULT NULL,
  `has_read` tinyint(1) DEFAULT NULL,
  `conversation_id` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,7,3,'hello','2020-08-15 20:30:39',0,'3-7'),(3,7,3,'Im a robot','2020-08-15 20:44:16',0,'3-7');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  `create_at` datetime NOT NULL,
  `comment_count` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `date_index` (`create_at`),
  KEY `question_user_id_fk` (`user_id`),
  CONSTRAINT `question_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'test','test Content',3,'2020-08-14 22:34:58',0),(2,'test0','test Content0',3,'2020-08-14 22:54:48',0),(3,'test1','test Content1',3,'2020-08-14 22:54:48',0),(4,'test2','test Content2',3,'2020-08-14 22:54:48',0),(5,'test3','test Content3',3,'2020-08-14 22:54:48',0),(6,'test4','test Content4',3,'2020-08-14 22:54:48',0),(7,'test5','test Content5',3,'2020-08-14 22:54:48',0),(8,'test6','test Content6',3,'2020-08-14 22:54:48',0),(9,'test7','test Content7',3,'2020-08-14 22:54:48',0),(10,'test8','test Content8',3,'2020-08-14 22:54:48',0),(11,'test9','test Content9',3,'2020-08-14 22:54:48',0),(12,'ss','11',3,'2020-08-15 01:02:20',0),(13,'ss','11',3,'2020-08-15 14:38:37',0),(14,'first question','shuai shuai shuai',7,'2020-08-15 16:46:10',0),(15,'first question','shuai shuai shuai',7,'2020-08-15 16:52:08',20);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `password` varchar(128) NOT NULL,
  `salt` varchar(32) NOT NULL,
  `head_url` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index2` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*INSERT INTO `user` VALUES (3,'jiawei','123','adsfafd','asdfasf',0),(4,'name','123','s','1',0),(5,'nae','111','sss','head',0),(6,'jiawei123','F8F834832E90878C3D815C97E9600B73','6b78c','www.baidu.com',0),(7,'jiaweiwu7','9FA03476A55A46FB77C2112350408A10','ca43f','www.baidu.com',0),(8,'jiaweiwu1','D9E315F8C3E03EA00DDC423CD85D65AD','f075e','www.baidu.com',0),(9,'a','aaa','aaa','aaa',90),(10,'a','aaa','aaa','aaa',90),(11,'b','aaa','aaa','aaa',80),(12,'b','aaa','aaa','aaa',80),(13,'a','aaa','aaa','aaa',90),(14,'a','aaa','aaa','aaa',90),(15,'b','aaa','aaa','aaa',80),(16,'b','aaa','aaa','aaa',80);
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

-- Dump completed on 2020-10-18 15:11:59
