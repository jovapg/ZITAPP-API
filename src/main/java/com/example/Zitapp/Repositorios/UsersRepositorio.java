package com.example.Zitapp.Repositorios;

import com.example.Zitapp.Modelos.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad Users.
 * Proporciona métodos para realizar operaciones CRUD y consultas personalizadas.
 */
public interface UsersRepositorio extends JpaRepository<Users, Long> {

    /**
     * Busca un usuario por su email.
     * @param email email del usuario a buscar.
     * @return Optional con el usuario si se encuentra, o vacío si no existe.
     */
    Optional<Users> findByEmail(String email);

    /**
     * Busca un usuario por su nombre.
     * @param nombre nombre del usuario a buscar.
     * @return Optional con el usuario si se encuentra, o vacío si no existe.
     */
    Optional<Users> findByNombre(String nombre);

    /**
     * Busca un usuario por su teléfono.
     * @param telefono teléfono del usuario a buscar.
     * @return Optional con el usuario si se encuentra, o vacío si no existe.
     */
    Optional<Users> findByTelefono(String telefono);

}
