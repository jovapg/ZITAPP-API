package com.example.Zitapp.Servicios;

import com.example.Zitapp.Modelos.Business;
import com.example.Zitapp.Modelos.BusinnesService;
import com.example.Zitapp.Repositorios.BusinessRepositorio;
import com.example.Zitapp.Repositorios.BusinessServiceRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessServiceServicio {

    private final BusinessServiceRepositorio serviceRepositorio;
    private final BusinessRepositorio businessRepositorio;

    @Autowired
    public BusinessServiceServicio(BusinessServiceRepositorio serviceRepositorio, BusinessRepositorio businessRepositorio) {
        this.serviceRepositorio = serviceRepositorio;
        this.businessRepositorio = businessRepositorio;
    }

    // Obtener todos los servicios
    public List<BusinnesService> obtenerTodosLosServicios() {
        return serviceRepositorio.findAll();
    }

    // Obtener un servicio por ID
    public Optional<BusinnesService> obtenerServicioPorId(Long id) {
        return serviceRepositorio.findById(id);
    }

    // Obtener servicios por ID de negocio
    public List<BusinnesService> obtenerServiciosPorNegocioId(Long businessId) {
        return serviceRepositorio.findByBusinessId(businessId);
    }

    // Crear un nuevo servicio
    public BusinnesService crearServicio(BusinnesService service, Long businessId) {
        Optional<Business> businessOptional = businessRepositorio.findById(businessId);

        if (businessOptional.isPresent()) {
            Business business = businessOptional.get();
            service.setBusiness(business);
            return serviceRepositorio.save(service);
        } else {
            throw new RuntimeException("Negocio no encontrado con ID: " + businessId);
        }
    }

    // Actualizar un servicio existente
    public BusinnesService actualizarServicio(Long id, BusinnesService serviceDetails) {
        Optional<BusinnesService> serviceOptional = serviceRepositorio.findById(id);

        if (serviceOptional.isPresent()) {
            BusinnesService serviceExistente = serviceOptional.get();
            serviceExistente.setNombre(serviceDetails.getNombre());
            serviceExistente.setDescripcion(serviceDetails.getDescripcion());
            serviceExistente.setPrecio(serviceDetails.getPrecio());
            serviceExistente.setDuration(serviceDetails.getDuration());

            return serviceRepositorio.save(serviceExistente);
        } else {
            throw new RuntimeException("Servicio no encontrado con ID: " + id);
        }
    }

    // Eliminar un servicio
    public void eliminarServicio(Long id) {
        Optional<BusinnesService> serviceOptional = serviceRepositorio.findById(id);

        if (serviceOptional.isPresent()) {
            serviceRepositorio.deleteById(id);
        } else {
            throw new RuntimeException("Servicio no encontrado con ID: " + id);
        }
    }

    // Buscar servicios por rango de precios
    public List<BusinnesService> buscarServiciosPorRangoDePrecio(Long businessId, Double precioMin, Double precioMax) {
        return serviceRepositorio.findByBusinessIdAndPrecioBetween(businessId, precioMin, precioMax);
    }

    // Buscar servicios por nombre
    public List<BusinnesService> buscarServiciosPorNombre(Long businessId, String nombre) {
        return serviceRepositorio.findByBusinessIdAndNombreContainingIgnoreCase(businessId, nombre);
    }
}