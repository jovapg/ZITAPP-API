package com.example.Zitapp.Controladores;

import com.example.Zitapp.Modelos.Users;
import com.example.Zitapp.Servicios.UsersServicios;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Controlador REST para gestionar operaciones de usuarios
 */
@RestController
@RequestMapping("/api/users")

public class UsersControlador {

    private static final Logger logger = LoggerFactory.getLogger(UsersControlador.class);

    @Autowired
    private UsersServicios usersServicio;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Crea un nuevo usuario
     * @param user datos del usuario a crear
     * @return ResponseEntity con el usuario creado o mensaje de error
     */
    @PostMapping       //   http://localhost:8081/api/users
    public ResponseEntity<?> crearUsuario(@RequestBody Users user) {
        try {
            logger.info("Intento de creación de usuario: {}", user.getEmail());
            Users nuevoUsuario = usersServicio.crearUsuario(user);
            logger.info("Usuario creado exitosamente: {}", nuevoUsuario.getId());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(nuevoUsuario);
        } catch (IllegalArgumentException e) {
            // Para errores de validación específicos
            logger.warn("Error de validación al crear usuario: {}", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());

        } catch (DataIntegrityViolationException e) {
            // Captura errores de integridad de datos (como duplicados)
            logger.warn("Error de integridad de datos al crear usuario: {}", e.getMessage());

            // Verifica si es un error de email duplicado
            if (e.getMessage() != null && e.getMessage().contains("Duplicate entry") &&
                    e.getMessage().contains("users.email")) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(Collections.singletonMap("error",
                                "El email ya está registrado. Por favor utilice otro email."));
            }

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("error",
                            "Violación de restricción en la base de datos: " + e.getMessage()));
        } catch (Exception e) {
            // Para errores inesperados
            logger.error("Error inesperado al crear usuario", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los usuarios
     * @return ResponseEntity con la lista de usuarios o error
     */
    @GetMapping  // http://localhost:8081/api/users
    public ResponseEntity<?> obtenerUsuarios() {
        try {
            logger.info("Solicitud para obtener todos los usuarios");
            List<Users> usuarios = usersServicio.obtenerTodos();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            logger.error("Error al obtener todos los usuarios", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error al obtener usuarios: " + e.getMessage()));
        }
    }

    /**
     * Obtiene un usuario por su ID
     * @param id identificador del usuario
     * @return ResponseEntity con el usuario encontrado o error
     */
    @GetMapping("/{id}")   //   http://localhost:8081/api/users/{id}
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id) {
        try {
            logger.info("Solicitud para obtener usuario con ID: {}", id);

            // Se maneja el Optional de forma más explícita
            var usuarioOptional = usersServicio.obtenerPorId(id);

            if (usuarioOptional.isPresent()) {
                Users usuario = usuarioOptional.get();
                logger.info("Usuario encontrado: {}", usuario.getId());
                return ResponseEntity.ok(usuario);
            } else {
                logger.warn("Usuario con ID {} no encontrado", id);
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("error", "Usuario no encontrado con ID: " + id));
            }
        } catch (Exception e) {
            logger.error("Error al obtener usuario con ID: {}", id, e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error al procesar la solicitud: " + e.getMessage()));
        }
    }

    /**
     * Obtiene todas las citas asociadas a un usuario sin incluir los datos del cliente
     * @param id identificador del usuario
     * @return ResponseEntity con la lista de citas o error
     */
    @GetMapping("/{id}/appointments")
    public ResponseEntity<?> obtenerCitasPorUsuario(@PathVariable Long id) {
        try {
            logger.info("Solicitud para obtener citas del usuario con ID: {}", id);

            // Primero verificamos si el usuario existe
            if (!usersServicio.existePorId(id)) {
                logger.warn("Intento de obtener citas de un usuario que no existe: {}", id);
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("error", "No se encontró el usuario con ID: " + id));
            }

            // Obtenemos las citas del usuario
            List<?> citasCompletas = usersServicio.obtenerCitasPorUsuario(id);

            // Si no hay citas, devolvemos un mensaje informativo
            if (citasCompletas.isEmpty()) {
                logger.info("El usuario con ID {} no tiene citas registradas", id);
                return ResponseEntity.ok(Collections.singletonMap("mensaje", "El usuario no tiene citas registradas"));
            }

            // Filtrar los datos del cliente de cada cita
            List<Map<String, Object>> citasFiltradas = new ArrayList<>();

            for (Object citaObj : citasCompletas) {
                // Convertimos el objeto a un mapa para manipularlo fácilmente
                Map<String, Object> citaMap = objectMapper.convertValue(citaObj, new TypeReference<Map<String, Object>>() {});

                // Eliminamos el campo 'client' del mapa
                citaMap.remove("client");

                // Añadimos la cita filtrada a la lista
                citasFiltradas.add(citaMap);
            }

            logger.info("Se encontraron {} citas para el usuario con ID: {}", citasFiltradas.size(), id);
            return ResponseEntity.ok(citasFiltradas);

        } catch (Exception e) {
            logger.error("Error al obtener citas del usuario con ID: {}", id, e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error al procesar la solicitud: " + e.getMessage()));
        }
    }

    /**
     * Actualiza un usuario existente
     * @param id identificador del usuario a actualizar
     * @param datosActualizados nuevos datos del usuario
     * @return ResponseEntity con el usuario actualizado o error
     */

    @PutMapping("/{id}")   //http://localhost:8081/api/users/{id}
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody Users datosActualizados) {
        try {
            logger.info("Intento de actualización de usuario con ID: {}", id);

            // Se maneja el Optional de forma diferente para evitar problemas de tipo
            var resultado = usersServicio.actualizarUsuario(id, datosActualizados);

            if (resultado.isPresent()) {
                Users usuarioActualizado = resultado.get();
                logger.info("Usuario actualizado exitosamente: {}", id);
                return ResponseEntity.ok(usuarioActualizado);
            } else {
                logger.warn("No se pudo actualizar usuario con ID {} (no encontrado)", id);
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("error", "Usuario no encontrado con ID: " + id));
            }
        } catch (IllegalArgumentException e) {
            // Para errores de validación específicos
            logger.warn("Error de validación al actualizar usuario: {}", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            logger.error("Error al actualizar usuario con ID: {}", id, e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error al procesar la solicitud: " + e.getMessage()));
        }
    }

    /**
     * Elimina un usuario por su ID
     * @param id identificador del usuario a eliminar
     * @return ResponseEntity con confirmación o error
     */
    @DeleteMapping("/{id}")   //    http://localhost:8081/api/users/{id}
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            logger.info("Intento de eliminación de usuario con ID: {}", id);
            boolean eliminado = usersServicio.eliminarUsuario(id);
            if (eliminado) {
                logger.info("Usuario eliminado exitosamente: {}", id);
                return ResponseEntity
                        .ok(Collections.singletonMap("mensaje", "Usuario eliminado exitosamente"));
            } else {
                logger.warn("No se pudo eliminar usuario con ID {} (no encontrado)", id);
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("error", "Usuario no encontrado con ID: " + id));
            }
        } catch (Exception e) {
            logger.error("Error al eliminar usuario con ID: {}", id, e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error al procesar la solicitud: " + e.getMessage()));
        }
    }

    /**
     * Autentica un usuario mediante email y contraseña
     * @param credenciales mapa que contiene email y contraseña
     * @return ResponseEntity con datos del usuario autenticado o mensaje de error
     */
    @PostMapping("/login")    //   http://localhost:8081/api/users/login
    public ResponseEntity<?> loginUsuario(@RequestBody Map<String, String> credenciales) {
        //JSON DE LA PETICION
        // {
        //    "email": "leo@leo.com",
        //     "contrasena": "12345"
        //}

        try {
            logger.info("Intento de login con email: {}", credenciales.get("email"));

            // Validar que se proporcionaron las credenciales necesarias
            if (credenciales.get("email") == null || credenciales.get("contrasena") == null) {
                logger.warn("Intento de login sin credenciales completas");
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "Se requieren email y contraseña"));
            }

            // Intentar autenticar al usuario
            Users usuarioAutenticado = usersServicio.autenticarUsuario(
                    credenciales.get("email"),
                    credenciales.get("contrasena")
            );

            if (usuarioAutenticado != null) {
                logger.info("Login exitoso para el usuario: {}", usuarioAutenticado.getId());

                // Crear respuesta con datos básicos (evitando enviar la contraseña)
                Map<String, Object> respuesta = new HashMap<>();
                respuesta.put("id", usuarioAutenticado.getId());
                respuesta.put("email", usuarioAutenticado.getEmail());
                respuesta.put("nombre", usuarioAutenticado.getNombre());
                respuesta.put("telefono", usuarioAutenticado.getTelefono());
                respuesta.put("tipo", usuarioAutenticado.getTipo());
                respuesta.put("mensaje", "Login exitoso");

                return ResponseEntity.ok(respuesta);
            } else {
                logger.warn("Credenciales inválidas para email: {}", credenciales.get("email"));
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("error", "Credenciales inválidas"));
            }
        } catch (Exception e) {
            logger.error("Error en el proceso de login", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error al procesar la solicitud: " + e.getMessage()));
        }

    }


}