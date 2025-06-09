package com.example.Zitapp.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO para recibir datos de negocio en solicitudes (crear/actualizar).
 */
public class BusinessRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(description = "Nombre del negocio", example = "Mi negocio")
    private String nombre;

    @NotBlank(message = "La categoría es obligatoria")
    @Schema(description = "Categoría del negocio", example = "Restaurante")
    private String categoria;

    @Schema(description = "Descripción del negocio", example = "Un lugar para comer delicioso")
    private String descripcion;

    @Schema(description = "Dirección del negocio", example = "Calle 123")
    private String direccion;

    @Schema(description = "URL de la imagen del negocio", example = "http://imagen.com/negocio.jpg")
    private String imagenUrl;

    @Schema(description = "telefono del negocio", example = "3111111")
    private String telefono;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @NotNull(message = "El id de usuario es obligatorio")
    @Schema(description = "ID del usuario propietario del negocio", example = "1")
    private Long idUsuario;

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
