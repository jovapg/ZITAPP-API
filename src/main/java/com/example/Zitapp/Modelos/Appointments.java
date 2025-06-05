package com.example.Zitapp.Modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference; // Importar
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Entity
@Table(name = "Appointments")
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonBackReference("user-appointments") // Mantener si la relación bidireccional User->Appointments existe
    private Users client;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_negocio", nullable = false)
    @JsonManagedReference("appointment-to-business") // <--- CAMBIO CLAVE AQUÍ: Ahora se serializa Business desde Appointments
    private Business business;


    @ManyToOne(fetch = FetchType.EAGER) // Ya estaba en EAGER, lo cual es bueno para cargar el servicio
    @JoinColumn(name = "id_servicio", nullable = false)
    // Mantener esta anotación para evitar la recursión si Service también tiene una referencia a Appointments
    @JsonIgnoreProperties({"business", "appointments"}) // Asumo que estas propiedades están en Service
    private BusinnesService service;


    private LocalDate fecha;
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado; // Asumo que es un Enum


    // Constructor vacío requerido por JPA
    public Appointments() {}

    // Constructor para crear una instancia con datos
    // Nota: Es mejor usar un Builder pattern o setters para asignar todas las propiedades
    public Appointments(Long id, EstadoCita estado, LocalTime hora, LocalDate fecha, Business business, Users client) {
        this.id = id;
        this.estado = estado;
        this.hora = hora;
        this.fecha = fecha;
        this.business = business;
        this.client = client;
        // service no está en este constructor, asegúrate de setearlo si es necesario
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