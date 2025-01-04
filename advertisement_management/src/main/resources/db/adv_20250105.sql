-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: adv
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `advertisement`
--

DROP TABLE IF EXISTS `advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `advertisement` (
  `ad_id` int NOT NULL AUTO_INCREMENT,
  `article_id` int DEFAULT NULL,
  `ad_name` varchar(45) DEFAULT NULL,
  `ad_description` mediumtext,
  `ad_image_url` json DEFAULT NULL,
  `ad_url` varchar(255) DEFAULT NULL,
  `ad_feature` varchar(255) DEFAULT NULL,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `ad_start_time` timestamp(4) NULL DEFAULT NULL,
  `ad_end_time` timestamp(4) NULL DEFAULT NULL,
  `ad_status` tinyint(1) DEFAULT NULL,
  `visit_count` int DEFAULT NULL,
  `click_count` int DEFAULT NULL,
  `conversion_count` int DEFAULT NULL,
  `click_through_rate` double DEFAULT NULL,
  `conversion_rate` double DEFAULT NULL,
  `revenue` double DEFAULT NULL,
  `last_visit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `advertiser_id` int DEFAULT NULL,
  `placement_location` json DEFAULT NULL,
  `target_audience` varchar(255) DEFAULT NULL,
  `impression_count` int DEFAULT NULL,
  `cost_per_click` double DEFAULT NULL,
  `cost_per_conversion` double DEFAULT NULL,
  `ad_content` mediumtext,
  `ad_detailed_page_url` varchar(255) DEFAULT NULL,
  `ad_create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `keywords` json DEFAULT NULL,
  `ad_category_id` int DEFAULT NULL,
  PRIMARY KEY (`ad_id`),
  KEY `fk_advertisement_user` (`advertiser_id`),
  KEY `fk_advertisement_article` (`article_id`),
  CONSTRAINT `fk_advertisement_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_advertisement_user` FOREIGN KEY (`advertiser_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisement`
--

LOCK TABLES `advertisement` WRITE;
/*!40000 ALTER TABLE `advertisement` DISABLE KEYS */;
/*!40000 ALTER TABLE `advertisement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ad_id` int DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `content` longtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (48,0,'11111','zxr','nihao'),(49,0,'11111','zxr','nihao'),(50,0,'11111','zxr','nihao'),(51,0,'11111','zxr','nihao'),(52,0,'11111','zxr','nihao'),(53,0,'11111','zxr','nihao'),(54,0,'11111','zxr','nihao'),(55,0,'11111','zxr','nihao'),(56,0,'11111','zxr','nihao'),(57,0,'11111','zxr','nihao'),(58,0,'11111','zxr','nihao'),(59,0,'11111','zxr','nihao'),(60,0,'11111','zxr','nihao'),(61,0,'11111','zxr','nihao'),(62,0,'11111','zxr','nihao'),(63,0,'11111','zxr','nihao'),(64,0,'11111','zxr','nihao'),(65,0,'11111','zxr','nihao'),(66,0,'11111','zxr','nihao'),(67,0,'11111','zxr','nihao'),(68,0,'11111','zxr','nihao');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `user_pk2` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (10005,'1','$2a$12$DqLi9kLUI1hFZlCQ5VCx5uxA7O.ctKJf7cfF2rK0v/jZ72LpwxbHu','2024-12-30 10:11:25','2024-12-30 10:11:25');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NOT NULL COMMENT '用户id',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_role_user_id_fk` (`user_id`) USING BTREE,
  CONSTRAINT `user_role_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_train_data`
--

DROP TABLE IF EXISTS `user_train_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_train_data` (
  `id` int NOT NULL AUTO_INCREMENT,
  `finger_print` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `age` int NOT NULL,
  `gender` enum('男','女','不愿透露') NOT NULL,
  `occupation` enum('学生','老师','白领','蓝领','研究工作者','工程师','服务工作者','不愿透露') NOT NULL,
  `education_level` enum('初中','高中','大学','不愿透露') NOT NULL,
  `region` enum('上海','北京','广州','深圳','武汉','成都','西安','杭州','南京','重庆','天津','苏州','长沙','青岛','大连','厦门','宁波','沈阳','昆明','无锡') NOT NULL,
  `country` enum('中国') NOT NULL,
  `device` enum('台式设备','笔记本电脑','平板电脑','智能手机','其他设备') NOT NULL,
  `Fashion` int DEFAULT NULL,
  `Art` int DEFAULT NULL,
  `Entertainment` int DEFAULT NULL,
  `Education` int DEFAULT NULL,
  `Pets` int DEFAULT NULL,
  `Eco` int DEFAULT NULL,
  `Weather` int DEFAULT NULL,
  `Technology` int DEFAULT NULL,
  `Politics` int DEFAULT NULL,
  `Economy` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_train_data_user_username_fk` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_train_data`
--

LOCK TABLES `user_train_data` WRITE;
/*!40000 ALTER TABLE `user_train_data` DISABLE KEYS */;
INSERT INTO `user_train_data` VALUES (1,'6VX8BH5duEfz6CeY','user_23402',65,'女','白领','初中','青岛','中国','其他设备',93,76,22,95,82,18,44,95,12,61),(2,'JjrTZF428nU5IHbH','user_78403',18,'女','老师','不愿透露','无锡','中国','笔记本电脑',96,76,91,16,65,45,16,82,17,8),(3,'u3we9G0g9AzQEUxA','user_35114',38,'女','白领','高中','青岛','中国','智能手机',72,60,65,96,67,50,58,82,91,84),(4,'yv6qjICCEJIYBq5Y','user_72330',28,'女','蓝领','初中','厦门','中国','笔记本电脑',78,98,87,81,68,61,38,18,96,97),(5,'JjT1L1Hn1xs5MKGA','user_11492',65,'不愿透露','研究工作者','大学','南京','中国','平板电脑',29,19,61,96,74,35,31,23,92,51),(6,'KX5ADiiuR1qtlBMJ','user_92479',35,'女','学生','高中','广州','中国','智能手机',94,62,74,80,60,23,72,76,96,87),(7,'dvbugs0CqFVhHKer','user_76405',34,'不愿透露','服务工作者','大学','昆明','中国','平板电脑',8,91,47,90,27,53,71,85,96,85),(8,'kFu7qo3RallmLPVt','user_48500',17,'女','服务工作者','大学','宁波','中国','台式设备',94,60,91,85,79,5,14,46,30,53),(9,'ewyhwUqnaNuKHewp','user_48707',29,'女','服务工作者','大学','长沙','中国','智能手机',68,96,84,87,95,72,43,47,83,97),(10,'jZEJS3byclDmd3xZ','user_34687',13,'男','不愿透露','高中','青岛','中国','台式设备',91,15,84,7,62,71,81,67,62,97);
/*!40000 ALTER TABLE `user_train_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-05  0:18:40
