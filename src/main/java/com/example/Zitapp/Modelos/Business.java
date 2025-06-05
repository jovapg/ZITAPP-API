package com.example.Zitapp.Modelos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference; // Importar
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

    private String nombre;
    private String categoria;
    private String descripcion;
    private String direccion;
    private String telefono; // <--- Asegúrate de tener el campo teléfono si lo usas en el frontend

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("business-services") // Esta relación está bien si quieres ver los servicios al serializar Business
    private List<BusinnesService> services = new ArrayList<>();

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("business-availabilities") // Esta relación está bien si quieres ver las disponibilidades al serializar Business
    private List<Availability> availabilities = new ArrayList<>();

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("appointment-to-business") // <--- CAMBIO CLAVE AQUÍ: Esta lista NO se serializa al serializar Business
    private List<Appointments> appointments = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public Business() {}

    // Constructor para crear una instancia con datos
    public Business(String nombre, String categoria, String descripcion, String direccion,
                    String imagenUrl, Long idUsuario, String telefono) { // Añadido telefono al constructor
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.imagenUrl = imagenUrl;
        this.idUsuario = idUsuario;
        this.telefono = telefono; // Asignar teléfono
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

    public String getTelefono() { // <--- Getter para teléfono
        return telefono;
    }

    public void setTelefono(String telefono) { // <--- Setter para teléfono
        this.telefono = telefono;
    }

    // Métodos auxiliares para manejo bidireccional
    public void addService(BusinnesService service) {
        services.add(service);
        service.setBusiness(this);
    }

    public void removeService(BusinnesService service) {
        services.remove(service);
        service.setBusiness(null);
    }
}