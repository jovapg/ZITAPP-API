package com.example.Zitapp.Controladores;

import com.example.Zitapp.Modelos.Availability;
import com.example.Zitapp.Servicios.AvailabilityServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityControlador {

    @Autowired
    private AvailabilityServicio availabilityServicio;

    // Crear disponibilidad
    @PostMapping // http://localhost:8081/api/availability
    public ResponseEntity<Availability> crearDisponibilidad(@RequestBody Availability disponibilidad) {
        Availability nueva = availabilityServicio.crearDisponibilidad(disponibilidad);
        return ResponseEntity.ok(nueva);
    }

    // Obtener todas las disponibilidades
    @GetMapping // http://localhost:8081/api/availability
    public ResponseEntity<List<Availability>> obtenerDisponibilidades() {
        return ResponseEntity.ok(availabilityServicio.obtenerTodas());
    }

    // Obtener disponibilidad por ID
    @GetMapping("/{id}") // http://localhost:8081/api/availability/{id}
    public ResponseEntity<Availability> obtenerPorId(@PathVariable Integer id) {
        return availabilityServicio.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar disponibilidad
    @PutMapping("/{id}") // http://localhost:8081/api/availability/{id}
    public ResponseEntity<Availability> actualizar(@PathVariable Integer id, @RequestBody Availability datosActualizados) {
        return availabilityServicio.actualizarDisponibilidad(id, datosActualizados)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar disponibilidad
    @DeleteMapping("/{id}") // http://localhost:8081/api/availability/{id}
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        boolean eliminado = availabilityServicio.eliminarDisponibilidad(id);
        return eliminado ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
