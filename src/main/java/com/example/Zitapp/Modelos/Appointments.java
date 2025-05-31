package com.example.Zitapp.Modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Appointments")
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Users client;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_negocio", nullable = false)
    @JsonIgnoreProperties({"services", "availabilities", "appointments"}) // Evita ciclos
    private Business business;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_servicio", nullable = false)
    @JsonIgnoreProperties({"business", "appointments"}) // Evita ciclos
    private BusinnesService service;

    private LocalDate fecha;
    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    // Constructores
    public Appointments() {
    }

    public Appointments(Long id, EstadoCita estado, LocalTime hora, LocalDate fecha, Business business, Users client) {
        this.id = id;
        this.estado = estado;
        this.hora = hora;
        this.fecha = fecha;
        this.business = business;
        this.client = client;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Users getClient() {
        return client;
    }

    public void setClient(Users client) {
        this.client = client;
    }

    public BusinnesService getService() {
        return service;
    }

    public void setService(BusinnesService service) {
        this.service = service;
    }
}