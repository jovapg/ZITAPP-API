package com.example.Zitapp.Repositorios;

import com.example.Zitapp.Modelos.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepositorio  extends  JpaRepository<Users, Long>{
    /**
     * Encuentra un usuario por su email
     * @param email el email del usuario a buscar
     * @return Optional con el usuario si existe
     */
    Optional<Users> findByEmail(String email);
}



