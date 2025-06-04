package com.example.Zitapp.Modelos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class EditarCitaRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate nuevaFecha;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime nuevaHora;

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
