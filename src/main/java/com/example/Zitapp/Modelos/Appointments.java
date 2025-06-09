// src/main/java/com/example/Zitapp/Modelos/Appointments.java
package com.example.Zitapp.Modelos;

// import com.fasterxml.jackson.annotation.JsonBackReference; // Ya no la necesitamos aquí para client
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // Importar
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Entity
@Table(name = "Appointments") // Asegúrate del nombre de la tabla
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    // --- CAMBIO CLAVE AQUÍ: Quitar JsonBackReference para 'client' ---
    // Si Users tiene una lista de citas, y Appointments tiene un Users 'client',
    // Y quieres serializar el cliente completo dentro de la cita, necesitas
    // usar JsonIgnoreProperties para evitar un bucle si Users también tiene una lista de citas.
    @JsonIgnoreProperties({"appointments", "business"}) // Ignora las citas y el negocio de la entidad Users para evitar recursión
    private Users client;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_negocio", nullable = false)
    @JsonManagedReference("appointment-to-business")// Esto está bien si el Business tiene una @JsonBackReference a Appointments
    @JsonIgnoreProperties({"services", "availabilities"})// Esto está bien si el Business tiene una @JsonBackReference a Appointments
    private Business business;


    @ManyToOne(fetch = FetchType.EAGER) // Ya estaba en EAGER
    @JoinColumn(name = "id_servicio", nullable = false)
    // Mantener esta anotación para evitar la recursión si BusinnesService tiene una referencia a Business o Appointments
    @JsonIgnoreProperties({"business", "appointments"}) // Asumo que estas propiedades están en BusinnesService
    private BusinnesService service;


    private LocalDate fecha;
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado; // Asumo que es un Enum


    // Constructor vacío requerido por JPA
    public Appointments() {}

    // Constructor para crear una instancia con datos
    // Nota: Es mejor usar un Builder pattern o setters para asignar todas las propiedades
    public Appointments(Long id, EstadoCita estado, LocalTime hora, LocalDate fecha, Business business, Users client, BusinnesService service) {
        this.id = id;
        this.estado = estado;
        this.hora = hora;
        this.fecha = fecha;
        this.business = business;
        this.client = client;
        this.service = service; // Asegúrate de incluir el servicio aquí si usas este constructor
    }

    // Helper para obtener LocalDateTime (si lo usas)
    public LocalDateTime getDateTime() {
        if (fecha != null && hora != null) {
            return LocalDateTime.of(fecha, hora);
        }
        return null;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public EstadoCita getEstado() { return estado; }
    public void setEstado(EstadoCita estado) { this.estado = estado; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Business getBusiness() { return business; }
    public void setBusiness(Business business) { this.business = business; }

    public Users getClient() { return client; }
    public void setClient(Users client) { this.client = client; }

    public BusinnesService getService() { return service; }
    public void setService(BusinnesService service) { this.service = service; }
}