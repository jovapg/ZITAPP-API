package com.example.Zitapp.Servicios;

import com.example.Zitapp.DTO.AvailabilityDTO;
import com.example.Zitapp.Modelos.Availability;
import com.example.Zitapp.Modelos.Business;
import com.example.Zitapp.Modelos.Appointments; // Importar el modelo de Appointments
import com.example.Zitapp.Repositorios.AvailabilityRepositorio;
import com.example.Zitapp.Repositorios.BusinessRepositorio;
import com.example.Zitapp.Repositorios.AppointmentsRepositorio; // Importar el repositorio de Appointments
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvailabilityServicio {

    private final AvailabilityRepositorio availabilityRepositorio;
    private final BusinessRepositorio businessRepositorio;
    private final AppointmentsRepositorio appointmentsRepositorio; // Inyectar AppointmentsRepositorio

    // Constructor actualizado para inyectar AppointmentsRepositorio
    public AvailabilityServicio(AvailabilityRepositorio availabilityRepositorio,
                                BusinessRepositorio businessRepositorio,
                                AppointmentsRepositorio appointmentsRepositorio) {
        this.availabilityRepositorio = availabilityRepositorio;
        this.businessRepositorio = businessRepositorio;
        this.appointmentsRepositorio = appointmentsRepositorio; // Asignar el repositorio
    }

    public Availability crearDisponibilidad(Availability disponibilidad, Long businessId) {
        Business business = businessRepositorio.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Negocio no encontrado con ID: " + businessId));
        disponibilidad.setBusiness(business);
        return availabilityRepositorio.save(disponibilidad);
    }

    public List<Availability> obtenerTodas() {
        return availabilityRepositorio.findAll();
    }

    public Optional<Availability> obtenerPorId(Integer id) {
        return availabilityRepositorio.findById(id);
    }

    public Availability actualizarDisponibilidad(Integer id, AvailabilityDTO datosActualizados) {
        Availability disponibilidad = availabilityRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Disponibilidad no encontrada con ID: " + id));

        disponibilidad.setDia(datosActualizados.getDia());
        disponibilidad.setHoraInicio(datosActualizados.getHoraInicio());
        disponibilidad.setHoraFin(datosActualizados.getHoraFin());

        return availabilityRepositorio.save(disponibilidad);
    }

    public boolean eliminarDisponibilidad(Integer id) {
        Optional<Availability> disponibilidad = availabilityRepositorio.findById(id);
        if (disponibilidad.isPresent()) {
            availabilityRepositorio.delete(disponibilidad.get());
            return true;
        }
        return false;
    }

    /**
     * Obtiene las horas disponibles para un negocio en una fecha específica.
     * Esta lógica asume que las citas ya agendadas ocupan slots de 30 minutos
     * y que la disponibilidad se define en bloques de 30 minutos.
     *
     * @param businessId ID del negocio
     * @param date       Fecha para la cual se consulta la disponibilidad
     * @return Una lista de Strings representando las horas disponibles (ej. "09:00", "09:30")
     */
    public List<String> obtenerHorasDisponiblesPorNegocioYFecha(Long businessId, LocalDate date) {
        // 1. Convertir LocalDate a el día de la semana en español (para coincidir con tu modelo 'dia')
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        String diaSemana = getDiaSemanaEnEspanol(dayOfWeek);

        // 2. Obtener la disponibilidad base del negocio para ese día de la semana
        List<Availability> disponibilidadesBase = availabilityRepositorio.findByBusinessIdAndDia(businessId, diaSemana);

        // Si no hay disponibilidad definida para ese día, no hay horas disponibles
        if (disponibilidadesBase.isEmpty()) {
            return new ArrayList<>();
        }

        // 3. Generar todos los posibles slots de 30 minutos basados en la disponibilidad base
        List<LocalTime> allPossibleSlots = new ArrayList<>();
        for (Availability av : disponibilidadesBase) {
            LocalTime currentTime = av.getHoraInicio();
            while (currentTime.isBefore(av.getHoraFin())) {
                allPossibleSlots.add(currentTime);
                currentTime = currentTime.plusMinutes(30); // Asumimos slots de 30 minutos
            }
        }

        // 4. Obtener las citas ya agendadas para este negocio y fecha
        List<Appointments> citasAgendadas = appointmentsRepositorio.findByBusinessIdAndFecha(businessId, date);

        // Extraer las horas de inicio de las citas agendadas
        List<LocalTime> occupiedSlots = citasAgendadas.stream()
                .map(Appointments::getHora) // Asumiendo que tu modelo Appointments tiene un método getHora() que devuelve LocalTime
                .collect(Collectors.toList());


        // 5. Filtrar los slots ocupados de los slots posibles
        List<LocalTime> availableSlots = allPossibleSlots.stream()
                .filter(slot -> !occupiedSlots.contains(slot))
                .collect(Collectors.toList());

        // 6. Formatear a String para el frontend (ej. "09:00")
        return availableSlots.stream()
                .map(LocalTime::toString) // LocalTime.toString() ya da el formato HH:MM o HH:MM:SS
                .collect(Collectors.toList());
    }

    // Método auxiliar para convertir DayOfWeek a String en español
    private String getDiaSemanaEnEspanol(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY: return "Lunes";
            case TUESDAY: return "Martes";
            case WEDNESDAY: return "Miércoles";
            case THURSDAY: return "Jueves";
            case FRIDAY: return "Viernes";
            case SATURDAY: return "Sábado";
            case SUNDAY: return "Domingo";
            default: return ""; // O lanza una excepción para un día no válido
        }
    }

    //obtener la disponibilidad por negocio
    public List<Availability> obtenerDisponibilidadPorBusinessId(Integer businessId) {
        return availabilityRepositorio.findByBusinessId(businessId);
    }
}