-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 04 déc. 2024 à 05:53
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_ecole`
--

-- --------------------------------------------------------

--
-- Structure de la table `annee_scolaire`
--

DROP TABLE IF EXISTS `annee_scolaire`;
CREATE TABLE IF NOT EXISTS `annee_scolaire` (
  `id_annee_scolaire` int NOT NULL,
  PRIMARY KEY (`id_annee_scolaire`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `annee_scolaire`
--

INSERT INTO `annee_scolaire` (`id_annee_scolaire`) VALUES
(2017),
(2018);

-- --------------------------------------------------------

--
-- Structure de la table `bulletin`
--

DROP TABLE IF EXISTS `bulletin`;
CREATE TABLE IF NOT EXISTS `bulletin` (
  `id_bulletin` int NOT NULL AUTO_INCREMENT,
  `id_trimestre` int NOT NULL,
  `id_inscription` int NOT NULL,
  `appreciation_bulletin` varchar(255) NOT NULL,
  PRIMARY KEY (`id_bulletin`),
  KEY `id_trimestre` (`id_trimestre`),
  KEY `id_inscription` (`id_inscription`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `bulletin`
--

INSERT INTO `bulletin` (`id_bulletin`, `id_trimestre`, `id_inscription`, `appreciation_bulletin`) VALUES
(1, 1, 1, 'De grandes difficultés dans les matières scientifiques, mais Pierre peut se surpasser'),
(2, 1, 2, 'Bon trimestre'),
(3, 1, 3, 'Excellent ! '),
(6, 3, 4, 'blabla');

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `id_classe` int NOT NULL AUTO_INCREMENT,
  `nom_classe` varchar(255) NOT NULL,
  `id_ecole` int NOT NULL,
  `id_niveau` int NOT NULL,
  `id_annee_scolaire` int NOT NULL,
  PRIMARY KEY (`id_classe`),
  KEY `id_ecole` (`id_ecole`),
  KEY `id_niveau` (`id_niveau`),
  KEY `id_annee_scolaire` (`id_annee_scolaire`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`id_classe`, `nom_classe`, `id_ecole`, `id_niveau`, `id_annee_scolaire`) VALUES
(1, 'TS1', 1, 1, 2018),
(2, 'TS2', 1, 1, 2018),
(3, '1S1', 1, 2, 2017),
(4, '2G1', 1, 3, 2018);

-- --------------------------------------------------------

--
-- Structure de la table `detail_bulletin`
--

DROP TABLE IF EXISTS `detail_bulletin`;
CREATE TABLE IF NOT EXISTS `detail_bulletin` (
  `id_detail_bulletin` int NOT NULL AUTO_INCREMENT,
  `id_bulletin` int NOT NULL,
  `id_enseignement` int NOT NULL,
  `appreciation_detail` varchar(255) NOT NULL,
  PRIMARY KEY (`id_detail_bulletin`),
  KEY `id_bulletin` (`id_bulletin`),
  KEY `id_enseignement` (`id_enseignement`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `detail_bulletin`
--

INSERT INTO `detail_bulletin` (`id_detail_bulletin`, `id_bulletin`, `id_enseignement`, `appreciation_detail`) VALUES
(1, 1, 14, ''),
(2, 1, 15, ''),
(3, 1, 5, 'Bravo, continuez ainsi !'),
(4, 1, 6, 'Bon ensemble'),
(5, 1, 7, ''),
(6, 1, 8, ''),
(7, 1, 9, ''),
(8, 1, 10, ''),
(9, 1, 13, ''),
(10, 2, 5, 'Bien'),
(11, 2, 6, 'Des progrès encourageant'),
(12, 2, 7, ''),
(13, 2, 8, ''),
(14, 2, 9, ''),
(15, 2, 10, ''),
(16, 2, 11, ''),
(17, 2, 13, ''),
(18, 2, 15, ''),
(19, 6, 5, 'blabla'),
(20, 3, 5, ''),
(21, 3, 6, ''),
(22, 3, 7, ''),
(23, 3, 8, ''),
(24, 3, 9, ''),
(25, 3, 10, ''),
(26, 3, 12, ''),
(27, 3, 13, ''),
(28, 3, 15, '');

-- --------------------------------------------------------

--
-- Structure de la table `discipline`
--

DROP TABLE IF EXISTS `discipline`;
CREATE TABLE IF NOT EXISTS `discipline` (
  `id_discipline` int NOT NULL AUTO_INCREMENT,
  `nom_discipline` varchar(255) NOT NULL,
  PRIMARY KEY (`id_discipline`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `discipline`
--

INSERT INTO `discipline` (`id_discipline`, `nom_discipline`) VALUES
(5, 'Histoire-Géographie'),
(6, 'Français'),
(7, 'Philosophie'),
(8, 'SVT'),
(9, 'EPS'),
(11, 'Anglais'),
(12, 'Chinois'),
(13, 'Mathématiques'),
(14, 'Allemand'),
(15, 'Espagnol'),
(16, 'Physique-chimie');

-- --------------------------------------------------------

--
-- Structure de la table `ds`
--

DROP TABLE IF EXISTS `ds`;
CREATE TABLE IF NOT EXISTS `ds` (
  `id_ds` int NOT NULL AUTO_INCREMENT,
  `nom_ds` varchar(255) NOT NULL,
  PRIMARY KEY (`id_ds`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ds`
--

INSERT INTO `ds` (`id_ds`, `nom_ds`) VALUES
(1, 'DS1 HG'),
(2, 'DS2 HG'),
(3, 'DS1 Fr'),
(4, 'DS2 Fr'),
(5, 'DS1 HG'),
(6, 'DS1 Philo'),
(7, 'DS2 Philo'),
(8, 'DS1 SVT'),
(9, 'DS2 SVT'),
(10, 'EPS'),
(11, 'DS1 Anglais'),
(12, 'DS2 Anglais'),
(13, 'DS1 CH'),
(14, 'DS2 CH'),
(15, 'DS1 Math'),
(16, 'DS2 Math'),
(17, 'DS1 All'),
(18, 'DS2 All'),
(19, 'DS1 Esp'),
(20, 'DS2 Esp'),
(21, 'DS1 PC'),
(22, 'DS2 PC');

-- --------------------------------------------------------

--
-- Structure de la table `ecole`
--

DROP TABLE IF EXISTS `ecole`;
CREATE TABLE IF NOT EXISTS `ecole` (
  `id_ecole` int NOT NULL AUTO_INCREMENT,
  `nom_ecole` varchar(255) NOT NULL,
  `adresse_ecole` varchar(255) NOT NULL,
  PRIMARY KEY (`id_ecole`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ecole`
--

INSERT INTO `ecole` (`id_ecole`, `nom_ecole`, `adresse_ecole`) VALUES
(1, 'Lycée Charlemagne', '13 Rue Charlemagne, 75004 Paris'),
(2, 'Lycée Hélène Boucher', '75 Cours de Vincennes, 75020 Paris');

-- --------------------------------------------------------

--
-- Structure de la table `enseignement`
--

DROP TABLE IF EXISTS `enseignement`;
CREATE TABLE IF NOT EXISTS `enseignement` (
  `id_enseignement` int NOT NULL AUTO_INCREMENT,
  `id_classe` int NOT NULL,
  `id_discipline` int NOT NULL,
  `id_personne` int NOT NULL,
  PRIMARY KEY (`id_enseignement`),
  KEY `id_classe` (`id_classe`),
  KEY `id_discipline` (`id_discipline`),
  KEY `id_personne` (`id_personne`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `enseignement`
--

INSERT INTO `enseignement` (`id_enseignement`, `id_classe`, `id_discipline`, `id_personne`) VALUES
(5, 1, 5, 18),
(6, 1, 6, 13),
(7, 1, 7, 14),
(8, 1, 8, 11),
(9, 1, 9, 15),
(10, 1, 11, 8),
(11, 1, 12, 9),
(12, 1, 14, 10),
(13, 1, 13, 17),
(14, 1, 15, 16),
(15, 1, 16, 12),
(16, 3, 5, 18);

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE IF NOT EXISTS `evaluation` (
  `id_evaluation` int NOT NULL AUTO_INCREMENT,
  `id_detail_bulletin` int NOT NULL,
  `id_ds` int NOT NULL,
  `note_evaluation` double NOT NULL,
  `coeff` int NOT NULL,
  `appreciation_evaluation` varchar(255) NOT NULL,
  PRIMARY KEY (`id_evaluation`),
  KEY `id_ds` (`id_ds`),
  KEY `id_detail_bulletin` (`id_detail_bulletin`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evaluation`
--

INSERT INTO `evaluation` (`id_evaluation`, `id_detail_bulletin`, `id_ds`, `note_evaluation`, `coeff`, `appreciation_evaluation`) VALUES
(3, 3, 1, 16, 1, 'Très bien !'),
(4, 4, 3, 12, 1, 'Bon travail'),
(5, 3, 2, 14, 1, ''),
(6, 4, 4, 10, 1, 'Ensemble correcte'),
(7, 10, 1, 15, 1, ''),
(8, 10, 2, 12, 1, ''),
(9, 11, 3, 8.5, 1, ''),
(10, 11, 4, 10, 1, ''),
(11, 19, 5, 20, 1, 'blabla'),
(12, 20, 1, 18, 1, ''),
(13, 20, 2, 17, 1, ''),
(14, 21, 3, 17, 1, ''),
(15, 21, 4, 16, 1, ''),
(16, 1, 19, 15, 1, ''),
(17, 1, 20, 13, 1, ''),
(18, 2, 21, 10, 1, ''),
(19, 2, 22, 9.5, 1, ''),
(20, 5, 6, 13, 1, ''),
(21, 5, 7, 12, 1, ''),
(22, 6, 8, 8, 1, ''),
(23, 6, 9, 9.75, 1, ''),
(24, 7, 10, 18, 1, 'Très don dynamisme sur le terrain de sport!'),
(25, 8, 11, 12, 1, ''),
(26, 8, 12, 13, 1, ''),
(27, 9, 15, 8, 1, ''),
(28, 9, 16, 6, 1, ''),
(29, 12, 6, 8, 1, 'Développez plus votre commentaire !'),
(30, 12, 7, 7, 1, 'Il faut mieux gérer votre temps'),
(31, 13, 8, 16, 1, ''),
(32, 13, 9, 18, 1, ''),
(33, 14, 10, 14, 1, 'Bon esprit d\'équipe en terrain'),
(34, 15, 11, 14, 1, 'Good commentary'),
(35, 15, 12, 11, 1, 'Need to see you grammar again ...'),
(36, 16, 13, 20, 1, ''),
(37, 16, 14, 19, 1, ''),
(38, 17, 15, 12, 1, ''),
(39, 17, 16, 14, 1, ''),
(40, 18, 21, 15, 1, ''),
(41, 18, 22, 13, 1, ''),
(46, 22, 6, 15, 1, ''),
(47, 22, 7, 15.5, 1, ''),
(48, 23, 8, 16, 1, ''),
(49, 23, 9, 18, 1, ''),
(50, 24, 10, 10, 1, 'Ce n\'est parce que vous êtes une fille que cela signifie que vous êtes nul en sport !'),
(51, 25, 11, 14, 1, ''),
(52, 25, 12, 15, 1, ''),
(53, 26, 17, 14, 1, ''),
(54, 26, 18, 15, 1, ''),
(55, 27, 15, 14.75, 1, ''),
(56, 27, 16, 16.5, 1, ''),
(57, 28, 21, 16.25, 1, ''),
(58, 28, 22, 15.5, 1, '');

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
CREATE TABLE IF NOT EXISTS `inscription` (
  `id_inscription` int NOT NULL AUTO_INCREMENT,
  `id_classe` int NOT NULL,
  `id_personne` int NOT NULL,
  PRIMARY KEY (`id_inscription`),
  KEY `id_classe` (`id_classe`),
  KEY `id_personne` (`id_personne`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`id_inscription`, `id_classe`, `id_personne`) VALUES
(1, 1, 1),
(2, 1, 3),
(3, 1, 4),
(4, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `niveau`
--

DROP TABLE IF EXISTS `niveau`;
CREATE TABLE IF NOT EXISTS `niveau` (
  `id_niveau` int NOT NULL AUTO_INCREMENT,
  `nom_niveau` varchar(255) NOT NULL,
  PRIMARY KEY (`id_niveau`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `niveau`
--

INSERT INTO `niveau` (`id_niveau`, `nom_niveau`) VALUES
(1, 'Terminale'),
(2, 'Première'),
(3, 'Seconde');

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `id_personne` int NOT NULL AUTO_INCREMENT,
  `nom_personne` varchar(255) NOT NULL,
  `prenom_personne` varchar(255) NOT NULL,
  `civilite` varchar(4) NOT NULL,
  `type_personne` varchar(255) NOT NULL,
  `Identifiant` varchar(255) NOT NULL,
  `Mdp` varchar(255) NOT NULL,
  PRIMARY KEY (`id_personne`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`id_personne`, `nom_personne`, `prenom_personne`, `civilite`, `type_personne`, `Identifiant`, `Mdp`) VALUES
(1, 'Dupont', 'Pierre', 'M.', 'Elève', 'pierre.dupont', '1234'),
(3, 'Cheong', 'Loïc', 'M.', 'Elève', 'loic.cheong', '1234'),
(4, 'Noireaux', 'Coline', 'Mlle', 'Elève', 'coline.noireaux', '1234'),
(8, 'Holmes', 'Liam', 'M.', 'Enseignant', 'liam.holmes', '1234'),
(9, 'Zhang', 'Yuan', 'Mme', 'Enseignant', 'yuan.zhang', '1234'),
(10, 'Schmidt', 'Bertha', 'Mme', 'Enseignant', 'bertha.schmidt', '1234'),
(11, 'Pasteur', 'Louis', 'M.', 'Enseignant', 'louis.pasteur', '1234'),
(12, 'Curie', 'Marie', 'Mme', 'Enseignant', 'marie.curie', '1234'),
(13, 'Hugo', 'Victor', 'M.', 'Enseignant', 'victor.hugo', '1234'),
(14, 'Rousseau', 'Jean-Jacques', 'M.', 'Enseignant', 'jq.rousseau', '1234'),
(15, 'Schwarzenegger', 'Arnold', 'M.', 'Enseignant', 'arnold.schwarzenegger', '1234'),
(16, 'Kahlo', 'Frida', 'Mme', 'Enseignant', 'frida.kahlo', '1234'),
(17, 'D\'Athènes', 'Pythagore', 'M.', 'Enseignant', 'pythagore', '1234'),
(18, 'd\'Halicarnasse', 'Hérodote', 'M.', 'Enseignant', 'herodote', '1234'),
(19, 'Manolo', 'Hina', 'M.', 'Admin', 'manolo.hina', '1234');

-- --------------------------------------------------------

--
-- Structure de la table `trimestre`
--

DROP TABLE IF EXISTS `trimestre`;
CREATE TABLE IF NOT EXISTS `trimestre` (
  `id_trimestre` int NOT NULL AUTO_INCREMENT,
  `numero_trimestre` int NOT NULL,
  `debut_trimestre` date NOT NULL,
  `fin_trimestre` date NOT NULL,
  `id_annee_scolaire` int NOT NULL,
  PRIMARY KEY (`id_trimestre`),
  KEY `id_annee_scolaire` (`id_annee_scolaire`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `trimestre`
--

INSERT INTO `trimestre` (`id_trimestre`, `numero_trimestre`, `debut_trimestre`, `fin_trimestre`, `id_annee_scolaire`) VALUES
(1, 1, '2018-09-01', '2018-11-01', 2018),
(2, 2, '2018-11-02', '2019-03-01', 2018),
(3, 1, '2017-09-01', '2017-11-01', 2017);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bulletin`
--
ALTER TABLE `bulletin`
  ADD CONSTRAINT `bulletin_ibfk_1` FOREIGN KEY (`id_trimestre`) REFERENCES `trimestre` (`id_trimestre`),
  ADD CONSTRAINT `bulletin_ibfk_2` FOREIGN KEY (`id_inscription`) REFERENCES `inscription` (`id_inscription`);

--
-- Contraintes pour la table `classe`
--
ALTER TABLE `classe`
  ADD CONSTRAINT `classe_ibfk_1` FOREIGN KEY (`id_ecole`) REFERENCES `ecole` (`id_ecole`),
  ADD CONSTRAINT `classe_ibfk_2` FOREIGN KEY (`id_niveau`) REFERENCES `niveau` (`id_niveau`),
  ADD CONSTRAINT `classe_ibfk_3` FOREIGN KEY (`id_annee_scolaire`) REFERENCES `annee_scolaire` (`id_annee_scolaire`);

--
-- Contraintes pour la table `detail_bulletin`
--
ALTER TABLE `detail_bulletin`
  ADD CONSTRAINT `detail_bulletin_ibfk_1` FOREIGN KEY (`id_bulletin`) REFERENCES `bulletin` (`id_bulletin`),
  ADD CONSTRAINT `detail_bulletin_ibfk_2` FOREIGN KEY (`id_enseignement`) REFERENCES `enseignement` (`id_enseignement`);

--
-- Contraintes pour la table `enseignement`
--
ALTER TABLE `enseignement`
  ADD CONSTRAINT `enseignement_ibfk_1` FOREIGN KEY (`id_classe`) REFERENCES `classe` (`id_classe`),
  ADD CONSTRAINT `enseignement_ibfk_2` FOREIGN KEY (`id_discipline`) REFERENCES `discipline` (`id_discipline`),
  ADD CONSTRAINT `enseignement_ibfk_3` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id_personne`);

--
-- Contraintes pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD CONSTRAINT `evaluation_ibfk_1` FOREIGN KEY (`id_ds`) REFERENCES `ds` (`id_ds`),
  ADD CONSTRAINT `evaluation_ibfk_2` FOREIGN KEY (`id_detail_bulletin`) REFERENCES `detail_bulletin` (`id_detail_bulletin`);

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `inscription_ibfk_1` FOREIGN KEY (`id_classe`) REFERENCES `classe` (`id_classe`),
  ADD CONSTRAINT `inscription_ibfk_2` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id_personne`);

--
-- Contraintes pour la table `trimestre`
--
ALTER TABLE `trimestre`
  ADD CONSTRAINT `trimestre_ibfk_1` FOREIGN KEY (`id_annee_scolaire`) REFERENCES `annee_scolaire` (`id_annee_scolaire`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
