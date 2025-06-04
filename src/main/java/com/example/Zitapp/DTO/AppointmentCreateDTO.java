package com.example.Zitapp.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentCreateDTO {

    private Long clientId;
    private Long businessId;
    private Long serviceId;
    private LocalDate fecha; // solo fecha, ej: 2025-06-05
    private LocalTime hora;  // solo hora, ej: 15:30

    // Getters y Setters
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
