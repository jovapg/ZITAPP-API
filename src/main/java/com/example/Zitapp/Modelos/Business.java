package com.example.Zitapp.Modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "businesses")
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String categoria;

    private String descripcion;

    private String direccion;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "id_usuario")
    private Long idUsuario;

    protected Business() {}

    public Business(
            String nombre,
            String categoria,
            String descripcion,
            String direccion,
            String imagenUrl,
            Long idUsuario
    ) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.imagenUrl = imagenUrl;
        this.idUsuario = idUsuario;
    }

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
