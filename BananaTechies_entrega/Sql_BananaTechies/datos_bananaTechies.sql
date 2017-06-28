-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bananatechies
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Dumping data for table `progreso`
--

LOCK TABLES `progreso` WRITE;
/*!40000 ALTER TABLE `progreso` DISABLE KEYS */;
INSERT INTO `progreso` VALUES (1,'pendiente'),(2,'en curso'),(3,'terminado');
/*!40000 ALTER TABLE `progreso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `proyecto`
--

LOCK TABLES `proyecto` WRITE;
/*!40000 ALTER TABLE `proyecto` DISABLE KEYS */;
INSERT INTO `proyecto` VALUES (1,'proyecto1',1,'2017-06-01 00:00:00','2018-06-10 00:00:00','Expetenda tincidunt in sed ex partem placerat sea porro commodo ex eam. ','Lorem ipsum dolor sit amet, consectetuer adipiscing elit sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.',0,1),(2,'proyecto2',2,'2017-06-02 00:00:00','2018-06-11 00:00:00','Expetenda tincidunt in sed ex partem placerat sea porro commodo ex eam.','Lorem ipsum dolor sit amet, consectetuer adipiscing elitsed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.',1,2),(3,'proyecto3',3,'2017-06-03 00:00:00','2018-06-12 00:00:00','Expetenda tincidunt in sed ex partem placerat sea porro commodo ex eam.','Lorem ipsum dolor sit amet, consectetuer adipiscing elit sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.',0,3),(4,'proyecto4',1,'2017-06-04 00:00:00','2018-06-10 00:00:00','Expetenda tincidunt in sed ex partem placerat sea porro commodo ex eam.','Lorem ipsum dolor sit amet, consectetuer adipiscing elit sed diam .',1,2),(5,'proyecto5',2,'2017-06-05 00:00:00','2018-06-11 00:00:00','Expetenda tincidunt in sed ex partem placerat sea porro commodo ex eam.','Lorem ipsum dolor sit amet, consectetuer adipiscing elit',0,1),(6,'proyecto6',3,'2017-06-06 00:00:00','2018-06-12 00:00:00','Expetenda tincidunt in sed ex partem placerat sea porro commodo ex eam.','Lorem ipsum dolor sit amet, consectetuer adipiscing elit',1,3),(7,'proyecto7',1,'2017-06-07 00:00:00','2018-06-10 00:00:00','Expetenda tincidunt in sed ex partem placerat sea porro commodo ex eam.','Lorem ipsum dolor sit amet, consectetuer adipiscing elit',0,2),(8,'proyecto8',2,'2017-06-08 00:00:00','2018-06-11 00:00:00','Expetenda tincidunt in sed ex partem placerat sea porro commodo ex eam.','Lorem ipsum dolor sit amet, consectetuer adipiscing elit',1,1),(9,'proyecto9',3,'2017-06-09 00:00:00','2018-06-12 00:00:00','Expetenda tincidunt in sed ex partem placerat se porro commodo ex eam.','Lorem ipsum dolor sit amet, consectetuer adipiscing elit',0,1);
/*!40000 ALTER TABLE `proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tarea`
--

LOCK TABLES `tarea` WRITE;
/*!40000 ALTER TABLE `tarea` DISABLE KEYS */;
INSERT INTO `tarea` VALUES (1,'tarea1','2017-06-01 00:00:00','2018-06-10 00:00:00',0,1,1,1),(2,'tarea2','2017-06-02 00:00:00','2018-06-11 00:00:00',1,2,2,2),(3,'tarea3','2017-06-03 00:00:00','2018-06-12 00:00:00',0,3,3,3),(4,'tarea4','2017-06-04 00:00:00','2018-06-13 00:00:00',1,1,1,4),(5,'tarea5','2017-06-05 00:00:00','2018-06-14 00:00:00',0,2,2,5),(6,'tarea6','2017-06-06 00:00:00','2018-06-15 00:00:00',1,3,3,6),(7,'tarea7','2017-06-07 00:00:00','2018-06-16 00:00:00',0,1,1,7),(8,'tarea8','2017-06-08 00:00:00','2018-06-17 00:00:00',1,2,2,8),(9,'tarea9','2017-06-09 00:00:00','2018-06-18 00:00:00',0,3,3,9);
/*!40000 ALTER TABLE `tarea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Juana','Juanason','juana@e.es','juanason_1','null','null'),(2,'Luisa','Luisason','luisa@e.es','luisason_2','null','null'),(3,'Marco','Marcoson','marco@e.es','marcoson_5','null','null');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-27 17:36:37
