// src/main/java/com/example/Zitapp/Modelos/Business.java
package com.example.Zitapp.Modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un negocio.
 */
@Entity
@Table(name = "businesses")
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(length = 500)
    private String descripcion;

    @Column(length = 255)
    private String direccion;

    @Column(length = 15)
    private String telefono;

    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;

    // --- ¡CAMBIO CLAVE AQUÍ! ---
    // Reemplazamos el campo Long idUsuario por una relación @OneToOne con la entidad Users.
    // Este es el lado "propietario" de la relación, donde se define la columna de la clave foránea.
    @OneToOne(fetch = FetchType.LAZY) // Un negocio es administrado por UN usuario
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", unique = true, nullable = false)
    // ^^^ El 'name' es el nombre de la columna en la tabla 'businesses' que guarda el ID del usuario
    // ^^^ 'referencedColumnName' es la columna a la que se hace referencia en la tabla 'users'
    // ^^^ 'unique = true' asegura que un usuario solo pueda ser propietario de un negocio
    // ^^^ 'nullable = false' asegura que cada negocio tenga un usuario asociado
    // Usamos JsonBackReference para evitar bucles de serialización si Users también tiene @JsonManagedReference a Business.
    @JsonBackReference("user-business") // Esta referencia corresponde al "user-business" de Users.java
    private Users usuario; // ¡Este es el campo 'usuario' que JPA estaba buscando!


    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("business-services")
    private List<BusinnesService> services = new ArrayList<>();

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("business-availabilities")
    private List<Availability> availabilities = new ArrayList<>();

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonBackReference("appointment-to-business")
    private List<Appointments> appointments = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public Business() {}

    // Constructor para crear una instancia con datos (asegúrate de que todos los campos del DTO estén aquí)
    // El constructor ahora recibe un objeto Users, no solo un Long idUsuario
    public Business(String nombre, String categoria, String descripcion, String direccion,
                    String imagenUrl, Users usuario, String telefono) { // <-- CAMBIO A Users usuario
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.imagenUrl = imagenUrl;
        this.usuario = usuario; // <-- Asigna el objeto Users
        this.telefono = telefono;
    }

    // Getters y setters
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

    // --- CAMBIO CLAVE: Getter y Setter para la entidad Users, no Long idUsuario ---
    public Users getUsuario() { // Obtén el objeto Users
        return usuario;
    }

    public void setUsuario(Users usuario) { // Establece el objeto Users
        this.usuario = usuario;
    }
    // --- Fin de CAMBIO CLAVE ---


    public List<BusinnesService> getServices() {
        return services;
    }

    public void setServices(List<BusinnesService> services) {
        this.services = services;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public List<Appointments> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointments> appointments) {
        this.appointments = appointments;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Métodos auxiliares para manejo bidireccional
    public void addService(BusinnesService service) {
        if (!services.contains(service)) {
            services.add(service);
            service.setBusiness(this);
        }
    }

    public void removeService(BusinnesService service) {
        if (services.contains(service)) {
            services.remove(service);
            service.setBusiness(null);
        }
    }

    public void addAvailability(Availability availability) {
        if (!availabilities.contains(availability)) {
            availabilities.add(availability);
            availability.setBusiness(this);
        }
    }

    public void removeAvailability(Availability availability) {
        if (availabilities.contains(availability)) {
            availabilities.remove(availability);
            availability.setBusiness(null);
        }
    }
}