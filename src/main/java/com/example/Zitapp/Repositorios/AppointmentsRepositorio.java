package com.example.Zitapp.Repositorios;

import com.example.Zitapp.Modelos.Appointments;
import com.example.Zitapp.Modelos.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentsRepositorio  extends JpaRepository<Appointments,Long> {
    List<Appointments> findByClientId(Long clientId);
    List<Appointments> findByBusinessId(Long businessId);
    // En tu AppointmentsRepositorio, agrega este método:
    List<Appointments> findByBusinessIdAndEstado(Long businessId, EstadoCita estado);

}
