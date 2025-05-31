package com.example.Zitapp.Controladores;

import com.example.Zitapp.Modelos.Availability;
import com.example.Zitapp.Servicios.AvailabilityServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/availability")

public class AvailabilityControlador {

    @Autowired
    private AvailabilityServicio availabilityServicio;

    // Crear disponibilidad
    @PostMapping // http://localhost:8081/api/availability
    public ResponseEntity<?> crearDisponibilidad(@RequestBody Availability disponibilidad) {
        try {
            Availability nueva = availabilityServicio.crearDisponibilidad(disponibilidad);
            return ResponseEntity.ok(nueva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear disponibilidad: " + e.getMessage());
        }
    }

    // Obtener todas las disponibilidades
    @GetMapping // http://localhost:8081/api/availability
    public ResponseEntity<?> obtenerDisponibilidades() {
        try {
            List<Availability> disponibilidades = availabilityServicio.obtenerTodas();
            return ResponseEntity.ok(disponibilidades);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener disponibilidades: " + e.getMessage());
        }
    }

    // Obtener disponibilidad por ID
    @GetMapping("/{id}") // http://localhost:8081/api/availability/{id}
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            return availabilityServicio.obtenerPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener disponibilidad con ID " + id + ": " + e.getMessage());
        }
    }

    // Actualizar disponibilidad
    @PutMapping("/{id}") // http://localhost:8081/api/availability/{id}
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Availability datosActualizados) {
        try {
            return availabilityServicio.actualizarDisponibilidad(id, datosActualizados)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar disponibilidad con ID " + id + ": " + e.getMessage());
        }
    }

    // Eliminar disponibilidad
    @DeleteMapping("/{id}") // http://localhost:8081/api/availability/{id}
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            boolean eliminado = availabilityServicio.eliminarDisponibilidad(id);
            return eliminado ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar disponibilidad con ID " + id + ": " + e.getMessage());
        }
    }
}