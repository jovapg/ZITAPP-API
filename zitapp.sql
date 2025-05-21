-- MySQL dump 10.13  Distrib 9.2.0, for Linux (x86_64)
--
-- Host: localhost    Database: zitapp
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_cliente` int NOT NULL,
  `id_negocio` int NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `estado` varchar(20) DEFAULT 'pendiente',
  PRIMARY KEY (`id`),
  KEY `idx_appointments_client` (`id_cliente`),
  KEY `idx_appointments_business` (`id_negocio`),
  CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `users` (`id`),
  CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`id_negocio`) REFERENCES `businesses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (2,2,2,'2025-05-11','09:15:00','CONFIRMADA'),(3,6,3,'2025-05-12','18:00:00','PENDIENTE'),(4,7,4,'2025-05-13','14:30:00','CONFIRMADA'),(5,9,5,'2025-05-14','12:00:00','PENDIENTE'),(7,2,5,'2025-05-16','16:30:00','CONFIRMADA'),(8,6,2,'2025-05-17','11:45:00','PENDIENTE'),(9,7,1,'2025-05-18','15:00:00','CONFIRMADA'),(10,9,4,'2025-05-19','09:30:00','CONFIRMADA'),(12,2,3,'2025-05-21','08:00:00','CONFIRMADA'),(13,6,4,'2025-05-22','13:30:00','CANCELADA'),(14,7,2,'2025-05-23','10:45:00','PENDIENTE'),(15,9,1,'2025-05-24','16:00:00','CONFIRMADA');
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `availability`
--

DROP TABLE IF EXISTS `availability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `availability` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_negocio` int NOT NULL,
  `día` varchar(255) NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_availability_business` (`id_negocio`),
  CONSTRAINT `availability_ibfk_1` FOREIGN KEY (`id_negocio`) REFERENCES `businesses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `availability`
--

LOCK TABLES `availability` WRITE;
/*!40000 ALTER TABLE `availability` DISABLE KEYS */;
INSERT INTO `availability` VALUES (1,1,'Lunes','09:00:00','18:00:00'),(2,1,'Martes','09:00:00','18:00:00'),(3,1,'Miércoles','09:00:00','18:00:00'),(4,1,'Jueves','09:00:00','18:00:00'),(5,1,'Viernes','09:00:00','17:00:00'),(6,2,'Lunes','08:00:00','16:00:00'),(7,2,'Martes','08:00:00','16:00:00'),(8,2,'Miércoles','08:00:00','16:00:00'),(9,2,'Jueves','08:00:00','16:00:00'),(10,2,'Viernes','08:00:00','14:00:00'),(11,3,'Lunes','06:00:00','22:00:00'),(12,3,'Martes','06:00:00','22:00:00'),(13,3,'Miércoles','06:00:00','22:00:00'),(14,3,'Jueves','06:00:00','22:00:00'),(15,3,'Viernes','06:00:00','22:00:00'),(16,3,'Sábado','08:00:00','20:00:00'),(17,3,'Domingo','08:00:00','14:00:00'),(18,4,'Lunes','08:00:00','18:00:00'),(19,4,'Martes','08:00:00','18:00:00'),(20,4,'Miércoles','08:00:00','18:00:00'),(21,4,'Jueves','08:00:00','18:00:00'),(22,4,'Viernes','08:00:00','18:00:00'),(23,4,'Sábado','09:00:00','13:00:00'),(24,5,'Martes','10:00:00','20:00:00'),(25,5,'Miércoles','10:00:00','20:00:00'),(26,5,'Jueves','10:00:00','20:00:00'),(27,5,'Viernes','10:00:00','20:00:00'),(28,5,'Sábado','10:00:00','16:00:00'),(29,1,'Lunes','09:00:00','18:00:00');
/*!40000 ALTER TABLE `availability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business`
--

DROP TABLE IF EXISTS `business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business`
--

LOCK TABLES `business` WRITE;
/*!40000 ALTER TABLE `business` DISABLE KEYS */;
/*!40000 ALTER TABLE `business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `businesses`
--

DROP TABLE IF EXISTS `businesses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `businesses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `categoría` varchar(50) DEFAULT NULL,
  `descripción` text,
  `dirección` varchar(200) DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `id_usuario` int NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_business_user` (`id_usuario`),
  CONSTRAINT `businesses_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `businesses`
--

LOCK TABLES `businesses` WRITE;
/*!40000 ALTER TABLE `businesses` DISABLE KEYS */;
INSERT INTO `businesses` VALUES (1,'Peluquería Carlitos','Belleza','Peluquería profesional para caballeros y damas','Calle Principal 123','https://example.com/images/peluqueria.jpg',3,NULL,NULL,NULL),(2,'Clínica Dental Ana','Salud','Servicios dentales de alta calidad','Avenida Central 456','https://example.com/images/dental.jpg',4,NULL,NULL,NULL),(3,'Gimnasio Roberto','Fitness','Equipamiento moderno y entrenadores personales','Plaza Mayor 789','https://example.com/images/gym.jpg',5,NULL,NULL,NULL),(4,'Taller Mecánico Laura','Automotriz','Reparación y mantenimiento de vehículos','Carretera Norte 101','https://example.com/images/taller.jpg',8,NULL,NULL,NULL),(5,'Centro de Masajes Carmen','Bienestar','Masajes terapéuticos y relajantes','Calle Secundaria 202','https://example.com/images/masajes.jpg',10,NULL,NULL,NULL),(6,'Peluquería Carlos',NULL,NULL,NULL,'https://example.com/images/peluqueria.jpg',3,NULL,NULL,NULL);
/*!40000 ALTER TABLE `businesses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `tipo` varchar(20) NOT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'María López','maria@example.com','CLIENTE','password123',NULL),(3,'Carlos Rodríguez','carlos@example.com','NEGOCIO','password123',NULL),(4,'Ana Martínez','ana@example.com','NEGOCIO','password123',NULL),(5,'Roberto Sánchez','roberto@example.com','NEGOCIO','password123',NULL),(6,'Sofía Fernández','sofia@example.com','CLIENTE','password123',NULL),(7,'Miguel Torres','miguel@example.com','CLIENTE','password123',NULL),(8,'Laura Ramírez','laura@example.com','NEGOCIO','password123',NULL),(9,'Pedro Gómez','pedro@example.com','CLIENTE','password123',NULL),(10,'Carmen Díaz','carmen@example.com','NEGOCIO','password123',NULL),(11,'leo','leo@leo.com','NEGOCIO','12345','312');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-20 14:41:11
