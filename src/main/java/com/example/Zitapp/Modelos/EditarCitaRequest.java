package com.example.Zitapp.Modelos;

import java.time.LocalDate;
import java.time.LocalTime;

public class EditarCitaRequest {
    private LocalDate nuevaFecha;
    private LocalTime nuevaHora;

    // Getters y Setters

    public LocalDate getNuevaFecha() {
        return nuevaFecha;
    }

    public void setNuevaFecha(LocalDate nuevaFecha) {
        this.nuevaFecha = nuevaFecha;
    }

    public LocalTime getNuevaHora() {
        return nuevaHora;
    }

    public void setNuevaHora(LocalTime nuevaHora) {
        this.nuevaHora = nuevaHora;
    }
}

