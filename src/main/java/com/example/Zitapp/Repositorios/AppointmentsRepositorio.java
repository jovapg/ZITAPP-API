// src/main/java/com/example/Zitapp/Repositorios/AppointmentsRepositorio.java
package com.example.Zitapp.Repositorios;

import com.example.Zitapp.Modelos.Appointments;
import com.example.Zitapp.Modelos.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; // Asegúrate de que esta importación esté ahí

import java.time.LocalDate;
import java.util.List;

@Repository // Asegúrate de que esta anotación esté ahí
public interface AppointmentsRepositorio  extends JpaRepository<Appointments,Long> {
    // OPCIÓN 1: Query con JOIN FETCH para cargar el servicio eagerly (para citas de cliente)
    @Query("SELECT a FROM Appointments a " +
            "JOIN FETCH a.service s " +
            "JOIN FETCH a.business b " + // Opcional: Si quieres que business también se cargue eager para esta consulta
            "WHERE a.client.id = :clientId")
    List<Appointments> findByClientIdWithService(@Param("clientId") Long clientId);

    // Tu método existente, que solo traía la cita por businessId (sin JOIN FETCH para client/service)
    // Esto causaba que el client no se serializara correctamente
    List<Appointments> findByBusinessId(Long businessId); // No borrar, se mantiene por si lo usas en otro lado

    // --- ¡NUEVO MÉTODO! Para obtener citas por negocio CON CLIENTE Y SERVICIO cargados ---
    // Usaremos este método en el servicio para el endpoint de citas por negocio.
    @Query("SELECT a FROM Appointments a " +
            "JOIN FETCH a.business b " +   // Carga el negocio
            "JOIN FETCH a.client c " +     // ¡Carga el cliente!
            "JOIN FETCH a.service s " +    // ¡Carga el servicio!
            "WHERE b.id = :businessId")
    List<Appointments> findByBusinessIdWithClientAndService(@Param("businessId") Long businessId);


    // Métodos existentes (sin cambios)
    List<Appointments> findByBusinessIdAndEstado(Long businessId, EstadoCita estado);
    List<Appointments> findByServiceId(Long serviceId);
    List<Appointments> findByBusinessIdAndFecha(Long businessId, LocalDate fecha);
}