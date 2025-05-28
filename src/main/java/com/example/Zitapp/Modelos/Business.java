package com.example.Zitapp.Modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BusinnesService> services = new ArrayList<>();

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Availability> availabilities = new ArrayList<>();

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

    public List<BusinnesService> getServices() {
        return services;
    }

    public void setServices(List<BusinnesService> services) {
        this.services = services;
    }

    public List<Availability> getAvailabilities() {return availabilities;}

    // Método de ayuda para agregar un servicio
    public void addService(BusinnesService service) {
        services.add(service);
        service.setBusiness(this);
    }

    // Método de ayuda para eliminar un servicio
    public void removeService(BusinnesService service) {
        services.remove(service);
        service.setBusiness(null);
    }
}