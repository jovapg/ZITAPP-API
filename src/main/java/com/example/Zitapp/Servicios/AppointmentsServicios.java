package com.example.Zitapp.Servicios;

// --- Importaciones de Entidades (Modelos) ---
import com.example.Zitapp.Modelos.Appointments;
import com.example.Zitapp.Modelos.Users;
import com.example.Zitapp.Modelos.Business;
import com.example.Zitapp.Modelos.BusinnesService;
import com.example.Zitapp.Modelos.EstadoCita; // Importado para el enum

// --- Importaciones de Repositorios ---
import com.example.Zitapp.Repositorios.AppointmentsRepositorio;
import com.example.Zitapp.Repositorios.UsersRepositorio;
import com.example.Zitapp.Repositorios.BusinessRepositorio;
import com.example.Zitapp.Repositorios.BusinessServiceRepositorio;


// --- Importaciones de DTOs ---
import com.example.Zitapp.DTO.AppointmentCreateDTO;
import com.example.Zitapp.DTO.AppointmentDetailsDTO;


// --- Otros imports de Spring y Java ---
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AppointmentsServicios {

    @Autowired
    private AppointmentsRepositorio appointmentsRepositorio;

    @Autowired
    private UsersRepositorio usersRepository;

    @Autowired
    private BusinessRepositorio businessRepository;

    @Autowired
    private BusinessServiceRepositorio businessServiceRepository;


    // --- Métodos de Creación, Obtención, Actualización, Eliminación ---

    // Método para crear una cita (requiere AppointmentCreateDTO)
    @Transactional
    public Appointments createAppointment(AppointmentCreateDTO dto) {
        Users client = usersRepository.findById(dto.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + dto.getClientId()));
        Business business = businessRepository.findById(dto.getBusinessId())
                .orElseThrow(() -> new IllegalArgumentException("Negocio no encontrado con ID: " + dto.getBusinessId()));
        BusinnesService service = businessServiceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new IllegalArgumentException("Servicio no encontrado con ID: " + dto.getServiceId()));

        Appointments appointment = new Appointments();
        appointment.setClient(client);
        appointment.setBusiness(business);
        appointment.setService(service);
        appointment.setFecha(dto.getFecha());
        appointment.setHora(dto.getHora());
        appointment.setEstado(EstadoCita.PENDIENTE);

        return appointmentsRepositorio.save(appointment);
    }

    /**
     * Obtiene una cita por su ID y la mapea a un DTO de detalles para evitar sobre-serialización.
     * @param id ID de la cita
     * @return Optional con el AppointmentDetailsDTO si se encuentra la cita
     */
    @Transactional(readOnly = true)
    public Optional<AppointmentDetailsDTO> ObtenerCita(Long id) {
        Optional<Appointments> appointmentOptional = appointmentsRepositorio.findById(id);

        if (appointmentOptional.isPresent()) {
            Appointments appointment = appointmentOptional.get();

            AppointmentDetailsDTO dto = new AppointmentDetailsDTO();
            dto.setId(appointment.getId());
            dto.setFecha(appointment.getFecha());
            dto.setHora(appointment.getHora());
            dto.setEstado(appointment.getEstado().name());

            if (appointment.getClient() != null) {
                Users client = appointment.getClient();
                dto.setClient(new AppointmentDetailsDTO.ClientInfoDTO(client.getId(), client.getNombre(), client.getEmail(), client.getTelefono()));
            }

            if (appointment.getBusiness() != null) {
                Business business = appointment.getBusiness();
                dto.setBusiness(new AppointmentDetailsDTO.BusinessInfoDTO(business.getId(), business.getNombre(), business.getCategoria(), business.getDireccion(), business.getTelefono()));
            }

            if (appointment.getService() != null) {
                BusinnesService service = appointment.getService();
                dto.setService(new AppointmentDetailsDTO.ServiceInfoDTO(service.getId(), service.getNombre(), service.getDescripcion(), service.getPrecio(), service.getDuracion()));
            }

            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    // Método para obtener todas las citas
    @Transactional(readOnly = true)
    public List<Appointments> obtenerTodos() {
        return appointmentsRepositorio.findAll();
    }

    // Método para obtener citas por negocio (asume que el repositorio tiene findByBusinessId)
    // Usamos findByBusinessIdWithClientAndService para cargar las relaciones necesarias para el DTO
    @Transactional(readOnly = true)
    public List<Appointments> obtenerCitasPorNegocio(Long idBusiness) {
        // ¡CORRECCIÓN AQUÍ! Usamos el método que carga las relaciones
        return appointmentsRepositorio.findByBusinessIdWithClientAndService(idBusiness);
    }

    // Método para obtener citas por cliente (asume que el repositorio tiene findByClientIdWithService)
    @Transactional(readOnly = true)
    public List<Appointments> obtenerCitasPorCliente(Long idClient) {
        // ¡CORRECCIÓN AQUÍ! Usamos el método que carga las relaciones
        return appointmentsRepositorio.findByClientIdWithService(idClient);
    }

    // Método para actualizar el estado de una cita
    @Transactional
    public AppointmentDetailsDTO updateAppointmentStatus(Long id, String newStatus) {
        Appointments appointment = appointmentsRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada con ID: " + id));

        try {
            appointment.setEstado(EstadoCita.valueOf(newStatus.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Estado de cita inválido: " + newStatus);
        }

        Appointments updatedAppointment = appointmentsRepositorio.save(appointment);
        return ObtenerCita(updatedAppointment.getId()).orElseThrow(() -> new RuntimeException("Error al mapear cita actualizada a DTO"));
    }

    // Método para eliminar una cita
    @Transactional
    public void deleteAppointment(Long id) {
        if (!appointmentsRepositorio.existsById(id)) {
            throw new IllegalArgumentException("Cita no encontrada con ID: " + id);
        }
        appointmentsRepositorio.deleteById(id);
    }

    // Método para verificar si existe una cita por ID
    public boolean existePorId(Long id) {
        return appointmentsRepositorio.existsById(id);
    }
}