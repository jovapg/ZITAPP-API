
package com.example.Zitapp.Repositorios;

import com.example.Zitapp.Modelos.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailabilityRepositorio extends JpaRepository<Availability, Integer> {
    // Nuevo método para encontrar la disponibilidad por ID de negocio y día de la semana
    List<Availability> findByBusinessIdAndDia(Long businessId, String dia);
    List<Availability> findByBusinessId(Integer businessId);
}