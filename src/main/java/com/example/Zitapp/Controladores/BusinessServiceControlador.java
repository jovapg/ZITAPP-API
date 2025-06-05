package com.example.Zitapp.Controladores;

import com.example.Zitapp.DTO.BusinnesServiceDTO;
import com.example.Zitapp.Modelos.BusinnesService;
import com.example.Zitapp.Servicios.BusinessServiceServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
@Tag(name = "Servicios", description = "CRUD de servicios ofrecidos por los negocios")
public class BusinessServiceControlador {

    private final BusinessServiceServicio serviceServicio;

    @Autowired
    public BusinessServiceControlador(BusinessServiceServicio serviceServicio) {
        this.serviceServicio = serviceServicio;
    }
//http://localhost:8081/api/services/
    @Operation(summary = "Obtener todos los servicios")
    @GetMapping("/")
    public ResponseEntity<List<BusinnesService>> obtenerTodos() {
        return ResponseEntity.ok(serviceServicio.obtenerTodosLosServicios());
    }

    @Operation(summary = "Obtener un servicio por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<BusinnesService> servicio = serviceServicio.obtenerServicioPorId(id);
        if (servicio.isPresent()) {
            return ResponseEntity.ok(servicio.get());
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }


  @Operation(summary = "Obtener todos los servicios de un negocio")

    @GetMapping("/businesses/{businessId}/services")
    public ResponseEntity<List<BusinnesService>> obtenerPorNegocio(@PathVariable Long businessId) {
        return ResponseEntity.ok(serviceServicio.obtenerServiciosPorNegocioId(businessId));
    }

    @Operation(summary = "Crear un servicio para un negocio usando parámetros en URL")
    @PostMapping(value = "/businesses/{businessId}/from-params")
    public ResponseEntity<?> crearDesdeParams(
            @Parameter(description = "ID del negocio", example = "1")
            @PathVariable Long businessId,
            @Parameter(description = "Nombre del servicio", example = "Masaje relajante")
            @RequestParam String nombre,
            @Parameter(description = "Descripción del servicio", example = "Masaje completo de cuerpo")
            @RequestParam String descripcion,
            @Parameter(description = "Precio del servicio", example = "45000")
            @RequestParam Double precio,
            @Parameter(description = "Duración del servicio en minutos", example = "60")
            @RequestParam int duracion) {

        try {
            BusinnesService service = new BusinnesService();
            service.setNombre(nombre);
            service.setDescripcion(descripcion);
            service.setPrecio(precio);
            service.setDuracion(duracion);

            return ResponseEntity.status(201).body(serviceServicio.crearServicio(service, businessId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }


    @Operation(summary = "Actualizar un servicio existente")

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody BusinnesServiceDTO detalles) {
        try {
            return ResponseEntity.ok(serviceServicio.actualizarServicio(id, detalles));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }



    @Operation(summary = "Eliminar un servicio por ID")


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try {
            serviceServicio.eliminarServicio(id);
            return ResponseEntity.ok("Servicio eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @Operation(summary = "Buscar servicios por rango de precio")
    @GetMapping("/businesses/{businessId}/services/search/by-price-range")
    public ResponseEntity<List<BusinnesService>> buscarPorPrecio(@PathVariable Long businessId,
                                                                 @RequestParam Double min,
                                                                 @RequestParam Double max) {
        return ResponseEntity.ok(serviceServicio.buscarServiciosPorRangoDePrecio(businessId, min, max));
    }

    @Operation(summary = "Buscar servicios por nombre")
    @GetMapping("/businesses/{businessId}/services/search/by-name")
    public ResponseEntity<List<BusinnesService>> buscarPorNombre(@PathVariable Long businessId,
                                                                 @RequestParam String nombre) {
        return ResponseEntity.ok(serviceServicio.buscarServiciosPorNombre(businessId, nombre));
    }

    // Endpoint de prueba
    @PostMapping("/test-json")
    public ResponseEntity<String> testJson(@RequestBody Map<String, Object> data) {
        return ResponseEntity.ok("Funcionó: " + data.toString());
    }
}
