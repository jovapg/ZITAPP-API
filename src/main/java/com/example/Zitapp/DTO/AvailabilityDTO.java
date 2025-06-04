package com.example.Zitapp.DTO;

import java.time.LocalTime;

public class AvailabilityDTO {
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    // Getters y setters
    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
}
