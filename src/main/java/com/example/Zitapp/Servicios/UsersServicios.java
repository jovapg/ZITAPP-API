package com.example.Zitapp.Servicios;


import com.example.Zitapp.Modelos.Appointments;
import com.example.Zitapp.Modelos.Users;
import com.example.Zitapp.Repositorios.AppointmentsRepositorio;
import com.example.Zitapp.Repositorios.UsersRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServicios {

   @Autowired
   private UsersRepositorio usersRepository;

   @Autowired
   private AppointmentsRepositorio appointmentsRepositorio;

    // Crear usuario
    public Users crearUsuario(Users user) {
        if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado.");
        }

        if (usersRepository.findByNombre(user.getNombre()).isPresent()) {
            throw new IllegalArgumentException("El nombre ya está en uso.");
        }

        if (usersRepository.findByTelefono(user.getTelefono()).isPresent()) {
            throw new IllegalArgumentException("El teléfono ya está registrado.");
        }

        return usersRepository.save(user);
    }


    // Obtener todos los usuarios
    public List<Users> obtenerTodos() {
        return usersRepository.findAll();
    }

    // Obtener un usuario por ID
    public Optional<Users> obtenerPorId(Long id) {
        return usersRepository.findById(id);
    }

    // Actualizar un usuario
    public Optional<Users> actualizarUsuario(Long id, Users datosActualizados) {
        return usersRepository.findById(id).map(usuario -> {
            usuario.setNombre(datosActualizados.getNombre());
            usuario.setEmail(datosActualizados.getEmail());
            usuario.setTelefono(datosActualizados.getTelefono());
            usuario.setContrasena(datosActualizados.getContrasena());
            usuario.setTipo(datosActualizados.getTipo());
            usuario.setEdad(datosActualizados.getEdad());
            usuario.setImagenPerfil(datosActualizados.getImagenPerfil());
            return usersRepository.save(usuario);
        });
    }

    // Eliminar un usuario
    public boolean eliminarUsuario(Long id) {
        return usersRepository.findById(id).map(usuario -> {
            usersRepository.delete(usuario);
            return true;
        }).orElse(false);
    }

    public boolean existePorId(Long id) {
        return usersRepository.existsById(id);
    }

    public List<Appointments> obtenerCitasPorUsuario(Long userId) {
        // Asumiendo que tienes un repositorio para las citas
        return appointmentsRepositorio.findByClientId(userId);
    }

    /**
     * Autentica un usuario mediante email y contraseña
     * @param email dirección de correo electrónico del usuario
     * @param contrasena contraseña del usuario
     * @return Usuario autenticado o null si la autenticación falla
     */
    public Users autenticarUsuario(String email, String contrasena) {
        if (email == null || contrasena == null || email.trim().isEmpty() || contrasena.trim().isEmpty()) {
            throw new IllegalArgumentException("Email y contraseña son requeridos");
        }

        // Buscar usuario por email
        Optional<Users> usuarioOptional = usersRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Users usuario = usuarioOptional.get();

            // Verificar la contraseña
            if (verificarContrasena(contrasena, usuario.getContrasena())) {
                return usuario;
            }
        }

        // Si el usuario no existe o la contraseña es incorrecta, retornar null
        return null;
    }

    /**
     * Verifica si la contraseña proporcionada coincide con la almacenada
     * Nota: Esta es una implementación básica. En un entorno de producción,
     * se debe utilizar un algoritmo de hash seguro como BCrypt.
     *
     * @param contrasenaIngresada contraseña proporcionada por el usuario
     * @param contrasenaAlmacenada contraseña almacenada en la base de datos
     * @return true si las contraseñas coinciden, false en caso contrario
     */
    private boolean verificarContrasena(String contrasenaIngresada, String contrasenaAlmacenada) {
        // En un escenario real, aquí deberías usar BCrypt o similar para comparar
        // return BCrypt.checkpw(contrasenaIngresada, contrasenaAlmacenada);

        // Implementación básica para ejemplo (SOLO PARA DESARROLLO)
        return contrasenaAlmacenada != null && contrasenaAlmacenada.equals(contrasenaIngresada);
    }
}
