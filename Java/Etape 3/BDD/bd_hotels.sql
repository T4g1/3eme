-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Sam 19 Janvier 2013 à 11:04
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `bd_hotels`
--

-- --------------------------------------------------------

--
-- Structure de la table `accompagnants`
--

CREATE TABLE IF NOT EXISTS `accompagnants` (
  `id` int(4) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `adresse_domicile` varchar(255) DEFAULT NULL,
  `titulaire` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_voyageurs-accompagnants_voyageurs_idx` (`titulaire`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `activites`
--

CREATE TABLE IF NOT EXISTS `activites` (
  `id` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `nb_max` int(4) DEFAULT NULL,
  `prix_htva` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `chambres`
--

CREATE TABLE IF NOT EXISTS `chambres` (
  `numero` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `categorie` enum('Motel','Village') NOT NULL DEFAULT 'Motel',
  `douche` tinyint(1) DEFAULT '1',
  `baignoire` tinyint(1) DEFAULT '1',
  `cuvette` tinyint(1) DEFAULT '1',
  `nb_occupants` int(4) DEFAULT NULL,
  `prix_htva` int(11) DEFAULT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `chambres`
--

INSERT INTO `chambres` (`numero`, `categorie`, `douche`, `baignoire`, `cuvette`, `nb_occupants`, `prix_htva`) VALUES
(1, 'Motel', 1, 1, 1, 2, 250),
(2, 'Motel', 1, 1, 1, 4, 300),
(3, 'Village', 1, 0, 2, 3, 300);

-- --------------------------------------------------------

--
-- Structure de la table `reservations`
--

CREATE TABLE IF NOT EXISTS `reservations` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `chambre` int(11) DEFAULT NULL,
  `paye` tinyint(1) DEFAULT '0',
  `titulaire` int(11) DEFAULT NULL,
  `debut` date NOT NULL,
  `duree` int(11) NOT NULL DEFAULT '1',
  `status` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_reservations_voyageurs1_idx` (`titulaire`),
  KEY `fk_reservations_chambres1_idx` (`chambre`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `reservations`
--

INSERT INTO `reservations` (`id`, `chambre`, `paye`, `titulaire`, `debut`, `duree`, `status`) VALUES
(7, 2, 0, 3, '2013-11-20', 3, 1),
(8, 1, 1, 4, '2011-12-20', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `voyageurs`
--

CREATE TABLE IF NOT EXISTS `voyageurs` (
  `id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `voyageurs`
--

INSERT INTO `voyageurs` (`id`, `username`, `nom`, `prenom`, `password`, `adresse`, `email`) VALUES
(3, 'T4g1', 'Van Gysegem', 'Thomas', 'azerty', 'Momalle, 4350, Rue du chêne, 46', 'thomasvangysegem@gmail.com'),
(4, 'gunner', 'Delsaux', 'Kevin', 'azertyaussi', 'Pas ici', 'gunner@gmail.com'),
(5, 'Thomas', NULL, NULL, 'azerty', NULL, NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
