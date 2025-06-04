package com.example.Zitapp.Modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Entidad que representa a un usuario en el sistema.")
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del usuario", example = "1")
    private Long id;

    @Column(nullable = false, length = 50)
    @Schema(description = "Nombre del usuario", example = "Juan Pérez")
    private String nombre;

    @Schema(description = "Teléfono del usuario", example = "3123456789")
    private String telefono;

    @Schema(description = "Correo electrónico del usuario", example = "juan@example.com")
    private String email;

    @Schema(description = "Contraseña del usuario (debería estar encriptada)", example = "******")
    private String contrasena;

    @Schema(description = "Edad del usuario", example = "25")
    private short edad;

    @Column(name = "imagen_perfil")
    @Schema(description = "URL de la imagen de perfil del usuario", example = "http://example.com/profile.jpg")
    private String imagenPerfil;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Tipo de usuario: CLIENTE, NEGOCIO o ADMIN", example = "CLIENTE")
    private TipoUsuario tipo;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonManagedReference("user-appointments")
    private List<Appointments> appointments;


    public enum TipoUsuario {
        CLIENTE,
        NEGOCIO,
        ADMIN
    }

    // Constructor vacío (requerido por JPA)
    public Users() {
    }

    // Constructor personalizado
    public Users(String contrasena, String email, Long id, String nombre, String telefono, TipoUsuario tipo) {
        this.contrasena = contrasena;
        this.email = email;
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.tipo = tipo;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public short getEdad() {
        return edad;
    }

    public void setEdad(short edad) {
        this.edad = edad;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public List<Appointments> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointments> appointments) {
        this.appointments = appointments;
    }
}
