package com.example.Zitapp.Servicios;

import com.example.Zitapp.Modelos.Availability;
import com.example.Zitapp.Modelos.Business;
import com.example.Zitapp.Repositorios.AvailabilityRepositorio;
import com.example.Zitapp.Repositorios.BusinessRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityServicio {

    private final AvailabilityRepositorio availabilityRepositorio;
    private final BusinessRepositorio businessRepositorio;

    public AvailabilityServicio(AvailabilityRepositorio availabilityRepositorio, BusinessRepositorio businessRepositorio) {
        this.availabilityRepositorio = availabilityRepositorio;
        this.businessRepositorio = businessRepositorio;
    }

    // Crear disponibilidad
    public Availability crearDisponibilidad(Availability disponibilidad, Long businessId) {
        Optional<Business> businessOptional = businessRepositorio.findById(businessId);

        if (businessOptional.isPresent()) {
            Business business = businessOptional.get();
            disponibilidad.setBusiness(business);
            return availabilityRepositorio.save(disponibilidad);
        } else {
            throw new RuntimeException("Negocio no encontrado con ID: " + businessId);
        }
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
    public Availability actualizarDisponibilidad(Integer id, Availability datosActualizados) {
        Optional<Availability> availabilityOptional = availabilityRepositorio.findById(id);

        if (availabilityOptional.isPresent()) {
            Availability availability = availabilityOptional.get();
            availability.setDia(datosActualizados.getDia());
            availability.setHoraInicio(datosActualizados.getHoraInicio());
            availability.setHoraFin((datosActualizados.getHoraFin()));

            return availabilityRepositorio.save(availability);
        } else {
            throw new RuntimeException("Disponibilidad no encontrado con ID: " + id);
        }
    }

    // Eliminar disponibilidad
    public boolean eliminarDisponibilidad(Integer id) {
        return availabilityRepositorio.findById(id).map(disp -> {
            availabilityRepositorio.delete(disp);
            return true;
        }).orElse(false);
    }
}
