-- Insertar datos en la tabla users
INSERT INTO users (nombre, email, contraseña, tipo) VALUES
('Juan Pérez', 'juan@example.com', 'password123', 'cliente'),
('María López', 'maria@example.com', 'secure456', 'cliente'),
('Carlos Rodríguez', 'carlos@example.com', 'pass789', 'negocio'),
('Ana Martínez', 'ana@example.com', 'ana1234', 'negocio'),
('Roberto Sánchez', 'roberto@example.com', 'rob567', 'negocio'),
('Sofía Fernández', 'sofia@example.com', 'sof890', 'cliente'),
('Miguel Torres', 'miguel@example.com', 'mig123', 'cliente'),
('Laura Ramírez', 'laura@example.com', 'lau456', 'negocio'),
('Pedro Gómez', 'pedro@example.com', 'ped789', 'cliente'),
('Carmen Díaz', 'carmen@example.com', 'car012', 'negocio');

-- Insertar datos en la tabla businesses
INSERT INTO businesses (nombre, categoría, descripción, dirección, imagen_url, id_usuario) VALUES
('Peluquería Carlos', 'Belleza', 'Peluquería profesional para caballeros y damas', 'Calle Principal 123', 'https://example.com/images/peluqueria.jpg', 3),
('Clínica Dental Ana', 'Salud', 'Servicios dentales de alta calidad', 'Avenida Central 456', 'https://example.com/images/dental.jpg', 4),
('Gimnasio Roberto', 'Fitness', 'Equipamiento moderno y entrenadores personales', 'Plaza Mayor 789', 'https://example.com/images/gym.jpg', 5),
('Taller Mecánico Laura', 'Automotriz', 'Reparación y mantenimiento de vehículos', 'Carretera Norte 101', 'https://example.com/images/taller.jpg', 8),
('Centro de Masajes Carmen', 'Bienestar', 'Masajes terapéuticos y relajantes', 'Calle Secundaria 202', 'https://example.com/images/masajes.jpg', 10);

-- Insertar datos en la tabla availability
INSERT INTO availability (id_negocio, día, hora_inicio, hora_fin) VALUES
(1, 'Lunes', '09:00:00', '18:00:00'),
(1, 'Martes', '09:00:00', '18:00:00'),
(1, 'Miércoles', '09:00:00', '18:00:00'),
(1, 'Jueves', '09:00:00', '18:00:00'),
(1, 'Viernes', '09:00:00', '17:00:00'),
(2, 'Lunes', '08:00:00', '16:00:00'),
(2, 'Martes', '08:00:00', '16:00:00'),
(2, 'Miércoles', '08:00:00', '16:00:00'),
(2, 'Jueves', '08:00:00', '16:00:00'),
(2, 'Viernes', '08:00:00', '14:00:00'),
(3, 'Lunes', '06:00:00', '22:00:00'),
(3, 'Martes', '06:00:00', '22:00:00'),
(3, 'Miércoles', '06:00:00', '22:00:00'),
(3, 'Jueves', '06:00:00', '22:00:00'),
(3, 'Viernes', '06:00:00', '22:00:00'),
(3, 'Sábado', '08:00:00', '20:00:00'),
(3, 'Domingo', '08:00:00', '14:00:00'),
(4, 'Lunes', '08:00:00', '18:00:00'),
(4, 'Martes', '08:00:00', '18:00:00'),
(4, 'Miércoles', '08:00:00', '18:00:00'),
(4, 'Jueves', '08:00:00', '18:00:00'),
(4, 'Viernes', '08:00:00', '18:00:00'),
(4, 'Sábado', '09:00:00', '13:00:00'),
(5, 'Martes', '10:00:00', '20:00:00'),
(5, 'Miércoles', '10:00:00', '20:00:00'),
(5, 'Jueves', '10:00:00', '20:00:00'),
(5, 'Viernes', '10:00:00', '20:00:00'),
(5, 'Sábado', '10:00:00', '16:00:00');

-- Insertar datos en la tabla appointments
INSERT INTO appointments (id_cliente, id_negocio, fecha, hora, estado) VALUES
(1, 1, '2025-05-10', '10:30:00', 'confirmado'),
(2, 2, '2025-05-11', '09:15:00', 'confirmado'),
(6, 3, '2025-05-12', '18:00:00', 'pendiente'),
(7, 4, '2025-05-13', '14:30:00', 'confirmado'),
(9, 5, '2025-05-14', '12:00:00', 'pendiente'),
(1, 3, '2025-05-15', '10:00:00', 'cancelado'),
(2, 5, '2025-05-16', '16:30:00', 'confirmado'),
(6, 2, '2025-05-17', '11:45:00', 'pendiente'),
(7, 1, '2025-05-18', '15:00:00', 'confirmado'),
(9, 4, '2025-05-19', '09:30:00', 'confirmado'),
(1, 5, '2025-05-20', '17:15:00', 'pendiente'),
(2, 3, '2025-05-21', '08:00:00', 'confirmado'),
(6, 4, '2025-05-22', '13:30:00', 'cancelado'),
(7, 2, '2025-05-23', '10:45:00', 'pendiente'),
(9, 1, '2025-05-24', '16:00:00', 'confirmado');
