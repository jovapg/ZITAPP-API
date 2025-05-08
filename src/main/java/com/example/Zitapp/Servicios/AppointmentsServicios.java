package com.example.Zitapp.Servicios;

import com.example.Zitapp.Modelos.Appointments;
import com.example.Zitapp.Modelos.Business;
import com.example.Zitapp.Modelos.EstadoCita;
import com.example.Zitapp.Modelos.Users;
import com.example.Zitapp.Repositorios.AppointmentsRepositorio;
import com.example.Zitapp.Repositorios.BusinessRepositorio;
import com.example.Zitapp.Repositorios.UsersRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentsServicios {
    @Autowired
    private AppointmentsRepositorio appointmentsRepositorio;
    @Autowired
    private UsersRepositorio usersRepositorio;

    @Autowired
    private BusinessRepositorio businessRepositorio;


    public Appointments CrearCita(Appointments appointments) {
        if (appointments.getClient() == null || appointments.getBusiness() == null) {
            throw new IllegalArgumentException("El cliente o el negocio no pueden ser null");
        }

        // Buscar los objetos completos en la base de datos usando los IDs
        Users client = usersRepositorio.findById(appointments.getClient().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Business business = businessRepositorio.findById(appointments.getBusiness().getId())
                .orElseThrow(() -> new RuntimeException("Negocio no encontrado"));

        // Asignar los objetos completos a la cita
        appointments.setClient(client);
        appointments.setBusiness(business);

        // Guardar la cita
        return appointmentsRepositorio.save(appointments);
    }



    //Comfirmar cita
    public Appointments ConfirmarCita(long idCita) {
        Appointments cita = appointmentsRepositorio.findById(idCita)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        cita.setEstado(EstadoCita.CONFIRMADA);
        return appointmentsRepositorio.save(cita);
    }

    //cancelarCita
    public Appointments CancelarCita(long idCita) {
        Appointments cita = appointmentsRepositorio.findById(idCita)
                .orElseThrow(() -> new RuntimeException("cita no encontrada"));

        if (cita.getEstado() == EstadoCita.CONFIRMADA || cita.getEstado() == EstadoCita.FINALIZADA) {
            throw new RuntimeException("No se puede cancelar una cita ya confirmada o finalizada.");
        }

        cita.setEstado(EstadoCita.CANCELADA);
        return appointmentsRepositorio.save(cita);  // <- Guarda y ya
    }

    //finalizarCita
    public Appointments FinalizarCita(long idCita) {
        Appointments cita = appointmentsRepositorio.findById(idCita)
                .orElseThrow(() -> new RuntimeException("cita no encontrada"));

        cita.setEstado(EstadoCita.FINALIZADA);

        return appointmentsRepositorio.save(cita);

    }

    //editarcita
    public Appointments EditarCita(Long idcita, LocalDate NuevaFecha, LocalTime NuevaHoras) {
        Appointments cita = appointmentsRepositorio.findById(idcita)
                .orElseThrow(() -> new RuntimeException("cita no encontrada"));
        //validar si el estado actual  permita editarlo
        if (cita.getEstado() == EstadoCita.CONFIRMADA || cita.getEstado() == EstadoCita.FINALIZADA) {
            throw new RuntimeException("\"No se puede cancelar una cita ya confirmada o finalizada.");
        }

        //editar valores
        cita.setFecha(NuevaFecha);
        cita.setHora(NuevaHoras);
        cita.setEstado(EstadoCita.CONFIRMADA);//puedes dajar el mismo  estado si prefieres
        return appointmentsRepositorio.save(cita);
    }

    // buscar todas citas
    public List<Appointments> ObtenerCitas() {
        return appointmentsRepositorio.findAll();
    }

    // buscar una cita
    public Optional<Appointments> ObtenerCita(Long idcita) {
        return appointmentsRepositorio.findById(idcita);  // Esto devuelve un Optional
    }




    // buscar una cita por cliente
    public List<Appointments> ObtenerCitasPorCliente(Long idCliente) {
        return appointmentsRepositorio.findByClientId(idCliente);
    }

    // buscar una cita por negocio
    public List<Appointments> ObtenerCitasPorNegocio(Long idNegocio) {
        return appointmentsRepositorio.findByBusinessId(idNegocio);
    }


}
