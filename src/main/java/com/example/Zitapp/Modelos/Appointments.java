package com.example.Zitapp.Modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference; // Asegúrate de que estén todas tus anotaciones
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "Appointments")
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- CAMBIO CLAVE AQUÍ: FetchType.EAGER a LAZY ---
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // ¡CAMBIO A LAZY!
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonIgnoreProperties({"appointments", "business"}) // Sigue ignorando para evitar recursión si Users tiene citas
    private Users client;

    // --- CAMBIO CLAVE AQUÍ: FetchType.EAGER a LAZY ---
    @ManyToOne(fetch = FetchType.LAZY, optional = false) // ¡CAMBIO A LAZY!
    @JoinColumn(name = "id_negocio", nullable = false)
    @JsonManagedReference("appointment-to-business") // Mantenemos esta referencia
    private Business business;

    // --- CAMBIO CLAVE AQUÍ: FetchType.EAGER a LAZY ---
    @ManyToOne(fetch = FetchType.LAZY) // ¡CAMBIO A LAZY!
    @JoinColumn(name = "id_servicio", nullable = false)
    @JsonIgnoreProperties({"business", "appointments"}) // Mantenemos esta anotación
    private BusinnesService service;

    private LocalDate fecha;
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado; // Asumo que es un Enum, o String

    // ... (rest of your entity: constructor, getters, setters)

    // Constructor vacío requerido por JPA
    public Appointments() {}

    // Constructor para crear una instancia con datos
    public Appointments(Long id, EstadoCita estado, LocalTime hora, LocalDate fecha, Business business, Users client, BusinnesService service) {
        this.id = id;
        this.estado = estado;
        this.hora = hora;
        this.fecha = fecha;
        this.business = business;
        this.client = client;
        this.service = service;
    }

    public LocalDateTime getDateTime() {
        if (fecha != null && hora != null) {
            return LocalDateTime.of(fecha, hora);
        }
        return null;
    }

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