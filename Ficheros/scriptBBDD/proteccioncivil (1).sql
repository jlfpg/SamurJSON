-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 11-01-2018 a las 11:24:35
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proteccioncivil`
--
CREATE DATABASE IF NOT EXISTS `proteccioncivil` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `proteccioncivil`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asigna`
--

DROP TABLE IF EXISTS `asigna`;
CREATE TABLE `asigna` (
  `dd` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

DROP TABLE IF EXISTS `empleado`;
CREATE TABLE `empleado` (
  `codinterno` int(11) NOT NULL,
  `dni` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `fechanaciemiento` varchar(45) NOT NULL,
  `codPark` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`codinterno`, `dni`, `nombre`, `apellido`, `fechanaciemiento`, `codPark`) VALUES
(6, '46547378R', 'Roberto', 'Fuster Sanchez', '2017-10-04', 6),
(7, 'Z7377042G', 'Antonio', 'Gonzalez Rodriguez', '2017-09-27', 7),
(8, 'H57329450', 'Jorge', 'Ramirez Sanz', '2017-09-26', 8),
(9, '67985094j', 'Jordi', 'Ck Kid', '2005-01-07', 7),
(10, '23456', 'Antoni', 'O', '2017-11-01', 5),
(11, '44444444', 'ttttttttttttttttt', 'yyyyyyyyyyyyyy', '2018-01-12', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `instalacion`
--

DROP TABLE IF EXISTS `instalacion`;
CREATE TABLE `instalacion` (
  `codparque` int(255) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `instalacion`
--

INSERT INTO `instalacion` (`codparque`, `nombre`, `telefono`, `direccion`) VALUES
(5, 'Torre de pisa', '918509499', 'Italia'),
(6, 'Coliseo', '913458675', 'Italia'),
(7, 'Las ventas', '913489944', 'Madrid'),
(8, 'Tarifa', '908445534', 'Andalucia'),
(9, 'ddd', '333', 'ddd'),
(10, 'eeee', '333', 'rrr'),
(11, 'gggggg', '2343243234', 'gtgtgtgtg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notificacion`
--

DROP TABLE IF EXISTS `notificacion`;
CREATE TABLE `notificacion` (
  `codnotificacion` int(11) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `urgencia` enum('baja','media','alta') DEFAULT NULL,
  `tipo` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `notificacion`
--

INSERT INTO `notificacion` (`codnotificacion`, `direccion`, `urgencia`, `tipo`) VALUES
(1, 'alalalal', 'media', 'fuego'),
(2, 'gg', 'alta', 'frf');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recibe`
--

DROP TABLE IF EXISTS `recibe`;
CREATE TABLE `recibe` (
  `sdgw` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`codinterno`),
  ADD KEY `codPark` (`codPark`);

--
-- Indices de la tabla `instalacion`
--
ALTER TABLE `instalacion`
  ADD PRIMARY KEY (`codparque`);

--
-- Indices de la tabla `notificacion`
--
ALTER TABLE `notificacion`
  ADD PRIMARY KEY (`codnotificacion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `codinterno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT de la tabla `instalacion`
--
ALTER TABLE `instalacion`
  MODIFY `codparque` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT de la tabla `notificacion`
--
ALTER TABLE `notificacion`
  MODIFY `codnotificacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`codPark`) REFERENCES `instalacion` (`codparque`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
