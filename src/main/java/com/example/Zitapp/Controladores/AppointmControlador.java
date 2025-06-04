package com.example.Zitapp.Controladores;

import com.example.Zitapp.DTO.AppointmentCreateDTO;
import com.example.Zitapp.Modelos.*;
import com.example.Zitapp.Repositorios.BusinessRepositorio;
import com.example.Zitapp.Repositorios.BusinessServiceRepositorio;
import com.example.Zitapp.Repositorios.UsersRepositorio;
import com.example.Zitapp.Servicios.AppointmentsServicios;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@Tag(name = "Citas", description = "API para gestión de citas")
public class AppointmControlador {

    @Autowired
    private AppointmentsServicios appointmentsServicios;

    @Autowired
    private UsersRepositorio usersRepositorio;

    @Autowired
    private BusinessRepositorio businessRepositorio;

    @Autowired
    private BusinessServiceRepositorio serviceRepositorio;

    @PostMapping
    @Operation(summary = "Crear una nueva cita")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cita creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno al crear la cita")
    })
    public ResponseEntity<?> crearCita(@RequestBody AppointmentCreateDTO dto) {
        try {
            // Pasar directamente el DTO al servicio, que hará las validaciones y guardado
            Appointments citaGuardada = appointmentsServicios.CrearCita(dto);
            return ResponseEntity.ok(citaGuardada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la cita: " + e.getMessage());
        }
    }


    @GetMapping("/all")
    @Operation(summary = "Obtener todas las citas")
    public ResponseEntity<?> obtenerCitas() {
        try {
            List<Appointments> citas = appointmentsServicios.ObtenerCitas();
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las citas: " + e.getMessage());
        }
    }

    @GetMapping("/clients/{idClient}")
    @Operation(summary = "Obtener citas por cliente")
    public ResponseEntity<?> getCitasPorCliente(@PathVariable Long idClient) {
        try {
            List<Appointments> citas = appointmentsServicios.ObtenerCitasPorCliente(idClient);
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las citas del cliente: " + e.getMessage());
        }
    }

    @GetMapping("/business/{idBusiness}")
    @Operation(summary = "Obtener citas por negocio")
    public ResponseEntity<?> getCitasPorNegocio(@PathVariable Long idBusiness) {
        try {
            List<Appointments> citas = appointmentsServicios.ObtenerCitasPorNegocio(idBusiness);
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las citas del negocio: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/confirmar")
    @Operation(summary = "Confirmar una cita")
    public ResponseEntity<?> confirmarCita(@PathVariable Long id) {
        try {
            Appointments confirmada = appointmentsServicios.ConfirmarCita(id);
            return ResponseEntity.ok(confirmada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al confirmar la cita: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/cancelar")
    @Operation(summary = "Cancelar una cita")
    public ResponseEntity<?> cancelarCita(@PathVariable Long id) {
        try {
            Appointments cancelar = appointmentsServicios.CancelarCita(id);
            return ResponseEntity.ok(cancelar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cancelar la cita: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/finalizar")
    @Operation(summary = "Finalizar una cita")
    public ResponseEntity<?> finalizarCita(@PathVariable Long id) {
        try {
            Appointments finalizar = appointmentsServicios.FinalizarCita(id);
            return ResponseEntity.ok(finalizar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al finalizar la cita: " + e.getMessage());
        }
    }

    @PutMapping("/editar/{id}")
    @Operation(summary = "Editar fecha y hora de una cita")
    public ResponseEntity<?> editarCita(@PathVariable Long id, @RequestBody EditarCitaRequest request) {
        try {
            Appointments citaActualizada = appointmentsServicios.editarCita(id, request);
            return ResponseEntity.ok(citaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al editar la cita: " + e.getMessage());
        }
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener una cita por ID")
    public ResponseEntity<?> obtenerCita(@PathVariable Long id) {
        try {
            return appointmentsServicios.ObtenerCita(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la cita: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una cita")
    public ResponseEntity<?> eliminarCita(@PathVariable Long id) {
        try {
            appointmentsServicios.EliminarCita(id);
            return ResponseEntity.ok("Cita eliminada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la cita: " + e.getMessage());
        }
    }
}
