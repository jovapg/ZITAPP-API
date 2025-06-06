package com.example.Zitapp.Controladores;

import com.example.Zitapp.DTO.AppointmentCreateDTO; // Para crear citas
import com.example.Zitapp.DTO.AppointmentDetailsDTO; // ¡Tu nuevo DTO para la respuesta!
import com.example.Zitapp.Modelos.Appointments; // Aunque no devuelvas la entidad directamente, la puedes usar internamente
import com.example.Zitapp.Servicios.AppointmentsServicios;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional; // Necesario para Optional
import java.util.stream.Collectors; // Para mapear listas

@RestController
@RequestMapping("/api/appointments")
@Tag(name = "Citas", description = "API para gestionar citas")
public class AppointmentsControlador {

    @Autowired
    private AppointmentsServicios appointmentsServicios;

    /**
     * Crea una nueva cita.
     * @param appointmentDTO DTO con los datos para crear la cita.
     * @return La cita creada (posiblemente mapeada a un DTO de respuesta si tienes uno específico para la creación).
     */
    @PostMapping
    @Operation(summary = "Crear una nueva cita")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cita creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "404", description = "Cliente, negocio o servicio no encontrado")
    })
    public ResponseEntity<?> createAppointment(@Valid @RequestBody AppointmentCreateDTO appointmentDTO) {
        try {
            // El servicio manejará la lógica de creación y devolverá la entidad creada o un DTO.
            Appointments createdAppointment = appointmentsServicios.createAppointment(appointmentDTO);
            // Devuelve el AppointmentDetailsDTO de la cita recién creada
            return ResponseEntity.status(HttpStatus.CREATED).body(appointmentsServicios.ObtenerCita(createdAppointment.getId()).orElse(null));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la cita: " + e.getMessage());
        }
    }

    /**
     * Obtener una cita por ID y devolverla como un DTO de detalles.
     * @param id ID de la cita
     * @return DTO de la cita encontrada o 404 si no existe
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una cita por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cita encontrada"),
            @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    public ResponseEntity<?> obtenerCita(@PathVariable Long id) {
        try {
            // El servicio ahora devuelve un Optional<AppointmentDetailsDTO>
            Optional<AppointmentDetailsDTO> citaDTO = appointmentsServicios.ObtenerCita(id);
            return citaDTO.map(ResponseEntity::ok) // Si el DTO está presente, devuelve 200 OK
                    .orElse(ResponseEntity.notFound().build()); // Si no, devuelve 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la cita: " + e.getMessage());
        }
    }

    /**
     * Obtener citas por ID de negocio.
     * @param idBusiness ID del negocio
     * @return Lista de citas del negocio.
     */
    @GetMapping("/business/{idBusiness}")
    @Operation(summary = "Obtener citas por negocio")
    public ResponseEntity<?> getCitasPorNegocio(@PathVariable Long idBusiness) {
        try {
            // Se asume que appointmentsServicios.obtenerCitasPorNegocio(idBusiness)
            // devuelve una List<Appointments> (entidades).
            // Luego, cada entidad se mapea a un AppointmentDetailsDTO usando ObtenerCita(id).
            List<AppointmentDetailsDTO> citas = appointmentsServicios.obtenerCitasPorNegocio(idBusiness).stream()
                    // Map a AppointmentDetailsDTO para cada cita, manejando si es Optional.empty()
                    .map(appointment -> appointmentsServicios.ObtenerCita(appointment.getId()).orElse(null))
                    // Eliminar cualquier posible null que resulte del orElse(null) si una cita no se encuentra
                    .filter(java.util.Objects::nonNull)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las citas del negocio: " + e.getMessage());
        }
    }

    /**
     * Obtener citas por ID de cliente.
     * @param idClient ID del cliente
     * @return Lista de citas del cliente.
     */
    @GetMapping("/clients/{idClient}")
    @Operation(summary = "Obtener citas por cliente")
    public ResponseEntity<?> getCitasPorCliente(@PathVariable Long idClient) {
        try {
            // Similar al método anterior, mapea la lista de entidades a una lista de DTOs
            List<AppointmentDetailsDTO> citas = appointmentsServicios.obtenerCitasPorCliente(idClient).stream()
                    .map(appointment -> appointmentsServicios.ObtenerCita(appointment.getId()).orElse(null))
                    .filter(java.util.Objects::nonNull)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las citas del cliente: " + e.getMessage());
        }
    }

    /**
     * Actualizar el estado de una cita.
     * (Asumo que tienes un DTO o RequestBody simple para esto, ej. StatusUpdateDTO con String newStatus)
     * @param id ID de la cita a actualizar.
     * @param newStatus Estado nuevo.
     * @return Cita actualizada o 404.
     */
    @PutMapping("/{id}/status")
    @Operation(summary = "Actualizar estado de una cita")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de cita actualizado"),
            @ApiResponse(responseCode = "400", description = "Estado inválido"),
            @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    public ResponseEntity<?> updateAppointmentStatus(@PathVariable Long id, @RequestBody String newStatus) {
        try {
            // Asumo que tu servicio `updateAppointmentStatus` devuelve un AppointmentDetailsDTO
            AppointmentDetailsDTO updatedCita = appointmentsServicios.updateAppointmentStatus(id, newStatus);
            return ResponseEntity.ok(updatedCita);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el estado de la cita: " + e.getMessage());
        }
    }

    /**
     * Eliminar una cita por ID.
     * @param id ID de la cita a eliminar.
     * @return Mensaje de éxito o 404.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una cita por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cita eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Cita no encontrada")
    })
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        try {
            appointmentsServicios.deleteAppointment(id);
            return ResponseEntity.ok().body("Cita eliminada correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error al eliminar la cita: " + e.getMessage());
        }
    }
}