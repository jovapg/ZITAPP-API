package com.example.Zitapp.Controladores;

import com.example.Zitapp.Modelos.Users;
import com.example.Zitapp.Servicios.UsersServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")

public class UsersControlador {

    @Autowired
    private UsersServicios usersServicio;

    // Crear usuario

    @PostMapping       //	http://localhost:8081/api/users
    public ResponseEntity<Users> crearUsuario(@RequestBody Users user) {
        Users nuevoUsuario = usersServicio.crearUsuario(user);
        return ResponseEntity.ok(nuevoUsuario);

        //se ingresan los datos en postman para probar
        //{
        //  "nombre": "Juan Pérez",
        //  "telefono": "3001234567",
        //  "email": "juan@example.com",
        //  "contrasena": "123456",
        //  "tipo": "CLIENTE" nota:  el campo tipo tenga solo los valores válidos: "CLIENTE" o "NEGOCIO"
        //}
    }

    // Obtener todos los usuarios
    @GetMapping  // http://localhost:8081/api/users
    public ResponseEntity<List<Users>> obtenerUsuarios() {
        return ResponseEntity.ok(usersServicio.obtenerTodos());
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")   //	http://localhost:8081/api/users/{id}
    public ResponseEntity<Users> obtenerUsuarioPorId(@PathVariable Long id) {
        return usersServicio.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar usuario
    @PutMapping("/{id}")   //http://localhost:8081/api/users/{id}
    public ResponseEntity<Users> actualizarUsuario(@PathVariable Long id, @RequestBody Users datosActualizados) {
        return usersServicio.actualizarUsuario(id, datosActualizados)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());


        //se ingresan los datos en postman para probar
        // {
        //  "nombre": "JovanyPérez",
        //  "telefono": "30124085005",
        //  "email": "jovany@example.com",
        //  "contrasena": "123456",
        //  "tipo": "CLIENTE"
        //}
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")   //	http://localhost:8081/api/users/{id}
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usersServicio.eliminarUsuario(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
