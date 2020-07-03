-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Июл 03 2020 г., 13:34
-- Версия сервера: 10.1.40-MariaDB
-- Версия PHP: 7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `pharmacy`
--

-- --------------------------------------------------------

--
-- Структура таблицы `log`
--

CREATE TABLE `log` (
  `ID` int(11) NOT NULL,
  `PHARMACIST_NAME` varchar(50) NOT NULL,
  `CLIENT_NAME` varchar(50) NOT NULL,
  `CLIENT_LIST` varchar(256) NOT NULL,
  `TIME_OF_SERVE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `log`
--

INSERT INTO `log` (`ID`, `PHARMACIST_NAME`, `CLIENT_NAME`, `CLIENT_LIST`, `TIME_OF_SERVE`) VALUES
(1, 'pharmacist1', 'client1', '( Aspirin  VitaminC  Aflubin  Aflubin  Suprastin )', 10),
(2, 'pharmacist1', 'client1', '( Aspirin  VitaminC  Aflubin  Aflubin  Suprastin )', 10),
(3, 'pharmacist1', 'client1', '( VitaminC )', 14),
(4, 'pharmacist2', 'client21', '( Gentos  Noshpa )', 1),
(5, 'pharmacist2', 'client23', '( VitaminC )', 3),
(6, 'pharmacist2', 'client22', '( Aflubin  Antiflu )', 3),
(7, 'pharmacist3', 'client31', '( Sinupret  Delufen  Aspirin  Noshpa  Noshpa )', 9),
(8, 'pharmacist1', 'client3', '( Ambroksol  Aspirin  Traumel  Suprastin )', 16),
(9, 'pharmacist3', 'client33', '( Gentos  Delufen  Aspirin )', 16),
(10, 'pharmacist1', 'client2', '( Sinupret  Sinupret  Traumel  VitaminC  Gentos )', 16),
(11, 'pharmacist3', 'client32', '( Ambroksol  Nimesil  VitaminC  Aspirin  Traumel )', 16),
(12, 'Pharmacist-1', 'client1', '( Spazmalgon )', 12),
(13, 'Pharmacist-2', 'client1', '( Relif  Ambroksol  Ambroksol )', 16),
(14, 'Pharmacist-3', 'client1', '( Gentos  Relif  Ambroksol )', 20),
(15, 'Pharmacist-1', 'client4', '( Aspirin  Suprastin  Sinupret  Gentos )', 4);

-- --------------------------------------------------------

--
-- Структура таблицы `medicine`
--

CREATE TABLE `medicine` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `TIME` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `medicine`
--

INSERT INTO `medicine` (`ID`, `NAME`, `TIME`) VALUES
(1, 'Nurofen', 5),
(2, 'Aspirin', 2),
(3, 'Sinupret', 3),
(4, 'Antiflu', 1),
(5, 'Gentos', 4),
(6, 'Traumel', 5),
(7, 'Spazmalgon', 2),
(8, 'Nimesil', 4),
(9, 'Noshpa', 1),
(10, 'Aflubin', 2),
(11, 'Delufen', 3),
(12, 'Relif', 5),
(13, 'VitaminC', 1),
(14, 'Ambroksol', 4),
(15, 'Suprastin', 3);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `log`
--
ALTER TABLE `log`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT для таблицы `medicine`
--
ALTER TABLE `medicine`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
