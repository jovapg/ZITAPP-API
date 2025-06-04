package com.example.Zitapp.Servicios;

import com.example.Zitapp.DTO.AppointmentCreateDTO;
import com.example.Zitapp.Modelos.*;
import com.example.Zitapp.Repositorios.AppointmentsRepositorio;
import com.example.Zitapp.Repositorios.BusinessRepositorio;
import com.example.Zitapp.Repositorios.BusinessServiceRepositorio;
import com.example.Zitapp.Repositorios.UsersRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Zitapp.Modelos.Notification.*;
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

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private BusinessServiceRepositorio serviceRepositorio;  // inyecta tu repositorio de servicios

    private String generarMensajeNotificacion(TipoNotification tipo, Users client, Appointments cita) {
        switch (tipo) {
            case CITA_CREADA:
                return String.format("Nueva cita creada con %s para el día %s a las %s.",
                        client.getNombre(),
                        cita.getFecha().toString(),
                        cita.getHora().toString());
            case CITA_CANCELADA:
                return String.format("La cita con %s para el día %s a las %s fue cancelada.",
                        client.getNombre(),
                        cita.getFecha().toString(),
                        cita.getHora().toString());
            case CITA_CONFIRMADA:
                return String.format("La cita con %s para el día %s a las %s fue confirmada.",
                        client.getNombre(),
                        cita.getFecha().toString(),
                        cita.getHora().toString());
            case CITA_FINALIZADA:
                return String.format("La cita con %s para el día %s a las %s fue finalizada.",
                        client.getNombre(),
                        cita.getFecha().toString(),
                        cita.getHora().toString());
            default:
                return "Tienes una nueva notificación.";
        }
    }


    public Appointments CrearCita(AppointmentCreateDTO dto) {
        if (dto.getClientId() == null || dto.getBusinessId() == null || dto.getServiceId() == null) {
            throw new IllegalArgumentException("Cliente, negocio y servicio no pueden ser null");
        }

        Users client = usersRepositorio.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Business business = businessRepositorio.findById(dto.getBusinessId())
                .orElseThrow(() -> new RuntimeException("Negocio no encontrado"));

        BusinnesService service = serviceRepositorio.findById(dto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        Appointments appointments = new Appointments();
        appointments.setClient(client);
        appointments.setBusiness(business);
        appointments.setService(service);  // asignar el servicio
        appointments.setFecha(dto.getFecha());
        appointments.setHora(dto.getHora());
        appointments.setEstado(EstadoCita.PENDIENTE);

        Appointments savedAppointment = appointmentsRepositorio.save(appointments);

        // Notificaciones aquí igual

        return savedAppointment;
    }

    public Appointments ConfirmarCita(long idCita) {
        Appointments cita = appointmentsRepositorio.findById(idCita)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        cita.setEstado(EstadoCita.CONFIRMADA);
        Appointments confirmedAppointment = appointmentsRepositorio.save(cita);

        Users client = cita.getClient();
        Business business = cita.getBusiness();

        String mensajeCliente = generarMensajeNotificacion(TipoNotification.CITA_CONFIRMADA, client, confirmedAppointment);

        // Notificación solo para el cliente
        notificationService.crearNotificasion(
                business.getId(),               // quien envía la notificación (negocio)
                TipoUsuario.NEGOCIO,
                client.getId(),                 // quien recibe la notificación (cliente)
                TipoUsuario.CLIENTE,
                mensajeCliente,
                TipoNotification.CITA_CONFIRMADA,
                confirmedAppointment.getId()
        );

        return confirmedAppointment;
    }

    public Appointments CancelarCita(long idCita) {
        Appointments cita = appointmentsRepositorio.findById(idCita)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        if (cita.getEstado() == EstadoCita.CONFIRMADA || cita.getEstado() == EstadoCita.FINALIZADA) {
            throw new RuntimeException("No se puede cancelar una cita ya confirmada o finalizada.");
        }

        cita.setEstado(EstadoCita.CANCELADA);
        Appointments cancelledAppointment = appointmentsRepositorio.save(cita);
        Users client = cita.getClient();
        Business business = cita.getBusiness();

        String mensajeCliente = generarMensajeNotificacion(TipoNotification.CITA_CANCELADA, client, cancelledAppointment);
        String mensajeNegocio = generarMensajeNotificacion(TipoNotification.CITA_CANCELADA, client, cancelledAppointment);

        // Notificación para el cliente
        notificationService.crearNotificasion(
                business.getId(),
                TipoUsuario.NEGOCIO,
                client.getId(),
                TipoUsuario.CLIENTE,
                mensajeCliente,
                TipoNotification.CITA_CANCELADA,
                cancelledAppointment.getId()
        );

        // Notificación para el negocio
        notificationService.crearNotificasion(
                client.getId(),
                TipoUsuario.CLIENTE,
                business.getId(),
                TipoUsuario.NEGOCIO,
                mensajeNegocio,
                TipoNotification.CITA_CANCELADA,
                cancelledAppointment.getId()
        );

        return cancelledAppointment;
    }

    public Appointments FinalizarCita(long idCita) {
        Appointments cita = appointmentsRepositorio.findById(idCita)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        cita.setEstado(EstadoCita.FINALIZADA);
        Appointments finalizedAppointment = appointmentsRepositorio.save(cita);

        Users client = cita.getClient();
        Business business = cita.getBusiness();

        String mensajeCliente = generarMensajeNotificacion(TipoNotification.CITA_FINALIZADA, client, finalizedAppointment);

        // Notificar solo al cliente que la cita fue finalizada
        notificationService.crearNotificasion(
                business.getId(),               // emisor (negocio)
                TipoUsuario.NEGOCIO,
                client.getId(),                 // receptor (cliente)
                TipoUsuario.CLIENTE,
                mensajeCliente,
                TipoNotification.CITA_FINALIZADA,
                finalizedAppointment.getId()
        );

        return finalizedAppointment;
    }


    public Appointments editarCita(Long idCita, EditarCitaRequest request) {
        Appointments cita = appointmentsRepositorio.findById(idCita)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        if (cita.getEstado() == EstadoCita.CONFIRMADA || cita.getEstado() == EstadoCita.FINALIZADA) {
            throw new RuntimeException("No se puede editar una cita ya confirmada o finalizada.");
        }

        cita.setFecha(request.getNuevaFecha());
        cita.setHora(request.getNuevaHora());
        cita.setEstado(EstadoCita.PENDIENTE);

        return appointmentsRepositorio.save(cita);
    }


    public List<Appointments> ObtenerCitas() {
        return appointmentsRepositorio.findAll();
    }

    public Optional<Appointments> ObtenerCita(Long idCita) {
        return appointmentsRepositorio.findById(idCita);
    }

    public List<Appointments> ObtenerCitasPorCliente(Long idCliente) {
        return appointmentsRepositorio.findByClientIdWithService(idCliente);
    }

    public List<Appointments> ObtenerCitasPorNegocio(Long idNegocio) {
        return appointmentsRepositorio.findByBusinessId(idNegocio);
    }

    public List<Appointments> ObtenerCitasPendientesPorNegocio(Long idNegocio) {
        return appointmentsRepositorio.findByBusinessIdAndEstado(idNegocio, EstadoCita.PENDIENTE);
    }

    public void EliminarCita(Long id) throws Exception {
        Optional<Appointments> cita = appointmentsRepositorio.findById(id);
        if (cita.isPresent()) {
            appointmentsRepositorio.deleteById(id);
        } else {
            throw new Exception("La cita con ID " + id + " no existe.");
        }
    }
}
