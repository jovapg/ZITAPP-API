package com.example.Zitapp.Servicios;


import com.example.Zitapp.Modelos.Users;
import com.example.Zitapp.Repositorios.UsersRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServicios {

   @Autowired
   private UsersRepositorio usersRepository;

    // Crear usuario
    public Users crearUsuario(Users user) {
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




}
