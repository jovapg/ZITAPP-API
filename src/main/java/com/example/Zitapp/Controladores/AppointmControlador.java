package com.example.Zitapp.Controladores;

import com.example.Zitapp.Modelos.Appointments;
import com.example.Zitapp.Modelos.EditarCitaRequest;
import com.example.Zitapp.Servicios.AppointmentsServicios;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Appointments")

public class AppointmControlador {
    @Autowired
    private AppointmentsServicios appointmentsServicios;

    //1. CrearCita
    // Crear una nueva cita
    @PostMapping
    public Appointments crearCita(@RequestBody Appointments appointments) {
        return appointmentsServicios.CrearCita(appointments);
    }



    //2. BuscarCita
    @GetMapping("/todas")
    public ResponseEntity<List<Appointments>> obtenercitas() {
        List<Appointments> citas = appointmentsServicios.ObtenerCitas();
        return ResponseEntity.ok(citas);
    }

    //3. buscarCita de cliente
    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Appointments>> getCitasPorUsuario(@PathVariable Long idUser) {
        List<Appointments> cita = appointmentsServicios.ObtenerCitasPorCliente(idUser);
        return ResponseEntity.ok(cita);

    }

    // 4. buscar Citas de negocios
    @GetMapping("/busness/{idBusness}")
    public ResponseEntity<List<Appointments>> getCitasPorbusness( @PathVariable Long idBusness) {
        List<Appointments> cita = appointmentsServicios.ObtenerCitasPorNegocio(idBusness);

        return ResponseEntity.ok(cita);
    }

    // 5. Confirmar cita
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Appointments> confirmarCita(@PathVariable Long id) {
        Appointments confirmada = appointmentsServicios.ConfirmarCita(id);
        return ResponseEntity.ok(confirmada);
    }

    // 6. Cancelar cita
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Appointments> CancelarCita(@PathVariable Long id) {
        Appointments Cancelar = appointmentsServicios.CancelarCita(id);
        return ResponseEntity.ok(Cancelar);
    }

    // 7.Finalizar Cita
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<Appointments> FinalizarCita(@PathVariable Long id) {
        Appointments Finalizar = appointmentsServicios.FinalizarCita(id);
        return ResponseEntity.ok(Finalizar);
    }

    //8.editar cita
    @PutMapping("/editar/{id}")
    public ResponseEntity<Appointments> EditarCita(@PathVariable Long id, @RequestBody  EditarCitaRequest  request) {
        Appointments citaActualizada = appointmentsServicios.EditarCita(id, request.getNuevaFecha(),request.getNuevaHora());

        return ResponseEntity.ok(citaActualizada);
    }
    //9. buscar cita  especifica
    @GetMapping("/cita/{id}")
    public ResponseEntity<Appointments> cita(@PathVariable Long id) {
        return appointmentsServicios.ObtenerCita(id)
                .map(ResponseEntity::ok)  // Si Optional tiene valor, se responde con 200 OK
                .orElse(ResponseEntity.notFound().build());  // Si Optional está vacío, se responde con 404 Not Found
    }



    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Prueba exitosa");
    }



}
