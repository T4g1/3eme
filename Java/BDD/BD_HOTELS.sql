SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
DROP DATABASE `bd_hotels`;
CREATE DATABASE `bd_hotels` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `bd_hotels`;

CREATE TABLE IF NOT EXISTS `accompagnants` (
  `id` int(4) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `adresse_domicile` varchar(255) DEFAULT NULL,
  `titulaire` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_voyageurs-accompagnants_voyageurs_idx` (`titulaire`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `activites` (
  `id` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `nb_max` int(4) DEFAULT NULL,
  `prix_htva` int(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `chambres` (
  `numero` int(5) NOT NULL,
  `douche` tinyint(1) DEFAULT '1',
  `baignoire` tinyint(1) DEFAULT '1',
  `cuvette` tinyint(1) DEFAULT '1',
  `nb_occupants` int(4) DEFAULT NULL,
  `prix_htva` int(6) DEFAULT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `reservations` (
  `id` varchar(255) NOT NULL,
  `chambre` int(5) DEFAULT NULL,
  `paye` tinyint(1) DEFAULT '0',
  `titulaire` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reservations_voyageurs1_idx` (`titulaire`),
  KEY `fk_reservations_chambres1_idx` (`chambre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
