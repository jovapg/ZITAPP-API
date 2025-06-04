package com.example.Zitapp.Controladores;

import com.example.Zitapp.DTO.LoginDTO;
import com.example.Zitapp.DTO.UsersDTO;
import com.example.Zitapp.Modelos.Appointments;
import com.example.Zitapp.Modelos.Users;
import com.example.Zitapp.Servicios.UsersServicios;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controlador REST para manejar operaciones relacionadas con usuarios.
 * Permite crear, obtener, actualizar, eliminar usuarios, autenticar y obtener sus citas.
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "API para gestionar usuarios")
public class UsersControlador {

    @Autowired
    private UsersServicios usersServicios;

    /**
     * Convierte un DTO de usuario a la entidad Users.
     * @param dto DTO con datos de usuario
     * @return entidad Users
     */
    private Users convertirADominio(UsersDTO dto) {
        Users user = new Users();
        user.setId(dto.getId());
        user.setNombre(dto.getNombre());
        user.setEmail(dto.getEmail());
        user.setTelefono(dto.getTelefono());
        user.setContrasena(dto.getContrasena());

        if (dto.getTipo() != null) {
            try {
                user.setTipo(Users.TipoUsuario.valueOf(dto.getTipo().toUpperCase()));
            } catch (IllegalArgumentException e) {
                user.setTipo(null); // Manejo simple, se puede mejorar
            }
        }

        if (dto.getEdad() != null) {
            user.setEdad(dto.getEdad().shortValue());
        }

        user.setImagenPerfil(dto.getImagenPerfil());

        return user;
    }

    /**
     * Convierte la entidad Users a su DTO correspondiente.
     * @param user entidad Users
     * @return DTO con datos de usuario
     */
    private UsersDTO convertirADTO(Users user) {
        UsersDTO dto = new UsersDTO();
        dto.setId(user.getId());
        dto.setNombre(user.getNombre());
        dto.setEmail(user.getEmail());
        dto.setTelefono(user.getTelefono());
        dto.setContrasena(user.getContrasena());
        dto.setTipo(user.getTipo() != null ? user.getTipo().name() : null);
        dto.setEdad((int) user.getEdad());
        dto.setImagenPerfil(user.getImagenPerfil());
        return dto;
    }

    /**
     * Crea un nuevo usuario validando los datos y evitando duplicados.
     *
     * @param nuevoUsuarioDTO DTO con datos del nuevo usuario
     * @return usuario creado con código HTTP 201, o error 409 si ya existe
     */
    @Operation(summary = "Crear un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "409", description = "Conflicto: Email, nombre o teléfono ya registrados"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsersDTO nuevoUsuarioDTO) {
        try {
            Users user = convertirADominio(nuevoUsuarioDTO);
            Users creado = usersServicios.crearUsuario(user);
            return new ResponseEntity<>(convertirADTO(creado), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Obtiene todos los usuarios registrados.
     *
     * @return lista de usuarios
     */
    @Operation(summary = "Obtener todos los usuarios")
    @GetMapping
    public ResponseEntity<List<UsersDTO>> obtenerTodos() {
        List<Users> usuarios = usersServicios.obtenerTodos();
        List<UsersDTO> dtos = usuarios.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id ID del usuario
     * @return usuario encontrado o error 404 si no existe
     */
    @Operation(summary = "Obtener un usuario por ID")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<Users> usuario = usersServicios.obtenerPorId(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(convertirADTO(usuario.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    /**
     * Actualiza un usuario existente con los nuevos datos.
     *
     * @param id ID del usuario a actualizar
     * @param dtoActualizado DTO con los datos nuevos
     * @return usuario actualizado o error 404 si no existe
     */
    @Operation(summary = "Actualizar usuario por ID")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado para actualizar")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsersDTO dtoActualizado) {
        Users userActualizado = convertirADominio(dtoActualizado);
        Optional<Users> usuarioOpt = usersServicios.actualizarUsuario(id, userActualizado);
        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok(convertirADTO(usuarioOpt.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado para actualizar");
        }
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id ID del usuario
     * @return mensaje de éxito o error 404 si no se encontró
     */
    @Operation(summary = "Eliminar usuario por ID")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado para eliminar")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usersServicios.eliminarUsuario(id);
        if (eliminado) {
            return ResponseEntity.ok().body("Usuario eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado para eliminar");
        }
    }

    /**
     * Autentica un usuario con email y contraseña.
     *
     * @param loginDto DTO con email y contraseña
     * @return usuario autenticado o error 401 si falla
     */
    @Operation(summary = "Autenticar usuario (login)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario autenticado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDto) {
        // Validar campos requeridos con anotaciones @Valid y @NotBlank en LoginDTO
        Users user = usersServicios.autenticarUsuario(loginDto.getEmail(), loginDto.getContrasena());
        if (user != null) {
            return ResponseEntity.ok(convertirADTO(user));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }


    /**
     * Obtiene las citas asociadas a un usuario.
     *
     * @param id ID del usuario
     * @return lista de citas o error 404 si no existe usuario
     */
    @Operation(summary = "Obtener citas por usuario")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @GetMapping("/{id}/appointments")
    public ResponseEntity<?> obtenerCitasPorUsuario(@PathVariable Long id) {
        if (!usersServicios.existePorId(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        List<Appointments> citas = usersServicios.obtenerCitasPorUsuario(id);
        return ResponseEntity.ok(citas);
    }
}
