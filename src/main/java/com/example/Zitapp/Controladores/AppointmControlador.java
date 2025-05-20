package com.example.Zitapp.Controladores;

import com.example.Zitapp.Modelos.Appointments;
import com.example.Zitapp.Modelos.EditarCitaRequest;
import com.example.Zitapp.Servicios.AppointmentsServicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Appointments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AppointmControlador {
    @Autowired
    private AppointmentsServicios appointmentsServicios;

    //1. CrearCita
    // Crear una nueva cita
    @PostMapping
    public ResponseEntity<?> crearCita(@RequestBody Appointments appointments) {
        try {
            Appointments nuevaCita = appointmentsServicios.CrearCita(appointments);
            return ResponseEntity.ok(nuevaCita);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la cita: " + e.getMessage());
        }
    }

    //2. BuscarCita
    @GetMapping("/todas")
    public ResponseEntity<?> obtenercitas() {
        try {
            List<Appointments> citas = appointmentsServicios.ObtenerCitas();
            return ResponseEntity.ok(citas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las citas: " + e.getMessage());
        }
    }

    //3. buscarCita de cliente
    @GetMapping("/user/{idUser}")
    public ResponseEntity<?> getCitasPorUsuario(@PathVariable Long idUser) {
        try {
            List<Appointments> cita = appointmentsServicios.ObtenerCitasPorCliente(idUser);
            return ResponseEntity.ok(cita);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las citas del usuario: " + e.getMessage());
        }
    }

    // 4. buscar Citas de negocios
    @GetMapping("/busness/{idBusness}")
    public ResponseEntity<?> getCitasPorbusness(@PathVariable Long idBusness) {
        try {
            List<Appointments> cita = appointmentsServicios.ObtenerCitasPorNegocio(idBusness);
            return ResponseEntity.ok(cita);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las citas del negocio: " + e.getMessage());
        }
    }

    // 5. Confirmar cita
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<?> confirmarCita(@PathVariable Long id) {
        try {
            Appointments confirmada = appointmentsServicios.ConfirmarCita(id);
            return ResponseEntity.ok(confirmada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al confirmar la cita: " + e.getMessage());
        }
    }

    // 6. Cancelar cita
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarCita(@PathVariable Long id) {
        try {
            Appointments cancelar = appointmentsServicios.CancelarCita(id);
            return ResponseEntity.ok(cancelar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al cancelar la cita: " + e.getMessage());
        }
    }

    // 7.Finalizar Cita
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizarCita(@PathVariable Long id) {
        try {
            Appointments finalizar = appointmentsServicios.FinalizarCita(id);
            return ResponseEntity.ok(finalizar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al finalizar la cita: " + e.getMessage());
        }
    }

    //8.editar cita
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarCita(@PathVariable Long id, @RequestBody EditarCitaRequest request) {
        try {
            Appointments citaActualizada = appointmentsServicios.EditarCita(id, request.getNuevaFecha(), request.getNuevaHora());
            return ResponseEntity.ok(citaActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al editar la cita: " + e.getMessage());
        }
    }

    //9. buscar cita específica
    @GetMapping("/cita/{id}")
    public ResponseEntity<?> cita(@PathVariable Long id) {
        try {
            return appointmentsServicios.ObtenerCita(id)
                    .map(ResponseEntity::ok)  // Si Optional tiene valor, se responde con 200 OK
                    .orElse(ResponseEntity.notFound().build());  // Si Optional está vacío, se responde con 404 Not Found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la cita: " + e.getMessage());
        }
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        try {
            return ResponseEntity.ok("Prueba exitosa");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en la prueba: " + e.getMessage());
        }
    }
}