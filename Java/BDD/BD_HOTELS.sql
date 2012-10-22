-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Dim 14 Octobre 2012 à 14:31
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
CREATE DATABASE `bd_hotels` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `bd_hotels`;

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

INSERT INTO `chambres` (`numero`, `douche`, `baignoire`, `cuvette`, `nb_occupants`, `prix_htva`) VALUES
(1, 1, 1, 1, 2, 250),
(2, 1, 1, 1, 4, 300),
(3, 1, 0, 2, 3, 300);

-- --------------------------------------------------------

--
-- Structure de la table `reservations`
--

CREATE TABLE IF NOT EXISTS `reservations` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `chambre` int(11) DEFAULT NULL,
  `paye` tinyint(1) DEFAULT '0',
  `titulaire` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reservations_voyageurs1_idx` (`titulaire`),
  KEY `fk_reservations_chambres1_idx` (`chambre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
