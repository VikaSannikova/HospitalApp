-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hospital
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `cabinets`
--

DROP TABLE IF EXISTS `cabinets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cabinets` (
  `idcab` int NOT NULL AUTO_INCREMENT,
  `capacity` int NOT NULL,
  PRIMARY KEY (`idcab`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cabinets`
--

LOCK TABLES `cabinets` WRITE;
/*!40000 ALTER TABLE `cabinets` DISABLE KEYS */;
INSERT INTO `cabinets` VALUES (1,2),(2,1),(3,2),(4,3),(5,1),(6,2),(7,2),(8,1);
/*!40000 ALTER TABLE `cabinets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnoses`
--

DROP TABLE IF EXISTS `diagnoses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diagnoses` (
  `iddiag` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL,
  `doctor_id` int NOT NULL,
  `diag_name` varchar(100) DEFAULT NULL,
  `date_of_request` date NOT NULL,
  PRIMARY KEY (`iddiag`),
  KEY `pat_idx` (`patient_id`),
  KEY `doc_idx` (`doctor_id`),
  CONSTRAINT `doc_to_diag` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`iddoc`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pat_to_diag` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`idpat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnoses`
--

LOCK TABLES `diagnoses` WRITE;
/*!40000 ALTER TABLE `diagnoses` DISABLE KEYS */;
INSERT INTO `diagnoses` VALUES (1,1,1,'ангина','2020-10-21'),(2,3,8,'волчанка','2020-10-21'),(3,9,8,'вши','2020-10-22'),(4,2,2,'вши','2020-10-22'),(5,4,3,'золотуха','2020-10-24'),(6,6,1,'ковид19','2020-10-25'),(7,8,4,'сифилис','2020-10-26'),(8,1,8,'отит','2020-10-27'),(9,2,6,'чесотка','2020-10-28'),(10,1,2,'краснуха','2020-10-26'),(11,1,2,'ОРВИ','2020-10-26'),(12,1,10,NULL,'2020-10-26'),(13,4,6,'вши','2020-10-31'),(14,20,3,NULL,'2020-11-08'),(15,20,10,NULL,'2020-11-10'),(17,1,9,NULL,'2020-11-08'),(18,1,6,'боль','2020-11-05'),(19,1,10,NULL,'2020-11-06'),(20,1,3,NULL,'2020-11-05'),(21,1,9,NULL,'2020-12-06'),(22,20,5,NULL,'2020-10-29');
/*!40000 ALTER TABLE `diagnoses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctors` (
  `iddoc` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `speciality` varchar(100) NOT NULL,
  `cabinet` int NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  PRIMARY KEY (`iddoc`),
  KEY `cab_to_docs_idx` (`cabinet`),
  CONSTRAINT `cab_to_docs` FOREIGN KEY (`cabinet`) REFERENCES `cabinets` (`idcab`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (1,'Айнур','Болитов','doc1','doc1','Терапевт',1,'1234'),(2,'Иван','Иванов','doc2','doc2','Остеопат',1,'123457'),(3,'Шарл','Шарлов','doc3','doc3','Стоматолог',2,'123458'),(4,'Барак','Обама','doc4','doc4','Венеролог',3,'123459'),(5,'Дональд','Трамп','doc5','doc5','Проктолог',3,'123465'),(6,'Ангела','Меркелева','doc6','doc6','Окулист',4,'123467'),(7,'Николя','Саркази','doc7','doc7','Психолог',5,'123468'),(8,'Николай','Коши','doc8','doc8','Дерматолог',6,'123469'),(9,'Виктор','Гюго','doc9','doc9','Терапевт',6,'123479'),(10,'Павел','Дуров','doc10','doc10','Психиатр',7,'111222');
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `idpat` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `sex` varchar(45) NOT NULL,
  `dob` date DEFAULT NULL,
  `phone_number` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `doc_id` int DEFAULT NULL,
  PRIMARY KEY (`idpat`),
  KEY `doc_idx` (`doc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,'Илья','Ильининов','1','1','m','1111-10-10','1222','12233423',10),(2,'Иван','Иванинов','2','2','m','1111-10-10','222','12',9),(3,'Петруша','Петров','3','3','m','1111-10-10','3','12@yandex.ru',8),(4,'Степан','Степнов','4','4','m','1111-10-10','4','12',7),(5,'Волк','Волков','5','5','m','1111-10-10','5','12',1),(6,'Заяц','Косой','6','6','m','1111-10-10','6','12',1),(7,'Цветок','Розовый','7','7','f','1111-10-10','7','12',4),(8,'Дарья','Демидова','8','8','f','1111-10-10','8','12',3),(9,'Елена','Малышева','9','9','f','1111-10-10','9','12',2),(10,'Александр','Мясников','10','10','m','1111-10-10','10','12',1),(11,'Привет','Приветов','11','1234','m','2222-12-12','+7','@',NULL),(12,'Пока','Покаев','12','1234','m','2000-02-10','+7222','123',NULL),(14,'123','123','13','1234','f','2010-10-12','+7','2222',NULL),(15,'Иван','Иванов','14','12345','m','1999-04-30','+7','1234',NULL),(18,'Виталий','Милонов','15','13','м','1970-06-20','+78003553535','3535',NULL),(19,'Акулина','Чебурашкина','16','33','ж','2008-08-08','+7999','1234',NULL),(20,'Хюррем','Султан','17','15','ж','1000-01-01','+7','1234',NULL);
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipes`
--

DROP TABLE IF EXISTS `recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipes` (
  `idrec` int NOT NULL AUTO_INCREMENT,
  `diagnos_id` int NOT NULL DEFAULT '1',
  `recomend` varchar(1000) DEFAULT NULL,
  `total_cost` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idrec`),
  KEY `pat_idx` (`diagnos_id`),
  CONSTRAINT `pat_to_rec` FOREIGN KEY (`diagnos_id`) REFERENCES `diagnoses` (`iddiag`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipes`
--

LOCK TABLES `recipes` WRITE;
/*!40000 ALTER TABLE `recipes` DISABLE KEYS */;
INSERT INTO `recipes` VALUES (1,3,'мыть голову','123'),(2,6,'не паниковать','1032'),(3,5,'ловить блох','555'),(4,1,'лежать на животе','2209'),(5,2,'ждать смерти','1223'),(6,7,'использовать презерватитвы в будущем','2347'),(7,4,'не чесаться','1000'),(8,8,'делать примочки','124'),(13,9,'не чесаться','1000'),(19,10,'не чесаться','1000'),(20,13,'мазаться мазью','88'),(21,18,'не жаловаться','900'),(22,11,'не кашлять на окружающих','10');
/*!40000 ALTER TABLE `recipes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-17  0:45:35
