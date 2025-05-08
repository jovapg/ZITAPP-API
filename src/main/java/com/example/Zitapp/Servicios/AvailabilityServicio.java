package com.example.Zitapp.Servicios;

import com.example.Zitapp.Modelos.Availability;
import com.example.Zitapp.Repositorios.AvailabilityRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityServicio {

    @Autowired
    private AvailabilityRepositorio availabilityRepositorio;

    // Crear disponibilidad
    public Availability crearDisponibilidad(Availability disponibilidad) {
        return availabilityRepositorio.save(disponibilidad);
    }

    // Obtener todas las disponibilidades
    public List<Availability> obtenerTodas() {
        return availabilityRepositorio.findAll();
    }

    // Obtener disponibilidad por ID
    public Optional<Availability> obtenerPorId(Integer id) {
        return availabilityRepositorio.findById(id);
    }

    // Actualizar disponibilidad
    public Optional<Availability> actualizarDisponibilidad(Integer id, Availability datosActualizados) {
        return availabilityRepositorio.findById(id).map(disp -> {
            disp.setIdNegocio(datosActualizados.getIdNegocio());
            disp.setDia(datosActualizados.getDia());
            disp.setHoraInicio(datosActualizados.getHoraInicio());
            disp.setHoraFin(datosActualizados.getHoraFin());
            return availabilityRepositorio.save(disp);
        });
    }

    // Eliminar disponibilidad
    public boolean eliminarDisponibilidad(Integer id) {
        return availabilityRepositorio.findById(id).map(disp -> {
            availabilityRepositorio.delete(disp);
            return true;
        }).orElse(false);
    }
}
