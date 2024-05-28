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
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Listage des données de la table epharmacy.pharmacie : ~129 rows (environ)
REPLACE INTO `pharmacie` (`id`, `adresse`, `email`, `fix`, `latitude`, `longitude`, `nom`, `telephone`) VALUES
	(1, '21 Av. Allal Ben Abdellah, Rabat 10000', 'normale@gmail.com', '0505050505', '34.02001573324233', '-6.833424059236956', 'Pharmacie Normale', '0606060606'),
	(2, '248V+M64، kebibate,, Rue colonnel cliquot, Rabat', 'aya@gmail.com', '0505050505', '34.017233443011015', '-6.853540686206301', 'Pharmacie Aya', '0606060606'),
	(3, 'Angle Bd Al Alaouiyine / 8 rue al Mouahidine, Av. Moulay Hassan, Rabat 10000', 'mausolee@gmail.com', '0505050505', '34.02442856162214', '-6.825146755684144', 'Pharmacie du Mausolée', '0606060606'),
	(4, 'Pharmacie Les Almohades', 'Almohades@gmail.com', '0537707001', '34.021175336921644', '-6.824356452028303', 'Pharmacie Les Almohades\r\n', '0606060606'),
	(6, 'Avenue Chellah شارع شالة', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0217171', '-6.8312237', 'Pharmacie Chellah صيدلية شالة', '06 06 06 06 06'),
	(7, 'Rue Al Marj زنقة المرج', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0195429', '-6.8310709', 'Pharmacie du Théatre صيدلية المسرح', '06 06 06 06 06'),
	(8, 'Place de l\'Unité Africaine ساحة الوحدة الإفريقية', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0178583', '-6.8290641', 'Pharmacie de la Tour Hassan صيدلية صومعة حسان', '06 06 06 06 06'),
	(9, 'Avenue Abou Faris Al Marini شارع أبو فارس المريني', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0163793', '-6.8319284', 'La Grande Pharmacie الصيدلية الكبرى', '06 06 06 06 06'),
	(10, 'Rue Karachi زنقة كالكوتا', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0273181', '-6.8455781', 'Pharmacie Gharbia صيدلية الغربية', '06 06 06 06 06'),
	(11, 'Rue Ibn Hajar', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0085482', '-6.8511786', 'Pharmacie Ibn Hajar صيدلية إبن حجر', '06 06 06 06 06'),
	(12, 'Avenue Al Marsa شارع المرسى', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.029522', '-6.8358024', 'Pharmacie des Oudayas صيدلية الأوداية', '06 06 06 06 06'),
	(13, 'Avenue Pasteur شارع باستور', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0125082', '-6.8425147', 'Pharmacie les Arcades صيدلية أركاد', '06 06 06 06 06'),
	(14, 'Rue Moulay Abdalaziz زنقة مولاي عبد العزيز', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0141694', '-6.8313865', 'Pharmacie de la Capitale صيدلية العاصمة', '06 06 06 06 06'),
	(15, 'Rue Balafrej زنقة بلافريج', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0107326', '-6.8564975', 'Pharmacie Al Faraj صيدلية الفرج', '06 06 06 06 06'),
	(16, 'Avenue Chellah شارع شالة', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0154509', '-6.8271485', 'Pharmacie Ben Omar صيدلية بن عمر', '06 06 06 06 06'),
	(17, 'Avenue Allal Ben Abdellah شارع علال بن عبد الله', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0215779', '-6.8363628', 'Pharmacie Guedira صيدلية عبد الحكيم اكديرة', '06 06 06 06 06'),
	(18, 'Avenue Mohamed V شارع محمد الخامس', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0203254', '-6.8372771', 'Pharmacie la Renaissance صيدلية النهضة', '06 06 06 06 06'),
	(19, 'Avenue Oqba', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0101104', '-6.8436866', 'Pharmacie l\'Oasis صيدلية الواحة', '06 06 06 06 06'),
	(20, 'Avenue Oqba', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0082884', '-6.8445817', 'Pharmacie Alam صيدلية علام', '06 06 06 06 06'),
	(21, 'Rue Casablanca زنقة الدار البيضاء', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0246476', '-6.829974', 'Pharmacie Dounia Soleil صيدلية دنيا الشمس', '06 06 06 06 06'),
	(22, 'Avenue Al Mouqaouama شارع المقاومة', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0229167', '-6.8462022', 'Pharmacie de la Résistance صيدلية المقاومة', '06 06 06 06 06'),
	(23, 'Rue Zagora Hassan', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0206772', '-6.8243041', 'Pharmacie et Laboratoire Les Almohades صيدلية و مختبر الموحدين', '06 06 06 06 06'),
	(24, 'Avenue d\'Alger شارع الجزائر', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0174608', '-6.8258189', 'Pharmacie l\'Officine Moderne صيدلية المستوصف الحديث', '06 06 06 06 06'),
	(25, 'Rue Ouazzane زنقة وزان', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0210339', '-6.8213996', 'Pharmacie des Cliniques صيدلية المصحات', '06 06 06 06 06'),
	(26, 'Avenue Patrice Lumumba شارع باتريس لومومبا', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0140629', '-6.8290191', 'Pharmacie de la Marne صيدلية مارل', '06 06 06 06 06'),
	(27, 'Avenue Al Mouqaouama شارع المقاومة', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0170042', '-6.8531909', 'Pharmacie Ibn El Haytam صيدلية ابن الهيثم', '06 06 06 06 06'),
	(28, 'Avenue El Hachimi El Mestari زنقة الهاشمي المستاري', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0166735', '-6.8567883', 'Pharmacie Aya صيدلية اًية', '06 06 06 06 06'),
	(29, 'Place des Alliés ساحة الحلفاء', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0221318', '-6.8497586', 'Pharmacie Maghrebine الصيدلية المغاربية', '06 06 06 06 06'),
	(30, 'Avenue Laalou شارع لعلو', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0286307', '-6.8387859', 'Pharmacie Cherqi صيدلية الشرقي', '06 06 06 06 06'),
	(31, 'Rue Prince Moulay Abdellah', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0174629', '-6.8350178', 'Pharmacie Zahra صيدلية زهرة', '06 06 06 06 06'),
	(32, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0356505', '-6.823897', 'La grande Pharmacie de Salé', '06 06 06 06 06'),
	(33, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0366326', '-6.8239893', 'Pharmacie de l\'Union', '06 06 06 06 06'),
	(34, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0357828', '-6.8209248', 'Pharmacie Ryad Sale', '06 06 06 06 06'),
	(35, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0414184', '-6.8281646', 'Pharmacie Fennich', '06 06 06 06 06'),
	(36, 'Avenue Maghreb Arabe شارع المغرب العربي', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0195343', '-6.8464862', 'Pharmacie Al Maghrib Al Arabi صيدلية المغرب العربي', '06 06 06 06 06'),
	(37, 'Place de Russie ساحة روسيا', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0195444', '-6.8499448', 'Pharmacie Fellat صيدلية فلات', '06 06 06 06 06'),
	(38, 'Rue Al Mouahidine زنقة الموحدين', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0237902', '-6.8253315', 'Pharmacie du Mausolée صيدلية الضريح', '06 06 06 06 06'),
	(39, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0450322', '-6.7980612', 'Pharmacie El Ismaili صيدلية الإسماعلي', '06 06 06 06 06'),
	(40, 'Boulevard d\'Egypte', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0251367', '-6.8416741', 'Pharmacie Marassa صيدلية المرسى', '06 06 06 06 06'),
	(41, 'Avenue Hassan II', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0127439', '-6.8488091', 'Pharmacie Al Amana صيدلية الأمانة', '06 06 06 06 06'),
	(42, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0166694', '-6.8465681', 'Pharmacie Diour Jamaâ صيدلية ديور الجامع', '06 06 06 06 06'),
	(43, 'Avenue Tonkin شارع طونكان', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0162248', '-6.8497592', 'Pharmacie Belmahi صيدلية بن الماحي', '06 06 06 06 06'),
	(44, 'Avenue Hassan II', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0140137', '-6.8480362', 'Pharmacie du Minaret صيدلية المنارة', '06 06 06 06 06'),
	(45, 'Avenue Hassan II', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0173385', '-6.8447823', 'Pharmacie El Amine صيدلية الأمين', '06 06 06 06 06'),
	(46, 'Rue Addis-Abeba زنقة أديس أبابا', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0258963', '-6.8485412', 'Pharmacie La Corniche صيدلية الكرنيش', '06 06 06 06 06'),
	(47, 'Avenue Abdelkrim Al Khattabi شارع عبد الكريم الخطابي', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0251959', '-6.8464955', 'Pharmacie de l\'Océan صيدلية المحيط', '06 06 06 06 06'),
	(48, 'Avenue Mohamed V شارع محمد الخامس', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.011256', '-6.8290584', 'Pharmacie Assounah صيدلية السنة', '06 06 06 06 06'),
	(49, 'Avenue Mohamed El Fassi شارع محمد الفاسي', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0132496', '-6.822307', 'Pharmacie les Colonnes صيدلية ليكولون', '06 06 06 06 06'),
	(50, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0409161', '-6.8126875', 'Pharmacie Ziniber', '06 06 06 06 06'),
	(51, 'Avenue Sidi Mohammed Ben Abdellah', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0105454', '-6.8595631', 'Pharmacie Du Croissant صيدلية الهلال', '06 06 06 06 06'),
	(52, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0428986', '-6.819845', 'Pharmacie Bab Sebta', '06 06 06 06 06'),
	(53, 'Avenue Al Massira Al Khadra', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0281209', '-6.8008932', 'Dar Dmana', '06 06 06 06 06'),
	(54, 'Avenue du Haut-Atlas', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0428108', '-6.7909214', 'Pharmacie Et Laboratoire al Hayat', '06 06 06 06 06'),
	(55, 'Avenue Hassan II شارع الحسن الثاني', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0198347', '-6.8425075', 'Pharmacie des Orangers صيدلية الليمون', '06 06 06 06 06'),
	(56, 'Rue Tchad زنقة نشاد', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0202562', '-6.8433934', 'Pharmacie Bon Secours صيدلية العناية', '06 06 06 06 06'),
	(57, 'Avenue Ibn Toumert شارع ابن تومرت', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0161602', '-6.8391178', 'Pharmacie Ibn Toumerte صيدلية ابن تومرت', '06 06 06 06 06'),
	(58, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0184271', '-6.8457835', 'Pharmacie de Rabat', '06 06 06 06 06'),
	(59, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0438361', '-6.804304', 'صيدلية الرياض', '06 06 06 06 06'),
	(60, 'Avenue Lalla Amina', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0410362', '-6.8101288', 'Pharmacie Ibtissam', '06 06 06 06 06'),
	(61, 'Avenue Achouhada', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0385712', '-6.7920811', 'Pharmacie Achouhadaa', '06 06 06 06 06'),
	(62, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0480173', '-6.7970992', 'Pharmacie El Fath', '06 06 06 06 06'),
	(63, 'Avenue Moulay Ismail شارع مولاي اسماعيل', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0209889', '-6.8267416', 'Pharmacie Centre Chèques Postaux صيدلية مركز الشيكات البريدية', '06 06 06 06 06'),
	(64, 'Avenue Chellah شارع شالة', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0217171', '-6.8312237', 'Pharmacie Chellah صيدلية شالة', '06 06 06 06 06'),
	(65, 'Rue Al Marj زنقة المرج', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0195429', '-6.8310709', 'Pharmacie du Théatre صيدلية المسرح', '06 06 06 06 06'),
	(66, 'MohammedV', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0489348', '-6.7994052', 'mohammed V', '06 06 06 06 06'),
	(67, 'Avenue Othman BIbn Affane', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0503377', '-6.8005667', 'Pharmacie Mamounia', '06 06 06 06 06'),
	(68, 'Avenue Al Marsa شارع المرسى', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.029522', '-6.8358024', 'Pharmacie des Oudayas صيدلية الأوداية', '06 06 06 06 06'),
	(69, 'Avenue Allal Ben Abdellah شارع علال بن عبد الله', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0215779', '-6.8363628', 'Pharmacie Guedira صيدلية عبد الحكيم اكديرة', '06 06 06 06 06'),
	(70, 'Avenue Mohamed V شارع محمد الخامس', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0203254', '-6.8372771', 'Pharmacie la Renaissance صيدلية النهضة', '06 06 06 06 06'),
	(71, 'Rue Casablanca زنقة الدار البيضاء', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0246476', '-6.829974', 'Pharmacie Dounia Soleil صيدلية دنيا الشمس', '06 06 06 06 06'),
	(72, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0527906', '-6.7849912', 'Pharmacie des Orangers صيدلية الليمون', '06 06 06 06 06'),
	(73, 'Rue Zagora Hassan', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0206772', '-6.8243041', 'Pharmacie et Laboratoire Les Almohades صيدلية و مختبر الموحدين', '06 06 06 06 06'),
	(74, 'Rue Ouazzane زنقة وزان', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0210339', '-6.8213996', 'Pharmacie des Cliniques صيدلية المصحات', '06 06 06 06 06'),
	(75, 'Avenue Laalou شارع لعلو', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0286307', '-6.8387859', 'Pharmacie Cherqi صيدلية الشرقي', '06 06 06 06 06'),
	(76, 'Avenue Ali Ben Abi Taleb شارع علي بن أبي طالب', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0575178', '-6.7982334', 'Pharmacie Hay Arrahma', '06 06 06 06 06'),
	(77, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0356505', '-6.823897', 'La grande Pharmacie de Salé', '06 06 06 06 06'),
	(78, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0366326', '-6.8239893', 'Pharmacie de l\'Union', '06 06 06 06 06'),
	(79, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0357828', '-6.8209248', 'Pharmacie Ryad Sale', '06 06 06 06 06'),
	(80, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0414184', '-6.8281646', 'Pharmacie Fennich', '06 06 06 06 06'),
	(81, 'Rue Al Mouahidine زنقة الموحدين', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0237902', '-6.8253315', 'Pharmacie du Mausolée صيدلية الضريح', '06 06 06 06 06'),
	(82, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0450322', '-6.7980612', 'Pharmacie El Ismaili صيدلية الإسماعلي', '06 06 06 06 06'),
	(83, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0610442', '-6.8099242', 'Pharmacie Charaf', '06 06 06 06 06'),
	(84, 'Boulevard Rahal Al Maskini', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0558772', '-6.8098055', 'Pharmacie Salam Eddine El Ayoubi', '06 06 06 06 06'),
	(85, 'Boulevard d\'Egypte', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0251367', '-6.8416741', 'Pharmacie Marassa صيدلية المرسى', '06 06 06 06 06'),
	(86, 'Avenue D', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0629524', '-6.7839141', 'Parapharmacie', '06 06 06 06 06'),
	(87, '5', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0594188', '-6.7847959', 'Pharmacie Al Ikhlas', '06 06 06 06 06'),
	(88, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0409161', '-6.8126875', 'Pharmacie Ziniber', '06 06 06 06 06'),
	(89, 'Avenue Mohammed V', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0552061', '-6.7934252', 'Pharmacie Hay Karima', '06 06 06 06 06'),
	(90, 'Boulevard Bocfeur Abd Karim Khatabi', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0607103', '-6.8133232', 'Pharmacie Semlali', '06 06 06 06 06'),
	(91, 'Zankat Bouchoak', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0538383', '-6.7917073', 'Ma Petite Pharmacie', '06 06 06 06 06'),
	(92, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0428986', '-6.819845', 'Pharmacie Bab Sebta', '06 06 06 06 06'),
	(93, 'Avenue Omar Ibn Laass', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0586393', '-6.7889178', 'Pharmacie du Quartier Industriel', '06 06 06 06 06'),
	(94, 'Avenue Nasser', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0502727', '-6.8226855', 'Pharmacie Sidi Moussa', '06 06 06 06 06'),
	(95, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0471208', '-6.8239568', 'Pharmacie Lahkim', '06 06 06 06 06'),
	(96, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0482115', '-6.8208495', 'Pharmacie al Osra', '06 06 06 06 06'),
	(97, 'Avenue Ibn Al Haytem', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.051685', '-6.7822069', 'Pharmacie Hammi', '06 06 06 06 06'),
	(98, 'Boulevard Oujada', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0530005', '-6.8157861', 'Cooper Pharma', '06 06 06 06 06'),
	(99, 'Rue Oued Roumane', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0518705', '-6.7787817', 'Pharmacie Az-zahra', '06 06 06 06 06'),
	(100, 'Avenue Ibn Al Haytem', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0569994', '-6.7774004', 'Pharmacie Annour', '06 06 06 06 06'),
	(101, 'Boulevard Oujada', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0530067', '-6.8157737', 'Pharmacie Nour Salé', '06 06 06 06 06'),
	(102, 'Boulevard Rahal Al Maskini', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0637245', '-6.8031389', 'Parapharmacie Raputons', '06 06 06 06 06'),
	(103, 'Rue Abed Allah Bnou Abbass', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0645711', '-6.8066458', 'Pharmacie Moulay Idriss', '06 06 06 06 06'),
	(104, 'Avenue de L\'Aviation', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0604448', '-6.767845', 'Pharmacie Anoual', '06 06 06 06 06'),
	(105, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0458079', '-6.8098189', 'Pharmacie L\'Opera', '06 06 06 06 06'),
	(106, 'Avenue 6 Novembre', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0479125', '-6.8079038', 'Pharmacie Rizk', '06 06 06 06 06'),
	(107, 'Avenue Al Massira Al Khadra', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0281209', '-6.8008932', 'Dar Dmana', '06 06 06 06 06'),
	(108, 'Avenue Salah Eddine el Ayoubi', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0474325', '-6.8129303', 'Pharmacie Essania', '06 06 06 06 06'),
	(109, 'Avenue du Haut-Atlas', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0428108', '-6.7909214', 'Pharmacie Et Laboratoire al Hayat', '06 06 06 06 06'),
	(110, 'Avenue Assalam', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0517494', '-6.7908946', 'Pharmacie Inbiat', '06 06 06 06 06'),
	(111, 'Avenue Mohamed V', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.053215', '-6.7952733', 'Pharmacie Alhan', '06 06 06 06 06'),
	(112, 'Avenue 20 Aout', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0410484', '-6.7798573', 'Pharmacie Volubilis', '06 06 06 06 06'),
	(113, 'Avenue 20 Aout', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0443499', '-6.7788471', 'Pharmacie El Ouedgi', '06 06 06 06 06'),
	(114, 'Avenue Imam Malik', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0445566', '-6.7825127', 'Pharmacie Ikram', '06 06 06 06 06'),
	(115, 'Avenue Imam Malik', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0407105', '-6.7843419', 'Pharmacie Safir', '06 06 06 06 06'),
	(116, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0465707', '-6.7806908', 'Pharmacie Lazrak', '06 06 06 06 06'),
	(117, 'Avenue Ali Ben Abi Taleb شارع علي بن أبي طالب', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0644811', '-6.7908671', 'Pharmacie L\'Hopital', '06 06 06 06 06'),
	(118, 'Avenue Omar Ibn Laass', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0614525', '-6.7928351', 'Pharmacie Zohour', '06 06 06 06 06'),
	(119, 'Avenue Mohamed V', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0618886', '-6.7828037', 'Pharmacie Super Arrahma', '06 06 06 06 06'),
	(120, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0239456', '-6.7567668', 'Pharmacie Al Wafae صيدلية الوفاء', '06 06 06 06 06'),
	(121, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0195302', '-6.7437123', 'Pharmacie Hssain صيدلية حصين', '06 06 06 06 06'),
	(122, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.025103', '-6.7524119', 'Pharmacie du Soleil صيدلية شمس', '06 06 06 06 06'),
	(123, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0239444', '-6.7494643', 'Pharmacie Ambra صيدلية عنبرة', '06 06 06 06 06'),
	(124, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0481748', '-6.8006116', 'Pasteur', '06 06 06 06 06'),
	(125, '', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0438361', '-6.804304', 'صيدلية الرياض', '06 06 06 06 06'),
	(126, 'Avenue Sidi Moussa', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0564026', '-6.8198596', 'Pharmacie al Kasba', '06 06 06 06 06'),
	(127, 'Avenue Lalla Amina', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0410362', '-6.8101288', 'Pharmacie Ibtissam', '06 06 06 06 06'),
	(128, 'Avenue Achouhada', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0385712', '-6.7920811', 'Pharmacie Achouhadaa', '06 06 06 06 06'),
	(129, 'Avenue Omar Bnou Abdelazziz', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0429026', '-6.7812576', 'Pharmacie Elamana', '06 06 06 06 06'),
	(130, 'Avenue Moulay Ismail شارع مولاي اسماعيل', 'email.pharmacie@gmail.com', '05 05 05 05 05', '34.0209889', '-6.8267416', 'Pharmacie Centre Chèques Postaux صيدلية مركز الشيكات البريدية', '06 06 06 06 06');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
