package com.example.Zitapp.Repositorios;

import com.example.Zitapp.Modelos.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentsRepositorio  extends JpaRepository<Appointments,Long> {
    // OPCIÓN 1: Query con JOIN FETCH para cargar el servicio eagerly
    @Query("SELECT a FROM Appointments a " +
            "JOIN FETCH a.service s " +
            "WHERE a.client.id = :clientId")
    List<Appointments> findByClientIdWithService(@Param("clientId") Long clientId);
    List<Appointments> findByBusinessId(Long businessId);

    // NUEVO: Obtener citas por servicio
    List<Appointments> findByServiceId(Long serviceId);
}
