package com.example.Zitapp.Controladores;

import com.example.Zitapp.DTO.AvailabilityDTO;
import com.example.Zitapp.Modelos.Availability;
import com.example.Zitapp.Servicios.AvailabilityServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/availability")
@Tag(name = "Disponibilidad", description = "Operaciones para gestionar la disponibilidad de los negocios")
public class AvailabilityControlador {

    private final AvailabilityServicio availabilityServicio;

    public AvailabilityControlador(AvailabilityServicio availabilityServicio) {
        this.availabilityServicio = availabilityServicio;
    }

    @Operation(summary = "Crear una disponibilidad para un negocio con parámetros en URL")
    @ApiResponse(responseCode = "201", description = "Disponibilidad creada correctamente")
    @ApiResponse(responseCode = "404", description = "Negocio no encontrado")
    @PostMapping("/create/{businessId}")
    public ResponseEntity<?> crearDisponibilidad(
            @PathVariable Long businessId,
            @RequestParam String dia,
            @RequestParam String horaInicio,
            @RequestParam String horaFin
    ) {
        try {
            Availability disponibilidad = new Availability();
            disponibilidad.setDia(dia);
            disponibilidad.setHoraInicio(LocalTime.parse(horaInicio));
            disponibilidad.setHoraFin(LocalTime.parse(horaFin));

            Availability nueva = availabilityServicio.crearDisponibilidad(disponibilidad, businessId);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear disponibilidad: " + e.getMessage());
        }
    }


    @Operation(summary = "Obtener todas las disponibilidades")
    @ApiResponse(responseCode = "200", description = "Lista de disponibilidades obtenida correctamente")
    @GetMapping
    public ResponseEntity<List<Availability>> obtenerDisponibilidades() {
        return ResponseEntity.ok(availabilityServicio.obtenerTodas());
    }

    @Operation(summary = "Obtener disponibilidad por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Disponibilidad encontrada"),
            @ApiResponse(responseCode = "404", description = "Disponibilidad no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        Optional<Availability> disponibilidad = availabilityServicio.obtenerPorId(id);

        if (disponibilidad.isPresent()) {
            return ResponseEntity.ok(disponibilidad.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disponibilidad no encontrada");
        }
    }


    @Operation(summary = "Actualizar una disponibilidad")
    @ApiResponse(responseCode = "200", description = "Disponibilidad actualizada correctamente")
    @ApiResponse(responseCode = "404", description = "Disponibilidad no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDisponibilidad(@PathVariable Integer id, @RequestBody AvailabilityDTO datosActualizados) {
        try {
            Availability updated = availabilityServicio.actualizarDisponibilidad(id, datosActualizados);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar disponibilidad: " + e.getMessage());
        }
    }

    @Operation(summary = "Eliminar una disponibilidad")
    @ApiResponse(responseCode = "204", description = "Disponibilidad eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Disponibilidad no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDisponibilidad(@PathVariable Integer id) {
        if (availabilityServicio.eliminarDisponibilidad(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disponibilidad no encontrada");
        }
    }
}
