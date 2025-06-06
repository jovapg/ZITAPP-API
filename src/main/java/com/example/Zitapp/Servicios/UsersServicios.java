// src/main/java/com/example/Zitapp/Servicios/UsersServicios.java
package com.example.Zitapp.Servicios;

import com.example.Zitapp.Modelos.Appointments;
import com.example.Zitapp.Modelos.Users;
import com.example.Zitapp.Repositorios.AppointmentsRepositorio;
import com.example.Zitapp.Repositorios.UsersRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para manejar la lógica de negocio relacionada con usuarios.
 */
@Service
public class UsersServicios {

    @Autowired
    private UsersRepositorio usersRepository;

    @Autowired
    private AppointmentsRepositorio appointmentsRepositorio;

    // Ya no se inyecta BusinessRepositorio aquí

    /**
     * Crea un nuevo usuario validando que no existan duplicados por email, nombre o teléfono.
     * @param user objeto Users con los datos del nuevo usuario
     * @return usuario creado y guardado en la base de datos
     * @throws IllegalArgumentException si el email, nombre o teléfono ya existen
     */
    @Transactional
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

    /**
     * Obtiene todos los usuarios registrados.
     * @return lista de usuarios
     */
    @Transactional(readOnly = true)
    public List<Users> obtenerTodos() {
        return usersRepository.findAll();
    }

    /**
     * Busca un usuario por su ID.
     * @param id identificador único del usuario
     * @return Optional con el usuario si se encuentra
     */
    @Transactional(readOnly = true)
    public Optional<Users> obtenerPorId(Long id) {
        return usersRepository.findById(id);
    }

    /**
     * Actualiza los datos de un usuario existente.
     * @param id ID del usuario a actualizar
     * @param datosActualizados objeto con los nuevos datos del usuario
     * @return Optional con el usuario actualizado si se encontró
     */
    @Transactional
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

    /**
     * Elimina un usuario por su ID.
     * @param id ID del usuario a eliminar
     * @return true si se eliminó con éxito, false si no se encontró el usuario
     */
    @Transactional
    public boolean eliminarUsuario(Long id) {
        return usersRepository.findById(id).map(usuario -> {
            usersRepository.delete(usuario);
            return true;
        }).orElse(false);
    }

    /**
     * Verifica si existe un usuario con un ID dado.
     * @param id ID a verificar
     * @return true si existe, false en caso contrario
     */
    @Transactional(readOnly = true)
    public boolean existePorId(Long id) {
        return usersRepository.existsById(id);
    }

    /**
     * Obtiene las citas asociadas a un usuario.
     * @param userId ID del usuario
     * @return lista de citas del usuario
     */
    @Transactional(readOnly = true)
    public List<Appointments> obtenerCitasPorUsuario(Long userId) {
        return appointmentsRepositorio.findByClientIdWithService(userId);
    }

    /**
     * Autentica un usuario verificando email y contraseña.
     * MODIFICADO: Ya no intenta cargar Business aquí. El controlador se encarga de eso.
     * @param email correo electrónico del usuario
     * @param contrasena contraseña proporcionada
     * @return usuario autenticado o null si falla autenticación
     * @throws IllegalArgumentException si email o contraseña son nulos o vacíos
     */
    @Transactional(readOnly = true) // El @Transactional es para la operación de búsqueda, no para cargar relaciones Business aquí.
    public Users autenticarUsuario(String email, String contrasena) {
        if (email == null || contrasena == null || email.trim().isEmpty() || contrasena.trim().isEmpty()) {
            throw new IllegalArgumentException("Email y contraseña son requeridos");
        }

        Optional<Users> usuarioOptional = usersRepository.findByEmail(email);
        if (usuarioOptional.isPresent()) {
            Users usuario = usuarioOptional.get();
            if (verificarContrasena(contrasena, usuario.getContrasena())) {
                return usuario; // Retorna el usuario autenticado
            }
        }
        return null; // Credenciales inválidas o usuario no encontrado
    }

    /**
     * Verifica si la contraseña ingresada coincide con la almacenada.
     * @param contrasenaIngresada contraseña recibida
     * @param contrasenaAlmacenada contraseña almacenada
     * @return true si coinciden, false en caso contrario
     */
    private boolean verificarContrasena(String contrasenaIngresada, String contrasenaAlmacenada) {
        return contrasenaAlmacenada != null && contrasenaAlmacenada.equals(contrasenaIngresada);
    }
}