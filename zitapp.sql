-- Create the users table
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    tipo VARCHAR(20) NOT NULL
);

-- Create the businesses table
CREATE TABLE businesses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    categoría VARCHAR(50),
    descripción TEXT,
    dirección VARCHAR(200),
    imagen_url VARCHAR(255),
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES users(id)
);

-- Create the availability table
CREATE TABLE availability (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_negocio INT NOT NULL,
    día VARCHAR(20) NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    FOREIGN KEY (id_negocio) REFERENCES businesses(id)
);

-- Create the appointments table
CREATE TABLE appointments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT NOT NULL,
    id_negocio INT NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    estado VARCHAR(20) DEFAULT 'pendiente',
    FOREIGN KEY (id_cliente) REFERENCES users(id),
    FOREIGN KEY (id_negocio) REFERENCES businesses(id)
);

-- Add indexes for performance
CREATE INDEX idx_appointments_client ON appointments(id_cliente);
CREATE INDEX idx_appointments_business ON appointments(id_negocio);
CREATE INDEX idx_business_user ON businesses(id_usuario);
CREATE INDEX idx_availability_business ON availability(id_negocio);
