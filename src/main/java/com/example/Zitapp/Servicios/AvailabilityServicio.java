package com.example.Zitapp.Servicios;

import com.example.Zitapp.DTO.AvailabilityDTO;
import com.example.Zitapp.Modelos.Availability;
import com.example.Zitapp.Modelos.Business;
import com.example.Zitapp.Repositorios.AvailabilityRepositorio;
import com.example.Zitapp.Repositorios.BusinessRepositorio;
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

    public Availability crearDisponibilidad(Availability disponibilidad, Long businessId) {
        Business business = businessRepositorio.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Negocio no encontrado con ID: " + businessId));
        disponibilidad.setBusiness(business);
        return availabilityRepositorio.save(disponibilidad);
    }

    public List<Availability> obtenerTodas() {
        return availabilityRepositorio.findAll();
    }

    public Optional<Availability> obtenerPorId(Integer id) {
        return availabilityRepositorio.findById(id);
    }

    public Availability actualizarDisponibilidad(Integer id, AvailabilityDTO datosActualizados) {
        Availability disponibilidad = availabilityRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Disponibilidad no encontrada con ID: " + id));

        disponibilidad.setDia(datosActualizados.getDia());
        disponibilidad.setHoraInicio(datosActualizados.getHoraInicio());
        disponibilidad.setHoraFin(datosActualizados.getHoraFin());

        return availabilityRepositorio.save(disponibilidad);
    }


    public boolean eliminarDisponibilidad(Integer id) {
        Optional<Availability> disponibilidad = availabilityRepositorio.findById(id);
        if (disponibilidad.isPresent()) {
            availabilityRepositorio.delete(disponibilidad.get());
            return true;
        }
        return false;
    }
}
