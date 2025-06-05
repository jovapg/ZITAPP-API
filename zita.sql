-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-06-2025 a las 19:43:26
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
  `estado` varchar(20) DEFAULT 'pendiente',
  `id_servicio` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `appointments`
--

INSERT INTO `appointments` (`id`, `id_cliente`, `id_negocio`, `fecha`, `hora`, `estado`, `id_servicio`) VALUES
(17, 7, 1, '2025-06-01', '09:00:00', 'PENDIENTE', 4),
(18, 9, 1, '2025-06-01', '10:30:00', 'CONFIRMADA', 5),
(19, 17, 2, '2025-06-02', '11:00:00', 'PENDIENTE', 9),
(20, 7, 2, '2025-06-02', '14:00:00', 'CANCELADA', 10),
(21, 9, 3, '2025-06-03', '15:30:00', 'FINALIZADA', 5),
(22, 17, 3, '2025-06-04', '16:00:00', 'CONFIRMADA', 8),
(23, 7, 4, '2025-06-05', '08:00:00', 'PENDIENTE', 7),
(24, 9, 4, '2025-06-05', '09:30:00', 'CONFIRMADA', 8),
(25, 17, 5, '2025-06-06', '13:00:00', 'PENDIENTE', 9),
(26, 7, 5, '2025-06-06', '14:30:00', 'CANCELADA', 10),
(27, 9, 6, '2025-06-07', '10:00:00', 'FINALIZADA', 11),
(28, 17, 6, '2025-06-07', '11:30:00', 'PENDIENTE', 12),
(29, 7, 1, '2025-06-08', '15:00:00', 'CONFIRMADA', 4),
(30, 9, 2, '2025-06-04', '14:30:00', 'PENDIENTE', 9),
(31, 17, 3, '2025-06-09', '09:00:00', 'FINALIZADA', 12),
(32, 7, 4, '2025-06-09', '10:30:00', 'CONFIRMADA', 7),
(33, 9, 5, '2025-06-10', '14:00:00', 'CANCELADA', 9),
(34, 17, 6, '2025-06-10', '15:30:00', 'CANCELADA', 12),
(38, 22, 12, '2025-06-04', '00:00:00', 'PENDIENTE', 21);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `availability`
--

CREATE TABLE `availability` (
  `id` int(11) NOT NULL,
  `dia` varchar(255) NOT NULL,
  `hora_fin` time(6) NOT NULL,
  `hora_inicio` time(6) NOT NULL,
  `business_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `availability`
--

INSERT INTO `availability` (`id`, `dia`, `hora_fin`, `hora_inicio`, `business_id`) VALUES
(1, 'Lunes', '16:00:00.000000', '14:30:00.000000', 1),
(2, 'Lunes', '17:00:00.000000', '13:00:00.000000', 1),
(3, 'Martes', '15:00:00.000000', '09:00:00.000000', 1),
(4, 'Miércoles', '14:00:00.000000', '10:00:00.000000', 1),
(5, 'Jueves', '12:30:00.000000', '08:30:00.000000', 1),
(6, 'Viernes', '13:00:00.000000', '09:00:00.000000', 1),
(7, 'Lunes', '16:00:00.000000', '08:00:00.000000', 2),
(8, 'Martes', '17:00:00.000000', '09:00:00.000000', 2),
(9, 'Miércoles', '12:00:00.000000', '08:00:00.000000', 2),
(10, 'Jueves', '18:00:00.000000', '13:00:00.000000', 2),
(11, 'Viernes', '16:00:00.000000', '10:00:00.000000', 2),
(12, 'Sábado', '13:00:00.000000', '09:00:00.000000', 2),
(13, 'Lunes', '15:00:00.000000', '07:00:00.000000', 3),
(14, 'Martes', '14:00:00.000000', '08:00:00.000000', 3),
(15, 'Miércoles', '13:00:00.000000', '09:00:00.000000', 3),
(16, 'Jueves', '16:00:00.000000', '10:00:00.000000', 3),
(17, 'Viernes', '15:00:00.000000', '09:00:00.000000', 3),
(18, 'Domingo', '14:00:00.000000', '10:00:00.000000', 3),
(19, 'Lunes', '12:00:00.000000', '08:00:00.000000', 4),
(20, 'Martes', '18:00:00.000000', '14:00:00.000000', 4),
(22, 'Jueves', '18:00:00.000000', '14:00:00.000000', 4),
(23, 'Viernes', '12:00:00.000000', '08:00:00.000000', 4),
(24, 'Sábado', '14:00:00.000000', '10:00:00.000000', 4),
(26, 'Martes', '14:00:00.000000', '10:00:00.000000', 5),
(27, 'Miércoles', '15:00:00.000000', '11:00:00.000000', 5),
(28, 'Jueves', '16:00:00.000000', '12:00:00.000000', 5),
(29, 'Viernes', '17:00:00.000000', '13:00:00.000000', 5),
(30, 'Sábado', '18:00:00.000000', '14:00:00.000000', 5),
(33, 'Martes', '15:00:00.000000', '14:00:00.000000', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `businesses`
--

CREATE TABLE `businesses` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `businesses`
--

INSERT INTO `businesses` (`id`, `nombre`, `imagen_url`, `id_usuario`, `categoria`, `descripcion`, `direccion`) VALUES
(1, 'Peluquería juanito', 'https://media.istockphoto.com/id/1497806504/es/foto/peluquer%C3%ADa-en-sal%C3%B3n-de-belleza-la-mujer-se-peina-en-el-sal%C3%B3n-de-belleza-moderno-estilista-seca.jpg?s=1024x1024&w=is&k=20&c=WuBXse8zPMp6wg8heu7V96uT3HMUJhwuwpSk8t3wZfs=', 2, 'BELLEZA', 'somos la peluqueria mas gay', '6.244381968590373, -75.55975949037125'),
(2, 'Clínica Dental Ana', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTv_vYxLTv-SujVdF7ncNrvPvy5GsV2pXPZ6w&s', 4, 'SALUD', 'Servicios dentales de alta calidad', '6.247843715524133, -75.56367957238065'),
(3, 'Gimnasio Roberto', 'https://blog.trainingym.com/hs-fs/hubfs/AdobeStock_174212531%20(1).jpeg?width=999&height=667&name=AdobeStock_174212531%20(1).jpeg', 5, 'FITNESS', 'Equipamiento moderno y entrenadores personales', '6.241886125854007, -75.56786002019788'),
(4, 'Taller Mecánico Laura', 'https://ovaciones.com/wp-content/uploads/2024/03/Daniel-Reza-2024-03-08T084225.822.png', 6, 'AUTOMOTRIZ', 'Reparación y mantenimiento de vehículos', '6.245307844703075, -75.57160940448486'),
(5, 'Centro de Masajes Carmen', 'https://www.flowww.es/hubfs/Q12023%20Marzo/Im%C3%A1genes%20blog/decoracion-negocios-wellness.webp', 1, 'BIENESTAR', 'Masajes terapéuticos y relajantes', '6.252065190092064, -75.56789402731395'),
(6, 'Peluquería Carlos', 'https://cdn1.treatwell.net/images/view/v2.i11964643.w720.h480.xDC84654A/', 3, 'BELLEZA', 'la mejor barberia de la ciudad', '6.23422139304263, -75.56946590657066'),
(7, 'Tienda Dana', 'https://ejemplo.com/logo.png', 1, 'Mascotas', 'Venta de productos', 'Calle 45'),
(9, 'Mi negocio', 'http://imagen.com/negocio.jpg', 18, 'Restaurante', 'Un lugar para comer delicioso', 'Calle 123'),
(10, 'Mi negocioc', 'http://imagen.com/negocio.jpg', 18, 'Restaurantec', 'Un lugar para comer delicioso', 'Calle 123'),
(12, 'Mi negocio', 'http://imagen.com/negocio.jpg', 1, 'Restaurante', 'Un lugar para comer delicioso', 'Calle 123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notifications`
--

CREATE TABLE `notifications` (
  `id` bigint(20) NOT NULL,
  `appointment_id` bigint(20) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) NOT NULL,
  `is_read` bit(1) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `recipient_id` bigint(20) DEFAULT NULL,
  `recipient_type` enum('ADMIN','CLIENTE','NEGOCIO') DEFAULT NULL,
  `sender_id` bigint(20) DEFAULT NULL,
  `sender_type` enum('ADMIN','CLIENTE','NEGOCIO') DEFAULT NULL,
  `type` enum('CITA_CANCELADA','CITA_CONFIRMADA','CITA_CREADA','CITA_FINALIZADA') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `notifications`
--

INSERT INTO `notifications` (`id`, `appointment_id`, `created_at`, `is_deleted`, `is_read`, `message`, `recipient_id`, `recipient_type`, `sender_id`, `sender_type`, `type`) VALUES
(1, 16, '2025-05-30 10:58:23.000000', b'0', b'1', 'Nueva cita creada con Pedro Gómez para el día 2025-05-31 a las 14:57.', 3, 'NEGOCIO', 9, 'CLIENTE', 'CITA_CREADA'),
(2, 35, '2025-06-04 10:23:03.000000', b'0', b'0', 'Nueva cita creada con test01 para el día 2025-06-05 a las 15:30.', 11, 'NEGOCIO', 22, 'CLIENTE', 'CITA_CREADA'),
(3, 36, '2025-06-04 10:29:15.000000', b'0', b'0', 'Nueva cita creada con test01 para el día 2025-06-05 a las 15:30.', 1, 'NEGOCIO', 22, 'CLIENTE', 'CITA_CREADA'),
(4, 36, '2025-06-04 12:25:34.000000', b'0', b'0', 'La cita con test01 para el día 2025-06-05 a las 15:30 fue confirmada.', 22, 'CLIENTE', 1, 'NEGOCIO', 'CITA_CONFIRMADA'),
(5, 36, '2025-06-04 12:26:11.000000', b'0', b'0', 'La cita con test01 para el día 2025-06-05 a las 15:30 fue finalizada.', 22, 'CLIENTE', 1, 'NEGOCIO', 'CITA_FINALIZADA'),
(6, 33, '2025-06-04 12:26:29.000000', b'0', b'0', 'La cita con Pedro Gómez para el día 2025-06-10 a las 14:00 fue cancelada.', 9, 'CLIENTE', 5, 'NEGOCIO', 'CITA_CANCELADA'),
(7, 33, '2025-06-04 12:26:29.000000', b'0', b'0', 'La cita con Pedro Gómez para el día 2025-06-10 a las 14:00 fue cancelada.', 5, 'NEGOCIO', 9, 'CLIENTE', 'CITA_CANCELADA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `services`
--

CREATE TABLE `services` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `business_id` bigint(20) NOT NULL,
  `duracion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `services`
--

INSERT INTO `services` (`id`, `descripcion`, `nombre`, `precio`, `business_id`, `duracion`) VALUES
(4, 'stridfdfg', 'juan', 35550, 1, 60),
(5, 'prueba1', 'prueba1', 20000, 1, 10),
(7, 'Corte clásico o moderno para hombres', 'Corte masculino', 18000, 1, 10),
(8, 'Coloración capilar permanente o semipermanente', 'Tinte de cabello', 45000, 1, 30),
(9, 'Eliminación de placa y sarro', 'Limpieza dental', 60000, 2, 90),
(10, 'Diagnóstico y aplicación de brackets', 'Ortodoncia', 2500000, 2, 30),
(11, 'Entrenamiento de alta intensidad en grupo', 'Clase funcional', 25000, 3, 30),
(12, 'Rutina y seguimiento con entrenador personal', 'Asesoría personalizada', 80000, 3, 45),
(13, 'Revisión general y cambio de lubricante', 'Cambio de aceite', 70000, 4, 23),
(14, 'Diagnóstico completo del vehículo', 'Revisión general', 100000, 4, 45),
(15, 'Terapia para aliviar tensiones musculares', 'Masaje relajante', 90000, 5, 60),
(16, 'Relajación profunda con piedras volcánicas', 'Masaje con piedras calientes', 120000, 5, 90),
(17, 'Corte, barba y cejas', 'Barbería completa', 25000, 6, 34),
(21, 'Masaje completo de cuerpo2', 'Masaje relajante2', 45000, 12, 60);

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
  `telefono` varchar(255) DEFAULT NULL,
  `edad` smallint(6) NOT NULL,
  `imagen_perfil` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `nombre`, `email`, `tipo`, `contrasena`, `telefono`, `edad`, `imagen_perfil`) VALUES
(1, 'Juan Actualizado', 'juan.actualizado@example.com', 'NEGOCIO', 'nuevaContrasena123', '3112233445', 30, 'http://example.com/nuevaImagen.jpg'),
(2, 'peluqueria juanito', 'peluqueriajuanito@example.com', 'NEGOCIO', '12345', '1111111', 40, 'https://randomuser.me/api/portraits/lego/2.jpg'),
(3, 'Carlos peluqueria', 'carlospeluqueria@example.com', 'NEGOCIO', '12345', '345', 20, 'https://randomuser.me/api/portraits/lego/3.jpg'),
(4, 'clínica dental Ana', 'clinicadentalana@example.com', 'NEGOCIO', '12345', '4353', 30, 'https://randomuser.me/api/portraits/lego/4.jpg'),
(5, 'Gimnacio Roberto', 'Gimnasioroberto@example.com', 'NEGOCIO', '12345', '4534', 34, 'https://randomuser.me/api/portraits/lego/5.jpg'),
(6, 'Taller mecani Laura', 'tallerlaura@example.com', 'NEGOCIO', '12345', '345', 27, 'https://randomuser.me/api/portraits/lego/6.jpg'),
(7, 'Miguel Torres', 'miguel@example.com', 'CLIENTE', '12345', '3188120099', 45, 'https://randomuser.me/api/portraits/men/45.jpg'),
(9, 'Pedro Gómez', 'pedro@example.com', 'CLIENTE', '12345', '345', 20, 'https://randomuser.me/api/portraits/men/12.jpg'),
(17, 'mamadelamama', 'mama@example.com', 'CLIENTE', '12345', '3011111111', 25, 'https://randomuser.me/api/portraits/women/32.jpg'),
(18, 'qaprueba', 'qa@example.com', 'NEGOCIO', '12345', '3111111111', 18, 'https://randomuser.me/api/portraits/women/1.jpg'),
(19, 'JoseRamos', 'joseramosgomez71@gmail.com', 'ADMIN', 'Joseramos2004', '3188122200', 18, 'https://randomuser.me/api/portraits/men/2.jpg'),
(21, 'Juan Actualizaado', 'juan.actualizadao@example.com', 'CLIENTE', 'nuevaContrasena123', '311223345', 30, 'http://example.com/nuevaImagen.jpg'),
(22, 'test01', 'test01@yopmail.com', 'CLIENTE', '12345', '322222', 20, 'https://play-lh.googleusercontent.com/h8q9KZ_BGb9cxsIQ4yUUFF_ZH__NepjNatsaeSuMrm0Wj0guaK6cRzxPmCiBbp1h4Sw'),
(23, 'test02', 'test02@yopmail.com', 'NEGOCIO', '12345', '3222242', 20, 'https://play-lh.googleusercontent.com/h8q9KZ_BGb9cxsIQ4yUUFF_ZH__NepjNatsaeSuMrm0Wj0guaK6cRzxPmCiBbp1h4Sw'),
(24, 'Juan Pérez', 'juanperez@example.com', 'CLIENTE', 'password123', '3001234567', 30, 'https://example.com/juan.jpg');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_appointments_client` (`id_cliente`),
  ADD KEY `idx_appointments_business` (`id_negocio`),
  ADD KEY `FK7807p7lpi5j4qmk3okmfdx5o3` (`id_servicio`);

--
-- Indices de la tabla `availability`
--
ALTER TABLE `availability`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `businesses`
--
ALTER TABLE `businesses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_business_user` (`id_usuario`);

--
-- Indices de la tabla `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT de la tabla `availability`
--
ALTER TABLE `availability`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de la tabla `businesses`
--
ALTER TABLE `businesses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `services`
--
ALTER TABLE `services`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `FK7807p7lpi5j4qmk3okmfdx5o3` FOREIGN KEY (`id_servicio`) REFERENCES `services` (`id`),
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`id_negocio`) REFERENCES `businesses` (`id`);

--
-- Filtros para la tabla `businesses`
--
ALTER TABLE `businesses`
  ADD CONSTRAINT `businesses_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
