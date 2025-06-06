// src/main/java/com/example/Zitapp/DTO/UsersDTO.java
package com.example.Zitapp.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para transferir datos de usuario.")
public class UsersDTO {

    @Schema(description = "Identificador único del usuario", example = "1")
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Schema(description = "Nombre del usuario", example = "Juan Pérez")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Email inválido")
    @Schema(description = "Correo electrónico del usuario", example = "juan@example.com")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 caracteres")
    @Schema(description = "Teléfono del usuario", example = "3123456789")
    private String telefono;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @Schema(description = "Contraseña del usuario", example = "mipasswordseguro")
    private String contrasena;

    @Schema(description = "Tipo de usuario: CLIENTE, NEGOCIO o ADMIN", example = "NEGOCIO")
    private String tipo;

    @Schema(description = "Edad del usuario", example = "30")
    private Integer edad;

    @Schema(description = "URL de la imagen de perfil del usuario", example = "https://example.com/imagen.jpg")
    private String imagenPerfil;

    // --- ¡NUEVO CAMPO PARA EL ID DEL NEGOCIO ASOCIADO! ---
    @Schema(description = "ID del negocio asociado si el usuario es de tipo NEGOCIO", example = "101", nullable = true)
    private Long businessId;

    // Constructor vacío
    public UsersDTO() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    // Getter y Setter para businessId
    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    @Override
    public String toString() {
        return "UsersDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipo='" + tipo + '\'' +
                ", edad=" + edad +
                ", imagenPerfil='" + imagenPerfil + '\'' +
                ", businessId=" + businessId +
                '}';
    }
}