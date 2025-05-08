package com.example.Zitapp.Repositorios;

import com.example.Zitapp.Modelos.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentsRepositorio  extends JpaRepository<Appointments,Long> {
    List<Appointments> findByClientId(Long clientId);
    List<Appointments> findByBusinessId(Long businessId);


}
