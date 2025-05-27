-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-05-2025 a las 22:36:47
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `zitapp`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `appointments`
--

CREATE TABLE `appointments` (
  `id` bigint(20) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_negocio` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `estado` varchar(20) DEFAULT 'pendiente'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `appointments`
--

INSERT INTO `appointments` (`id`, `id_cliente`, `id_negocio`, `fecha`, `hora`, `estado`) VALUES
(2, 2, 2, '2025-05-11', '09:15:00', 'FINALIZADA'),
(3, 17, 3, '2025-05-12', '18:00:00', 'PENDIENTE'),
(4, 17, 4, '2025-05-13', '14:30:00', 'CONFIRMADA'),
(5, 9, 5, '2025-05-14', '12:00:00', 'PENDIENTE'),
(7, 2, 5, '2025-05-16', '16:30:00', 'CONFIRMADA'),
(8, 6, 2, '2025-05-17', '11:45:00', 'PENDIENTE'),
(9, 7, 1, '2025-05-18', '15:00:00', 'CONFIRMADA'),
(10, 9, 4, '2025-05-19', '09:30:00', 'CONFIRMADA'),
(12, 2, 3, '2025-05-21', '08:00:00', 'CONFIRMADA'),
(13, 6, 4, '2025-05-22', '13:30:00', 'CANCELADA'),
(14, 7, 2, '2025-05-23', '10:45:00', 'PENDIENTE'),
(15, 9, 1, '2025-05-24', '16:00:00', 'CONFIRMADA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `availability`
--

CREATE TABLE `availability` (
  `id` int(11) NOT NULL,
  `id_negocio` int(11) NOT NULL,
  `día` varchar(255) NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fin` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `availability`
--

INSERT INTO `availability` (`id`, `id_negocio`, `día`, `hora_inicio`, `hora_fin`) VALUES
(1, 1, 'Lunes', '09:00:00', '18:00:00'),
(2, 1, 'Martes', '09:00:00', '18:00:00'),
(3, 1, 'Miércoles', '09:00:00', '18:00:00'),
(4, 1, 'Jueves', '09:00:00', '18:00:00'),
(5, 1, 'Viernes', '09:00:00', '17:00:00'),
(6, 2, 'Lunes', '08:00:00', '16:00:00'),
(7, 2, 'Martes', '08:00:00', '16:00:00'),
(8, 2, 'Miércoles', '08:00:00', '16:00:00'),
(9, 2, 'Jueves', '08:00:00', '16:00:00'),
(10, 2, 'Viernes', '08:00:00', '14:00:00'),
(11, 3, 'Lunes', '06:00:00', '22:00:00'),
(12, 3, 'Martes', '06:00:00', '22:00:00'),
(13, 3, 'Miércoles', '06:00:00', '22:00:00'),
(14, 3, 'Jueves', '06:00:00', '22:00:00'),
(15, 3, 'Viernes', '06:00:00', '22:00:00'),
(16, 3, 'Sábado', '08:00:00', '20:00:00'),
(17, 3, 'Domingo', '08:00:00', '14:00:00'),
(18, 4, 'Lunes', '08:00:00', '18:00:00'),
(19, 4, 'Martes', '08:00:00', '18:00:00'),
(20, 4, 'Miércoles', '08:00:00', '18:00:00'),
(21, 4, 'Jueves', '08:00:00', '18:00:00'),
(22, 4, 'Viernes', '08:00:00', '18:00:00'),
(23, 4, 'Sábado', '09:00:00', '13:00:00'),
(24, 5, 'Martes', '10:00:00', '20:00:00'),
(25, 5, 'Miércoles', '10:00:00', '20:00:00'),
(26, 5, 'Jueves', '10:00:00', '20:00:00'),
(27, 5, 'Viernes', '10:00:00', '20:00:00'),
(28, 5, 'Sábado', '10:00:00', '16:00:00'),
(29, 1, 'Lunes', '09:00:00', '18:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `business`
--

CREATE TABLE `business` (
  `id` bigint(20) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `businesses`
--

CREATE TABLE `businesses` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `categoría` varchar(50) DEFAULT NULL,
  `descripción` text DEFAULT NULL,
  `dirección` varchar(200) DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `businesses`
--

INSERT INTO `businesses` (`id`, `nombre`, `categoría`, `descripción`, `dirección`, `imagen_url`, `id_usuario`, `categoria`, `descripcion`, `direccion`) VALUES
(1, 'Peluquería juanito', NULL, '', '', 'https://media.istockphoto.com/id/1497806504/es/foto/peluquer%C3%ADa-en-sal%C3%B3n-de-belleza-la-mujer-se-peina-en-el-sal%C3%B3n-de-belleza-moderno-estilista-seca.jpg?s=1024x1024&w=is&k=20&c=WuBXse8zPMp6wg8heu7V96uT3HMUJhwuwpSk8t3wZfs=', 2, 'BELLEZA', 'somos la peluqueria mas gay', '6.244381968590373, -75.55975949037125'),
(2, 'Clínica Dental Ana', NULL, '', '', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTv_vYxLTv-SujVdF7ncNrvPvy5GsV2pXPZ6w&s', 4, 'SALUD', 'Servicios dentales de alta calidad', '6.247843715524133, -75.56367957238065'),
(3, 'Gimnasio Roberto', NULL, '', '', 'https://blog.trainingym.com/hs-fs/hubfs/AdobeStock_174212531%20(1).jpeg?width=999&height=667&name=AdobeStock_174212531%20(1).jpeg', 5, 'FITNESS', 'Equipamiento moderno y entrenadores personales', '6.241886125854007, -75.56786002019788'),
(4, 'Taller Mecánico Laura', NULL, '', '', 'https://ovaciones.com/wp-content/uploads/2024/03/Daniel-Reza-2024-03-08T084225.822.png', 6, 'AUTOMOTRIZ', 'Reparación y mantenimiento de vehículos', '6.245307844703075, -75.57160940448486'),
(5, 'Centro de Masajes Carmen', NULL, '', '', 'https://www.flowww.es/hubfs/Q12023%20Marzo/Im%C3%A1genes%20blog/decoracion-negocios-wellness.webp', 1, 'BIENESTAR', 'Masajes terapéuticos y relajantes', '6.252065190092064, -75.56789402731395'),
(6, 'Peluquería Carlos', NULL, NULL, NULL, 'https://cdn1.treatwell.net/images/view/v2.i11964643.w720.h480.xDC84654A/', 3, 'BELLEZA', 'la mejor barberia de la ciudad', '6.23422139304263, -75.56946590657066');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `services`
--

CREATE TABLE `services` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `business_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `services`
--

INSERT INTO `services` (`id`, `descripcion`, `nombre`, `precio`, `business_id`) VALUES
(4, 'Servicio profesional de peluquería', 'Corte de cabello', 2001, 1),
(5, 'prueba1', 'prueba1', 20000, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `tipo` varchar(20) NOT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `nombre`, `email`, `tipo`, `contrasena`, `telefono`) VALUES
(1, 'Masajes Carmen', 'centrodemasajescarmen@correo.com', 'NEGOCIO', '12345', '312'),
(2, 'peluqueria juanito', 'peluqueriajuanito@example.com', 'NEGOCIO', '12345', '1111111'),
(3, 'Carlos peluqueria', 'carlospeluqueria@example.com', 'NEGOCIO', '12345', NULL),
(4, 'clínica dental Ana', 'clinicadentalana@example.com', 'NEGOCIO', '12345', NULL),
(5, 'Gimnacio Roberto', 'Gimnasioroberto@example.com', 'NEGOCIO', '12345', NULL),
(6, 'Taller mecani Laura', 'tallerlaura@example.com', 'NEGOCIO', '12345', NULL),
(7, 'Miguel Torres', 'miguel@example.com', 'CLIENTE', '12345', NULL),
(9, 'Pedro Gómez', 'pedro@example.com', 'CLIENTE', '12345', NULL),
(16, 'Jovany', 'jovapg@gmail.com', 'ADMIN', '12345', '3012408505'),
(17, 'mamadelamama', 'mama@example.com', 'CLIENTE', '12345', '3011111111'),
(18, 'qaprueba', 'qa@example.com', 'ADMIN', '12345', '3111111111');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_appointments_client` (`id_cliente`),
  ADD KEY `idx_appointments_business` (`id_negocio`);

--
-- Indices de la tabla `availability`
--
ALTER TABLE `availability`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_availability_business` (`id_negocio`);

--
-- Indices de la tabla `business`
--
ALTER TABLE `business`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `businesses`
--
ALTER TABLE `businesses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_business_user` (`id_usuario`);

--
-- Indices de la tabla `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `appointments`
--
ALTER TABLE `appointments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `availability`
--
ALTER TABLE `availability`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `business`
--
ALTER TABLE `business`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `businesses`
--
ALTER TABLE `businesses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `services`
--
ALTER TABLE `services`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`id_negocio`) REFERENCES `businesses` (`id`);

--
-- Filtros para la tabla `availability`
--
ALTER TABLE `availability`
  ADD CONSTRAINT `availability_ibfk_1` FOREIGN KEY (`id_negocio`) REFERENCES `businesses` (`id`);

--
-- Filtros para la tabla `businesses`
--
ALTER TABLE `businesses`
  ADD CONSTRAINT `businesses_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
