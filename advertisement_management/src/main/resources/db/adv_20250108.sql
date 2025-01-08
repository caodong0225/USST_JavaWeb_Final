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
  `region` enum('上海','北京','广州','深圳','武汉','成都','西安','杭州','南京','重庆','天津','苏州','长沙','青岛','大连','厦门','宁波','沈阳','昆明','无锡','不愿透露') NOT NULL,
  `country` enum('中国','不愿透露') NOT NULL,
  `device` enum('台式设备','笔记本电脑','平板电脑','智能手机','其他设备','不愿透露') NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_train_data`
--

LOCK TABLES `user_train_data` WRITE;
/*!40000 ALTER TABLE `user_train_data` DISABLE KEYS */;
INSERT INTO `user_train_data` VALUES (1,'6VX8BH5duEfz6CeY','user_23402',65,'女','白领','初中','青岛','中国','其他设备',93,76,22,95,82,18,44,95,12,61),(2,'JjrTZF428nU5IHbH','user_78403',18,'女','老师','不愿透露','无锡','中国','笔记本电脑',96,76,91,16,65,45,16,82,17,8),(3,'u3we9G0g9AzQEUxA','user_35114',38,'女','白领','高中','青岛','中国','智能手机',72,60,65,96,67,50,58,82,91,84),(4,'yv6qjICCEJIYBq5Y','user_72330',28,'女','蓝领','初中','厦门','中国','笔记本电脑',78,98,87,81,68,61,38,18,96,97),(5,'JjT1L1Hn1xs5MKGA','user_11492',65,'不愿透露','研究工作者','大学','南京','中国','平板电脑',29,19,61,96,74,35,31,23,92,51),(6,'KX5ADiiuR1qtlBMJ','user_92479',35,'女','学生','高中','广州','中国','智能手机',94,62,74,80,60,23,72,76,96,87),(7,'dvbugs0CqFVhHKer','user_76405',34,'不愿透露','服务工作者','大学','昆明','中国','平板电脑',8,91,47,90,27,53,71,85,96,85),(8,'kFu7qo3RallmLPVt','user_48500',17,'女','服务工作者','大学','宁波','中国','台式设备',94,60,91,85,79,5,14,46,30,53),(9,'ewyhwUqnaNuKHewp','user_48707',29,'女','服务工作者','大学','长沙','中国','智能手机',68,96,84,87,95,72,43,47,83,97),(10,'jZEJS3byclDmd3xZ','user_34687',13,'男','不愿透露','高中','青岛','中国','台式设备',91,15,84,7,62,71,81,67,62,97),(11,'KnGhgS7ELjOK81xd','user_26222',26,'女','不愿透露','大学','北京','中国','智能手机',71,74,82,93,74,10,42,50,94,87),(12,'T0OMTcIKRRO0I277','user_80482',60,'不愿透露','白领','初中','南京','中国','智能手机',70,3,64,31,35,71,18,41,77,37),(13,'K24KK2Si7Nz8JOKI','user_47622',57,'男','工程师','初中','西安','中国','智能手机',83,70,70,18,41,33,14,61,68,87),(14,'aWYdMemR7ivH3Zjn','user_59075',35,'女','学生','高中','无锡','中国','台式设备',87,92,32,84,90,9,86,71,100,85),(15,'ESEFDmgrG4QjFUCz','user_23714',39,'不愿透露','研究工作者','不愿透露','无锡','中国','其他设备',24,38,90,85,85,20,14,38,91,98),(16,'abxUH1krhucgIWE1','user_48196',52,'女','研究工作者','高中','苏州','中国','其他设备',86,78,38,98,88,3,52,36,24,84),(17,'HSTMY7LwTjA43YRS','user_13038',20,'女','工程师','初中','厦门','中国','其他设备',78,94,94,40,62,58,42,53,40,40),(18,'aOSSeZXayDtJL5oQ','user_13071',35,'不愿透露','研究工作者','初中','宁波','中国','平板电脑',31,95,100,92,94,90,69,39,93,89),(19,'i4whxH0mRmP7RpyJ','user_73045',4,'不愿透露','服务工作者','高中','沈阳','中国','平板电脑',98,47,96,86,61,32,61,21,63,28),(20,'u0uC5kEJA6EsLe6E','user_22524',64,'男','研究工作者','不愿透露','西安','中国','其他设备',13,36,45,53,89,77,76,76,79,96),(21,'YltIyFQrdE5YMgsA','user_13497',58,'女','老师','高中','成都','中国','智能手机',73,69,20,67,60,2,11,22,27,55),(22,'v9F0ep7xt2bR7nUr','user_70923',58,'女','学生','大学','广州','中国','其他设备',98,60,6,7,74,45,29,52,87,23),(23,'NblwyhxF6NohLGfq','user_53248',56,'不愿透露','蓝领','不愿透露','昆明','中国','其他设备',95,39,25,53,10,76,99,80,5,29),(24,'capzmM6BTc4cHfNN','user_96867',64,'男','学生','初中','青岛','中国','平板电脑',2,48,99,49,10,4,7,63,98,90),(25,'pE6kjl5NTS1gN3oZ','user_51534',2,'女','白领','初中','大连','中国','平板电脑',79,76,100,80,79,30,87,85,60,65),(26,'HyMurlHfaG1GPU53','user_58394',66,'不愿透露','服务工作者','大学','大连','中国','台式设备',68,16,95,85,71,18,4,4,95,1),(27,'oJPf73eCbfpZDUN4','user_73261',25,'女','蓝领','高中','长沙','中国','智能手机',82,92,100,1,92,34,59,94,54,28),(28,'mbjXNTtRNKsHANBH','user_69130',41,'女','白领','初中','沈阳','中国','智能手机',94,96,3,90,95,48,82,65,1,7),(29,'ctOGQ7ldFGUjdwx3','user_65543',45,'女','不愿透露','不愿透露','宁波','中国','智能手机',68,68,14,8,76,23,74,51,7,69),(30,'IN9z70GGFEKtJxzV','user_25044',65,'女','服务工作者','高中','杭州','中国','平板电脑',89,64,33,72,72,27,61,45,16,69),(31,'3532sU55hHwkoDrW','user_20039',53,'不愿透露','老师','高中','宁波','中国','其他设备',1,55,43,6,32,20,52,31,69,40),(32,'i2Y3RBhnFvhT6xon','user_25819',14,'女','不愿透露','不愿透露','成都','中国','平板电脑',64,97,86,53,67,95,77,21,55,67),(33,'K7zobA2OGMf9PdRL','user_59146',28,'女','工程师','初中','长沙','中国','笔记本电脑',80,91,17,84,70,67,6,70,97,93),(34,'QPc0fx1al4GBYAB0','user_67203',61,'不愿透露','服务工作者','初中','西安','中国','其他设备',60,100,95,30,14,42,89,48,53,1),(35,'0UophpwpqafjApmj','user_42311',16,'女','学生','初中','宁波','中国','平板电脑',75,65,97,34,85,15,9,95,88,62),(36,'MCa6aGvAoqr9rloI','user_30788',67,'男','老师','大学','广州','中国','智能手机',25,89,42,26,30,80,92,98,72,81),(37,'YXEed9j22COXcDEj','user_60688',17,'不愿透露','蓝领','大学','沈阳','中国','智能手机',95,68,92,84,10,45,1,56,33,96),(38,'FM104HA4X6vMbDM0','user_27576',23,'不愿透露','学生','不愿透露','成都','中国','其他设备',81,42,95,57,72,50,94,95,8,50),(39,'59uGf7p5md7M6ztB','user_95765',16,'男','学生','大学','广州','中国','台式设备',100,58,92,15,78,71,90,81,70,81),(40,'dezJy6oJnTl5DzKi','user_39793',31,'不愿透露','不愿透露','初中','北京','中国','笔记本电脑',95,27,79,80,74,41,34,47,85,93),(41,'8g76ubQgUW9ZFfV3','user_18114',63,'女','不愿透露','初中','无锡','中国','台式设备',79,68,67,28,88,28,55,25,50,30),(42,'BXjsYUPx9ZIatoS2','user_73574',16,'不愿透露','不愿透露','初中','厦门','中国','笔记本电脑',88,4,89,60,80,60,66,6,48,24),(43,'vqWgmZmgneKsfVgL','user_48997',55,'不愿透露','蓝领','不愿透露','杭州','中国','平板电脑',58,92,24,6,11,50,11,84,36,48),(44,'73RD9SyQ2VsnFjfu','user_47687',35,'不愿透露','服务工作者','高中','昆明','中国','台式设备',82,68,18,96,95,9,19,52,91,80),(45,'h7cmA2vSzvbJnpy7','user_93677',37,'男','不愿透露','不愿透露','深圳','中国','台式设备',34,35,12,83,37,24,41,64,94,93),(46,'EQCtlStQufW129PW','user_55877',63,'不愿透露','不愿透露','初中','沈阳','中国','笔记本电脑',44,33,33,35,45,5,47,49,99,2),(47,'Ljsdw5LETJeWsKHO','user_36408',49,'女','蓝领','大学','沈阳','中国','笔记本电脑',75,76,28,89,71,2,82,72,22,26),(48,'ifCj717xSAohljDD','user_66995',50,'男','蓝领','大学','青岛','中国','台式设备',71,72,53,29,95,1,9,62,70,97),(49,'rcTreqxfFcq8kRN4','user_50366',44,'不愿透露','学生','高中','青岛','中国','台式设备',45,56,78,35,34,3,36,6,5,84),(50,'9ltpKUIQocoaJImi','user_13128',35,'女','学生','不愿透露','沈阳','中国','智能手机',97,78,87,86,86,63,12,52,87,88),(51,'u1p9SGG01cShL4D1','user_80986',25,'男','白领','初中','无锡','中国','智能手机',96,62,88,69,34,49,81,65,69,98),(52,'jWNKGrfA5SRWkFLI','user_71309',50,'不愿透露','学生','初中','青岛','中国','笔记本电脑',60,25,27,81,65,29,98,90,38,35),(53,'H3WpfvpBKkXGDFQq','user_44714',57,'男','研究工作者','大学','西安','中国','台式设备',60,90,72,4,39,95,94,78,77,81),(54,'n6kJWwv3vz07W0pr','user_74138',38,'女','学生','初中','苏州','中国','其他设备',81,89,97,89,81,13,91,54,81,84),(55,'PVM0Qk30wJtL915R','user_82135',30,'不愿透露','蓝领','大学','厦门','中国','笔记本电脑',43,96,22,97,37,28,65,71,86,89),(56,'eVlFDuHd160liYSI','user_76305',8,'女','白领','初中','西安','中国','平板电脑',90,90,97,68,73,1,82,46,30,27),(57,'sGbmCBLQoDWj8oXz','user_84995',51,'不愿透露','老师','初中','重庆','中国','台式设备',27,31,63,42,40,22,8,5,25,66),(58,'TkV7UkGbRwHYiehZ','user_10458',58,'男','蓝领','不愿透露','昆明','中国','其他设备',16,7,99,22,32,13,65,81,65,84),(59,'SBPioNacHKHo9Jh0','user_17915',25,'女','蓝领','大学','大连','中国','平板电脑',96,93,98,33,72,89,79,85,78,27),(60,'ttLZ2C3LltUjWLKS','user_63598',39,'男','学生','大学','上海','中国','笔记本电脑',81,44,43,80,33,60,4,62,78,89),(61,'w2Zt6YEhebEvObWT','user_23670',20,'女','老师','高中','武汉','中国','笔记本电脑',88,73,84,34,74,34,13,55,5,7),(62,'oqgaWjX1FcuhybJH','user_49023',55,'不愿透露','白领','不愿透露','厦门','中国','其他设备',21,11,69,24,54,78,96,10,98,87),(63,'cogRQ9QnC8Aheymn','user_34163',45,'不愿透露','学生','不愿透露','北京','中国','其他设备',36,73,43,80,92,54,80,42,6,70),(64,'Xjx6X4Elj8Hga0HD','user_14209',39,'不愿透露','工程师','大学','武汉','中国','平板电脑',95,28,2,86,42,70,86,95,83,87),(65,'qG6Tix1GldtFVGDf','user_42929',11,'不愿透露','学生','高中','广州','中国','智能手机',90,55,93,12,51,13,27,80,22,33),(66,'s6V11Ut8PYIsHJbJ','user_57302',48,'女','老师','大学','武汉','中国','智能手机',64,98,20,72,67,55,9,32,47,60),(67,'gJ9FqpSsBAACHsvt','user_81165',5,'男','蓝领','高中','杭州','中国','平板电脑',91,7,99,34,81,88,61,81,62,87),(68,'oZJg8PXbElMGioat','user_68622',19,'不愿透露','研究工作者','高中','上海','中国','智能手机',96,50,94,5,69,39,8,54,67,25),(69,'jMR2FZNb2PD3kas3','user_30401',20,'女','研究工作者','初中','成都','中国','笔记本电脑',97,60,97,38,61,89,71,72,47,98),(70,'OOcF2z4q9A2DaHZp','user_55409',41,'不愿透露','学生','高中','苏州','中国','其他设备',89,21,37,66,14,74,30,68,95,16),(71,'BGhnAyucEnPOOZQR','user_14521',14,'男','研究工作者','大学','重庆','中国','笔记本电脑',89,13,84,75,61,39,74,90,92,62),(72,'V2Zzg6RRfokbousd','user_53997',56,'不愿透露','不愿透露','高中','深圳','中国','笔记本电脑',3,51,23,66,14,37,91,64,97,78),(73,'s3yJwyKiUqBont0y','user_66028',58,'女','老师','不愿透露','广州','中国','其他设备',82,87,0,53,63,32,79,52,39,18),(74,'s7P1HW0CWXpHpzph','user_35772',61,'男','工程师','高中','天津','中国','其他设备',6,33,22,34,89,46,52,99,73,73),(75,'vJbTGJVrs3WAmjXv','user_51281',44,'男','研究工作者','高中','天津','中国','笔记本电脑',7,46,50,61,51,57,53,93,97,64),(76,'ayuRiutbDZqTEY1M','user_89478',30,'男','工程师','高中','南京','中国','平板电脑',75,34,29,85,73,47,80,86,62,76),(77,'kYHclfJPiRQgj9RB','user_54406',61,'不愿透露','服务工作者','初中','深圳','中国','笔记本电脑',98,38,70,50,82,5,57,92,88,48),(78,'8sChMZBG4DfK5sbm','user_81858',29,'男','学生','初中','无锡','中国','笔记本电脑',30,52,69,90,13,31,38,85,81,87),(79,'TxCLZFpaC5ps47sg','user_25186',54,'男','工程师','大学','天津','中国','平板电脑',16,40,77,54,95,0,16,89,96,80),(80,'DJZCwwhdU46fqaJH','user_58130',33,'男','研究工作者','初中','武汉','中国','台式设备',71,64,47,90,34,96,33,84,60,61),(81,'TvEj2V01COkr1w8R','user_99601',43,'男','学生','初中','昆明','中国','平板电脑',90,14,91,72,85,26,89,93,69,67),(82,'tbvkbozv4d2R9Ulp','user_88712',0,'不愿透露','老师','初中','昆明','中国','平板电脑',100,81,87,5,99,97,25,14,15,97),(83,'dygoS41hoZRj0oj9','user_97631',49,'男','蓝领','初中','广州','中国','平板电脑',48,96,13,6,41,63,40,67,68,68),(84,'X0pj0bTLu9ZOAxRT','user_42467',21,'男','学生','高中','西安','中国','其他设备',92,58,96,41,71,40,53,72,68,77),(85,'6VAKK8X32oZltAAH','user_63441',25,'不愿透露','工程师','高中','西安','中国','台式设备',85,34,91,49,11,42,85,78,24,82),(86,'5avAJozWGsmJUqva','user_62421',51,'男','蓝领','不愿透露','沈阳','中国','笔记本电脑',68,45,31,98,26,44,96,98,92,67),(87,'aCsES51T3TWpRLSZ','user_33702',54,'男','老师','不愿透露','昆明','中国','笔记本电脑',18,94,72,55,85,55,65,97,63,81),(88,'rRxLcwtqtyLpBJ8n','user_47014',18,'女','工程师','大学','天津','中国','其他设备',80,75,94,100,93,58,46,22,19,44),(89,'cKvLyaiakFd4ArJu','user_39040',57,'不愿透露','学生','大学','青岛','中国','平板电脑',41,81,61,10,99,59,50,3,100,0),(90,'lisvOHso4W3RbrLd','user_69202',18,'不愿透露','研究工作者','不愿透露','西安','中国','台式设备',89,78,99,98,25,62,89,85,97,98),(91,'tcn0liFcpcH645oN','user_76733',5,'不愿透露','研究工作者','不愿透露','西安','中国','平板电脑',96,16,91,1,100,46,48,28,68,81),(92,'M56KeigYoFskxwIz','user_19646',20,'男','服务工作者','初中','长沙','中国','笔记本电脑',88,20,88,74,75,83,84,88,83,93),(93,'yLPnJPUDdQC5MEJd','user_11725',57,'男','服务工作者','初中','天津','中国','平板电脑',19,50,49,29,92,13,48,70,84,83),(94,'kV67DqnvLx43gJSU','user_72473',31,'男','不愿透露','不愿透露','长沙','中国','台式设备',82,38,19,95,9,87,93,77,98,75),(95,'uU3n1AyigEokE3UO','user_92303',23,'不愿透露','蓝领','初中','宁波','中国','其他设备',84,58,83,32,35,1,30,55,30,8),(96,'qZbnD15Isxe5QK9v','user_43597',48,'不愿透露','学生','大学','无锡','中国','笔记本电脑',83,56,49,64,77,61,32,50,44,27),(97,'rNRcylPaW2Wg1O2c','user_77387',43,'女','服务工作者','不愿透露','沈阳','中国','台式设备',80,68,36,41,87,61,25,45,43,57),(98,'lOveMaT9mXRN7Jhp','user_32739',49,'男','学生','初中','广州','中国','其他设备',4,89,96,49,68,99,10,76,67,86),(99,'zKxMQEsHdQCjkoqo','user_62149',44,'女','工程师','初中','南京','中国','平板电脑',90,88,91,71,98,4,53,60,16,79),(100,'cQsJke8WWCgrwUpT','user_49979',19,'男','研究工作者','高中','苏州','中国','台式设备',91,78,82,71,70,68,54,96,85,76),(101,'2Po2m9qXd5hW6OJS','user_73128',23,'男','老师','大学','昆明','中国','其他设备',87,33,83,28,14,88,3,62,71,70),(102,'zJt5WqJ8PcPTZlbs','user_19577',58,'女','老师','高中','成都','中国','平板电脑',98,90,72,8,77,36,4,60,91,7),(103,'LCQKBk4GxpRa8yut','user_14688',44,'女','白领','大学','西安','中国','其他设备',60,93,88,21,84,95,42,90,92,60),(104,'LJKWJd8Ub4x6mWhN','user_12588',18,'男','蓝领','不愿透露','无锡','中国','笔记本电脑',99,38,97,14,65,79,37,84,68,63),(105,'8yiiFjZ2ecZDIDs4','user_32972',12,'女','服务工作者','高中','天津','中国','台式设备',63,62,100,69,74,84,2,84,60,1),(106,'TwYO3QJ4dBwD7wKA','user_96791',44,'男','研究工作者','不愿透露','苏州','中国','平板电脑',50,51,68,83,34,32,29,74,89,93),(107,'nM3YdYdhlg6mEkcZ','user_19872',9,'男','工程师','不愿透露','上海','中国','智能手机',87,68,94,20,83,76,80,60,77,90),(108,'LxcjSAM7xNkIIm9t','user_15928',65,'女','不愿透露','不愿透露','北京','中国','其他设备',100,85,0,67,98,43,24,0,91,86),(109,'66XBeRXMChN5aui1','user_31241',40,'女','学生','不愿透露','无锡','中国','台式设备',94,75,31,90,69,20,8,0,91,98),(110,'qtu6r7D5usB2bDGa','user_28773',41,'男','白领','不愿透露','成都','中国','其他设备',75,87,96,79,39,1,13,96,82,64),(111,'rcLAHBq7574SDp9H','user_91152',26,'男','不愿透露','大学','宁波','中国','台式设备',95,34,46,100,48,61,96,100,82,75),(112,'g1AqXiR7xpET252C','user_39060',46,'不愿透露','白领','初中','成都','中国','台式设备',2,67,97,3,2,65,96,98,8,52),(113,'9lsJV8OL6tGyVzIB','user_47475',31,'男','不愿透露','不愿透露','南京','中国','台式设备',62,43,59,82,5,16,40,100,63,70),(114,'xUjBAZrWsba1KKf0','user_15103',27,'不愿透露','学生','不愿透露','武汉','中国','其他设备',15,85,47,86,28,78,64,86,92,87),(115,'ECp2pvHlb7ZyzPf1','user_45498',32,'女','服务工作者','初中','青岛','中国','台式设备',75,76,60,92,89,8,100,37,85,91),(116,'H4KlCtJljCu8MXx2','user_13466',24,'男','服务工作者','高中','长沙','中国','笔记本电脑',98,21,87,44,49,15,59,95,85,93),(117,'c1zyFTaMZCyvDCMF','user_50214',25,'男','研究工作者','高中','长沙','中国','智能手机',93,21,84,87,59,79,89,95,80,96),(118,'tHjG2Nm36apWcs6a','user_41961',23,'不愿透露','白领','高中','无锡','中国','其他设备',98,65,93,44,87,10,27,60,35,70),(119,'MwbZcsmOquhtYzpP','user_18847',43,'男','服务工作者','高中','长沙','中国','平板电脑',33,83,59,17,15,56,92,89,92,64),(120,'ZLQnF0URd8e3Jcpl','user_20369',2,'不愿透露','老师','不愿透露','杭州','中国','台式设备',99,38,80,46,90,100,39,74,77,98),(121,'e85847d11bd016b421b1e85c2accb44f','Tourist_1736326962952',25,'不愿透露','不愿透露','不愿透露','不愿透露','不愿透露','不愿透露',1,1,1,1,1,3,1,1,1,1);
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

-- Dump completed on 2025-01-08 17:28:45
