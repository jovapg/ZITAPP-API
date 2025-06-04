package com.example.Zitapp.Servicios;

import com.example.Zitapp.DTO.BusinnesServiceDTO;
import com.example.Zitapp.Modelos.BusinnesService;
import com.example.Zitapp.Modelos.Business;
import com.example.Zitapp.Repositorios.BusinessRepositorio;
import com.example.Zitapp.Repositorios.BusinessServiceRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessServiceServicio {

    private final BusinessServiceRepositorio serviceRepo;
    private final BusinessRepositorio businessRepo;

    @Autowired
    public BusinessServiceServicio(BusinessServiceRepositorio serviceRepo, BusinessRepositorio businessRepo) {
        this.serviceRepo = serviceRepo;
        this.businessRepo = businessRepo;
    }

    public List<BusinnesService> obtenerTodosLosServicios() {
        return serviceRepo.findAll();
    }

    public Optional<BusinnesService> obtenerServicioPorId(Long id) {
        return serviceRepo.findById(id);
    }

    public List<BusinnesService> obtenerServiciosPorNegocioId(Long businessId) {
        return serviceRepo.findByBusinessId(businessId);
    }

    public BusinnesService crearServicio(BusinnesService servicio, Long businessId) {
        Business business = businessRepo.findById(businessId)
                .orElseThrow(() -> new RuntimeException("Negocio no encontrado con ID: " + businessId));
        servicio.setBusiness(business);
        return serviceRepo.save(servicio);
    }

    public BusinnesService actualizarServicio(Long id, BusinnesServiceDTO serviceDetails) {
        BusinnesService servicio = serviceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + id));

        // Mapeo manual desde DTO a entidad
        servicio.setNombre(serviceDetails.getNombre());
        servicio.setDescripcion(serviceDetails.getDescripcion());
        servicio.setPrecio(serviceDetails.getPrecio());
        servicio.setDuracion(serviceDetails.getDuracion());

        return serviceRepo.save(servicio);
    }


    public void eliminarServicio(Long id) {
        BusinnesService servicio = serviceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con ID: " + id));
        serviceRepo.delete(servicio);
    }

    public List<BusinnesService> buscarServiciosPorRangoDePrecio(Long businessId, Double min, Double max) {
        return serviceRepo.findByBusinessIdAndPrecioBetween(businessId, min, max);
    }

    public List<BusinnesService> buscarServiciosPorNombre(Long businessId, String nombre) {
        return serviceRepo.findByBusinessIdAndNombreContainingIgnoreCase(businessId, nombre);
    }
}
