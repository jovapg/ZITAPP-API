package com.example.Zitapp.Modelos;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Appointments")
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación Many-to-One con Users (cliente), cambiamos a EAGER
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Users client;

    // Relación Many-to-One con Business (negocio), cambiamos a EAGER
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_negocio", nullable = false)
    private Business business;

    private LocalDate fecha;
    private LocalTime hora;
    //Guarda este enum como un texto (STRING) en la base de datos,
    @Enumerated(EnumType.STRING)
    private EstadoCita estado; //Pendiente,Confimada,Cancelada

    //constructores

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
//set && get
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
}





