package com.example.Zitapp.Servicios;

import com.example.Zitapp.DTO.BusinessRequestDTO;
import com.example.Zitapp.DTO.BusinessResponseDTO;
import com.example.Zitapp.Mappers.BusinessMapper;
import com.example.Zitapp.Modelos.Business;
import com.example.Zitapp.Repositorios.BusinessRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para lógica de negocio relacionada con negocios.
 */
@Service
public class BusinessService {

    private final BusinessRepositorio businessRepositorio;
    private final BusinessMapper businessMapper;

    @Autowired
    public BusinessService(BusinessRepositorio businessRepositorio, BusinessMapper businessMapper) {
        this.businessRepositorio = businessRepositorio;
        this.businessMapper = businessMapper;
    }

    /**
     * Obtiene todos los negocios en forma de lista DTO.
     */
    public List<BusinessResponseDTO> getAll() {
        return businessRepositorio.findAll()
                .stream()
                .map(businessMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un negocio por su id.
     */
    public BusinessResponseDTO getById(Long id) {
        Business business = businessRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Negocio no encontrado con id " + id));
        return businessMapper.toDTO(business);
    }

    /**
     * Crea un nuevo negocio.
     */
    public BusinessResponseDTO create(BusinessRequestDTO dto) {
        Business business = businessMapper.toEntity(dto);
        Business saved = businessRepositorio.save(business);
        return businessMapper.toDTO(saved);
    }

    /**
     * Actualiza un negocio existente.
     */
    public BusinessResponseDTO update(Long id, BusinessRequestDTO dto) {
        Business business = businessRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Negocio no encontrado con id " + id));
        businessMapper.updateEntityFromDTO(dto, business);
        Business saved = businessRepositorio.save(business);
        return businessMapper.toDTO(saved);
    }

    /**
     * Elimina un negocio por id.
     */
    public void delete(Long id) {
        if (!businessRepositorio.existsById(id)) {
            throw new RuntimeException("Negocio no encontrado con id " + id);
        }
        businessRepositorio.deleteById(id);
    }
}
