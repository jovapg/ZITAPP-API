package com.example.Zitapp.Controladores;

import com.example.Zitapp.Modelos.BusinnesService;
import com.example.Zitapp.Servicios.BusinessServiceServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class BusinessServiceControlador {

    private final BusinessServiceServicio serviceServicio;

    @Autowired
    public BusinessServiceControlador(BusinessServiceServicio serviceServicio) {
        this.serviceServicio = serviceServicio;
    }

    // Obtener todos los servicios
    @GetMapping("/")
    public ResponseEntity<Object> obtenerTodosLosServicios() {
        try {
            List<BusinnesService> servicios = serviceServicio.obtenerTodosLosServicios();
            return new ResponseEntity<>(servicios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Error al obtener servicios: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener un servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerServicioPorId(@PathVariable Long id) {
        try {
            Optional<BusinnesService> servicio = serviceServicio.obtenerServicioPorId(id);

            if (servicio.isPresent()) {
                return new ResponseEntity<>(servicio.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Servicio no encontrado con ID: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Error al obtener servicio: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener servicios por ID de negocio
    @GetMapping("/businesses/{businessId}/services")
    public ResponseEntity<Object> obtenerServiciosPorNegocioId(@PathVariable Long businessId) {
        try {
            List<BusinnesService> servicios = serviceServicio.obtenerServiciosPorNegocioId(businessId);
            return new ResponseEntity<>(servicios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Error al obtener servicios del negocio: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Crear un nuevo servicio asociado a un negocio
    // JSON para creacion
    // {
    //      "nombre": "Mantenimiento",
    //      "descripcion": "Mantenimiento",
    //      "precio": 20.000
    //}
    @PostMapping("/businesses/{businessId}")
    public ResponseEntity<Object> crearServicio(@PathVariable Long businessId, @RequestBody BusinnesService service) {
        try {
            BusinnesService nuevoServicio = serviceServicio.crearServicio(service, businessId);
            return new ResponseEntity<>(nuevoServicio, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error al crear servicio: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Error interno al crear servicio: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualizar un servicio existente
    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarServicio(@PathVariable Long id, @RequestBody BusinnesService serviceDetails) {
        try {
            BusinnesService servicioActualizado = serviceServicio.actualizarServicio(id, serviceDetails);
            return new ResponseEntity<>(servicioActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error al actualizar servicio: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Error interno al actualizar servicio: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar un servicio
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarServicio(@PathVariable Long id) {
        try {
            serviceServicio.eliminarServicio(id);
            return new ResponseEntity<>("Servicio eliminado correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error al eliminar servicio: " + e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Error interno al eliminar servicio: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar servicios por rango de precios
    @GetMapping("/businesses/{businessId}/services/search/by-price-range")
    public ResponseEntity<Object> buscarServiciosPorRangoDePrecio(
            @PathVariable Long businessId,
            @RequestParam Double min,
            @RequestParam Double max) {
        try {
            List<BusinnesService> servicios = serviceServicio.buscarServiciosPorRangoDePrecio(businessId, min, max);
            return new ResponseEntity<>(servicios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Error al buscar servicios por rango de precio: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Buscar servicios por nombre
    @GetMapping("/businesses/{businessId}/services/search/by-name")
    public ResponseEntity<Object> buscarServiciosPorNombre(
            @PathVariable Long businessId,
            @RequestParam String nombre) {
        try {
            List<BusinnesService> servicios = serviceServicio.buscarServiciosPorNombre(businessId, nombre);
            return new ResponseEntity<>(servicios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Error al buscar servicios por nombre: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}