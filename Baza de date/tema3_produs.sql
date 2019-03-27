-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tema3
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `produs`
--

DROP TABLE IF EXISTS `produs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produs` (
  `id_produs` int(11) NOT NULL AUTO_INCREMENT,
  `nume` varchar(45) NOT NULL,
  `categorie` varchar(45) NOT NULL,
  `cantitate` int(11) NOT NULL,
  `pret` int(11) NOT NULL,
  PRIMARY KEY (`id_produs`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produs`
--

LOCK TABLES `produs` WRITE;
/*!40000 ALTER TABLE `produs` DISABLE KEYS */;
INSERT INTO `produs` VALUES (1,'Aspirator','Electrocasnice',50,200),(2,'Fier de calcat','Electrocasnice',43,150),(3,'Espressor','Electrocasnice',59,900),(4,'Storcator de fructe','Electrocasnice',65,70),(5,'Mixer','Electrocasnice',40,130),(6,'Frigider','Electrocasnice',67,1300),(7,'Fierbator','Electrocasnice',35,40),(8,'Telefon Samsung','Telefoane',70,3500),(9,'Telefon Huawei','Telefoane',59,1500),(10,'Telefon iPhone','Telefoane',93,5000),(11,'Bratara fitness','Gadgeturi',82,100),(12,'Epilator Braun','Ingrijire personala',64,550),(13,'Aparat de ras','Ingrijire personala',51,300),(14,'Ondulator','Ingrijire personala',35,250),(15,'Uscator de par','Ingrijire personala',40,70);
/*!40000 ALTER TABLE `produs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-19  2:03:00
