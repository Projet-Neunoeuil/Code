SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+01:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `Application`
--
CREATE DATABASE IF NOT EXISTS `Application` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `Application`;

-- --------------------------------------------------------

--
-- Structure de la table `LastDay`
--

CREATE TABLE `LastDay` (
  `value` decimal(3,1) NOT NULL,
  `hour` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tronquer la table avant d'insérer `LastDay`
--

TRUNCATE TABLE `LastDay`;
--
-- Insertion du jeu d'éssai `LastDay`
--

INSERT INTO `LastDay` (`value`, `hour`) VALUES
('23.9', '2021-03-15 12:00:00'),
('23.2', '2021-03-15 13:00:00'),
('23.3', '2021-03-15 14:00:00'),
('23.8', '2021-03-15 15:00:00'),
('23.7', '2021-03-15 16:00:00'),
('23.6', '2021-03-15 17:00:00'),
('23.5', '2021-03-15 18:00:00'),
('23.0', '2021-03-15 19:00:00'),
('23.7', '2021-03-15 20:00:00'),
('23.2', '2021-03-15 21:00:00'),
('23.1', '2021-03-15 22:00:00'),
('23.8', '2021-03-15 23:00:00'),
('23.2', '2021-03-16 00:00:00'),
('23.0', '2021-03-16 01:00:00'),
('23.4', '2021-03-16 02:00:00'),
('23.1', '2021-03-16 03:00:00'),
('23.4', '2021-03-16 04:00:00'),
('23.5', '2021-03-16 05:00:00'),
('23.0', '2021-03-16 06:00:00'),
('23.1', '2021-03-16 07:00:00'),
('23.3', '2021-03-16 08:00:00'),
('23.2', '2021-03-16 09:00:00'),
('23.5', '2021-03-16 09:28:50'),
('23.3', '2021-03-16 10:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `LastMonth`
--

CREATE TABLE `LastMonth` (
  `value` decimal(3,1) NOT NULL,
  `day` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tronquer la table avant d'insérer `LastMonth`
--

TRUNCATE TABLE `LastMonth`;
--
-- Insertion du jeu d'éssai `LastMonth`
--

INSERT INTO `LastMonth` (`value`, `day`) VALUES
('23.3', '2021-02-11'),
('20.0', '2021-02-12'),
('21.1', '2021-02-13'),
('22.5', '2021-02-14'),
('23.5', '2021-02-15'),
('24.7', '2021-02-16'),
('20.1', '2021-02-17'),
('24.5', '2021-02-19'),
('25.8', '2021-02-20'),
('30.0', '2021-02-21'),
('23.3', '2021-02-22'),
('24.4', '2021-02-23'),
('19.8', '2021-02-25'),
('20.3', '2021-02-26'),
('23.7', '2021-02-27'),
('19.9', '2021-02-28'),
('25.5', '2021-03-01'),
('28.9', '2021-03-02'),
('27.6', '2021-03-03'),
('23.3', '2021-03-04'),
('22.3', '2021-03-05'),
('25.7', '2021-03-06'),
('24.4', '2021-03-07'),
('23.3', '2021-03-08'),
('20.7', '2021-03-09'),
('27.5', '2021-03-10'),
('23.6', '2021-03-11'),
('21.5', '2021-03-12'),
('21.4', '2021-03-13'),
('21.3', '2021-03-14'),
('22.4', '2021-03-15');

-- --------------------------------------------------------

--
-- Structure de la table `LastWeek`
--

CREATE TABLE `LastWeek` (
  `value` decimal(3,1) NOT NULL,
  `day` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tronquer la table avant d'insérer `LastWeek`
--

TRUNCATE TABLE `LastWeek`;
--
-- Insertion du jeu d'éssai `LastWeek`
--

INSERT INTO `LastWeek` (`value`, `day`) VALUES
('20.4', '2021-03-12'),
('20.9', '2021-03-13'),
('21.3', '2021-03-14'),
('22.4', '2021-03-15'),
('22.1', '2021-03-16'),
('22.6', '2021-03-17'),
('23.1', '2021-03-18');

-- --------------------------------------------------------

--
-- Structure de la table `LastYear`
--

CREATE TABLE `LastYear` (
  `value` decimal(3,1) NOT NULL,
  `month` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tronquer la table avant d'insérer `LastYear`
--

TRUNCATE TABLE `LastYear`;
--
-- Insertion du jeu d'éssai `LastYear`
--

INSERT INTO `LastYear` (`value`, `month`) VALUES
('22.2', '2020-04-01'),
('23.4', '2020-05-01'),
('23.9', '2020-06-01'),
('24.8', '2020-07-01'),
('25.3', '2020-08-01'),
('23.9', '2020-09-01'),
('22.4', '2020-10-01'),
('21.3', '2020-11-01'),
('20.1', '2020-12-01'),
('19.4', '2021-01-01'),
('20.5', '2021-02-01'),
('21.6', '2021-03-01');

-- --------------------------------------------------------

--
-- Structure de la table `Parameters`
--

CREATE TABLE `Parameters` (
  `minTemp` double(3,1) NOT NULL,
  `maxTemp` double(3,1) NOT NULL,
  `whiteTime` time NOT NULL,
  `blueTime` time NOT NULL,
  `waterLevel` tinyint(1) NOT NULL,
  `periodGetTemp` int(11) NOT NULL COMMENT 'En minutes',
  `periodChangeWater` int(11) NOT NULL COMMENT 'En jours'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tronquer la table avant d'insérer `Parameters`
--

TRUNCATE TABLE `Parameters`;
--
-- Insertion du jeu d'éssai `Parameters`
--

INSERT INTO `Parameters` (`minTemp`, `maxTemp`, `whiteTime`, `blueTime`, `waterLevel`, `periodGetTemp`, `periodChangeWater`) VALUES
(18.0, 28.0, '08:00:00', '17:30:00', 1, 10, 3);

-- --------------------------------------------------------

--
-- Structure de la table `Temperature`
--

CREATE TABLE `Temperature` (
  `value` double(3,1) NOT NULL,
  `time` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tronquer la table avant d'insérer `Temperature`
--

TRUNCATE TABLE `Temperature`;
--
--  Insertion du jeu d'éssai `Temperature`
--

INSERT INTO `Temperature` (`value`, `time`) VALUES
(25.9, '2021-03-15 21:48:27'),
(20.7, '2021-03-15 22:21:24');

-- --------------------------------------------------------

--
-- Structure de la table `Water`
--

CREATE TABLE `Water` (
  `waterid` bigint(20) UNSIGNED NOT NULL,
  `lastChange` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tronquer la table avant d'insérer `Water`
--

TRUNCATE TABLE `Water`;
--
-- Insertion du jeu d'éssai Water
--

INSERT INTO `Water` (`waterid`, `lastChange`) VALUES
(1, '2021-03-14'),
(2, '2021-03-17'),
(3, '2021-03-18'),
(4, '2021-03-17');


--
-- Index pour la table `LastDay`
--
ALTER TABLE `LastDay`
  ADD PRIMARY KEY (`hour`);

--
-- Index pour la table `LastMonth`
--
ALTER TABLE `LastMonth`
  ADD PRIMARY KEY (`day`);

--
-- Index pour la table `LastWeek`
--
ALTER TABLE `LastWeek`
  ADD PRIMARY KEY (`day`);

--
-- Index pour la table `LastYear`
--
ALTER TABLE `LastYear`
  ADD PRIMARY KEY (`month`);

--
-- Index pour la table `Parameters`
--
ALTER TABLE `Parameters`
  ADD PRIMARY KEY (`minTemp`);

--
-- Index pour la table `Temperature`
--
ALTER TABLE `Temperature`
  ADD PRIMARY KEY (`time`);

--
-- Index pour la table `Water`
--
ALTER TABLE `Water`
  ADD PRIMARY KEY (`waterid`);


--
-- AUTO_INCREMENT pour la table `Water`
--
ALTER TABLE `Water`
  MODIFY `waterid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
