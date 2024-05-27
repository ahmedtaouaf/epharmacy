-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           10.4.28-MariaDB - mariadb.org binary distribution
-- SE du serveur:                Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour epharmacy
CREATE DATABASE IF NOT EXISTS `epharmacy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `epharmacy`;

-- Listage de la structure de la table epharmacy. hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table epharmacy.hibernate_sequence : ~1 rows (environ)
REPLACE INTO `hibernate_sequence` (`next_val`) VALUES
	(1);

-- Listage de la structure de la table epharmacy. login
CREATE TABLE IF NOT EXISTS `login` (
  `id` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3svxcq6q51yfdg253l6x3dget` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table epharmacy.login : ~2 rows (environ)
REPLACE INTO `login` (`id`, `password`, `role`, `username`) VALUES
	(1, '$2y$10$zBTkPWPu2zjYzlE9/oA5oO0WU1q.rsJvYw3LVG2jAtzSTEgGOSvIi', 'USER', 'client'),
	(2, '$2y$10$eSqNyMHq89VIJGNvOSbSI.85iFRDXy/SzSy9sq48tJuFZIbniuEya', 'ADMIN', 'admin');

-- Listage de la structure de la table epharmacy. medicament
CREATE TABLE IF NOT EXISTS `medicament` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `image_file_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table epharmacy.medicament : ~17 rows (environ)
REPLACE INTO `medicament` (`id`, `description`, `image_file_name`, `name`, `price`) VALUES
	(1, 'Acide Zolédronique.png', 'Acide Zolédronique.png', 'Acide Zolédronique', 150),
	(2, 'aclasta.png', 'aclasta.png', 'Aclasta', 24),
	(3, 'ASPEGIC.png', 'ASPEGIC.png', 'ASPEGIC', 20),
	(4, 'doliprane.png', 'doliprane.png', 'Doliprane', 20.5),
	(5, 'Gliclazide80mg.png', 'Gliclazide80mg.png', 'Gliclazide 80mg', 49.5),
	(6, 'Lenangio5mg.png', 'Lenangio5mg.png', 'Lenangio 5mg', 75),
	(7, 'lifongid250.png', 'lifongid250.png', 'Lifongid 250mg', 65),
	(8, 'locatop01.png', 'locatop01.png', 'Locatop', 15.6),
	(9, 'Lyrica50mg.png', 'Lyrica50mg.png', 'Lyrica 50mg', 70),
	(10, 'midal-e.png', 'midal-e.png', 'Midal-e', 15.2),
	(11, 'product_03.png', 'product_03.png', 'Product 3mg', 22.5),
	(12, 'sertraline.png', 'sertraline.png', 'Sertraline', 25),
	(13, 'Surgam.png', 'Surgam.png', 'Surgam 500mg', 23.45),
	(14, 'zanidip90.png', 'zanidip90.png', 'Zanidip 90mg', 24),
	(15, 'Zelax10mg.png', 'Zelax10mg.png', 'Zelax10mg', 44.5),
	(16, 'zinaskin45mg.png', 'zinaskin45mg.png', 'zinaskin 45mg', 35.6),
	(17, 'Zoloft.png', 'Zoloft.png', 'Zoloft', 90);

-- Listage de la structure de la table epharmacy. pharmacie
CREATE TABLE IF NOT EXISTS `pharmacie` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fix` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table epharmacy.pharmacie : ~4 rows (environ)
REPLACE INTO `pharmacie` (`id`, `adresse`, `email`, `fix`, `latitude`, `longitude`, `nom`, `telephone`) VALUES
	(1, '21 Av. Allal Ben Abdellah, Rabat 10000', 'normale@gmail.com', '0505050505', '34.02001573324233', '-6.833424059236956', 'Pharmacie Normale', '0606060606'),
	(2, '248V+M64، kebibate,, Rue colonnel cliquot, Rabat', 'aya@gmail.com', '0505050505', '34.017233443011015', '-6.853540686206301', 'Pharmacie Aya', '0606060606'),
	(3, 'Angle Bd Al Alaouiyine / 8 rue al Mouahidine, Av. Moulay Hassan, Rabat 10000', 'mausolee@gmail.com', '0505050505', '34.02442856162214', '-6.825146755684144', 'Pharmacie du Mausolée', '0606060606'),
	(4, 'Pharmacie Les Almohades', 'Almohades@gmail.com', '0537707001', '34.021175336921644', '-6.824356452028303', 'Pharmacie Les Almohades\r\n', '0606060606');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
