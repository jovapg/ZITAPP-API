// src/main/java/com/example/Zitapp/Servicios/BusinessService.java
package com.example.Zitapp.Servicios;

import com.example.Zitapp.DTO.BusinessRequestDTO;
import com.example.Zitapp.DTO.BusinessResponseDTO;
import com.example.Zitapp.Mappers.BusinessMapper;
import com.example.Zitapp.Modelos.Business;
import com.example.Zitapp.Modelos.Users; // Importa Users
import com.example.Zitapp.Repositorios.BusinessRepositorio;
import com.example.Zitapp.Repositorios.UsersRepositorio; // Importa UsersRepositorio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para lógica de negocio relacionada con negocios.
 */
@Service
public class BusinessService {

    private final BusinessRepositorio businessRepositorio;
    private final BusinessMapper businessMapper;
    private final UsersRepositorio usersRepositorio; // Inyectado para buscar Users

    @Autowired
    public BusinessService(BusinessRepositorio businessRepositorio, BusinessMapper businessMapper, UsersRepositorio usersRepositorio) {
        this.businessRepositorio = businessRepositorio;
        this.businessMapper = businessMapper;
        this.usersRepositorio = usersRepositorio;
    }

    /**
     * Obtiene todos los negocios en forma de lista DTO.
     */
    @Transactional(readOnly = true)
    public List<BusinessResponseDTO> getAll() {
        return businessRepositorio.findAll()
                .stream()
                .map(businessMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un negocio por su id.
     */
    @Transactional(readOnly = true)
    public BusinessResponseDTO getById(Long id) {
        Business business = businessRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Negocio no encontrado con id " + id));
        return businessMapper.toDTO(business);
    }

    /**
     * Crea un nuevo negocio.
     */
    @Transactional
    public BusinessResponseDTO create(BusinessRequestDTO dto) {
        // Busca el usuario por el idUsuario del DTO
        Users user = usersRepositorio.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario con id " + dto.getIdUsuario() + " no encontrado para asociar al negocio."));

        Business business = businessMapper.toEntity(dto); // Mapea los campos básicos
        business.setUsuario(user); // ¡Asigna la entidad Users!

        Business saved = businessRepositorio.save(business);
        return businessMapper.toDTO(saved);
    }

    /**
     * Actualiza un negocio existente.
     */
    @Transactional
    public BusinessResponseDTO update(Long id, BusinessRequestDTO dto) {
        Business business = businessRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Negocio no encontrado con id " + id));

        // Si el idUsuario en el DTO es diferente al actual, busca el nuevo usuario
        // Nota: asumo que business.getUsuario() nunca será null aquí después de la creación/persistencia
        if (!business.getUsuario().getId().equals(dto.getIdUsuario())) {
            Users newUser = usersRepositorio.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Nuevo usuario con id " + dto.getIdUsuario() + " no encontrado para actualizar el negocio."));
            business.setUsuario(newUser); // Asigna el nuevo objeto Users
        }

        businessMapper.updateEntityFromDTO(dto, business); // Actualiza los demás campos
        Business saved = businessRepositorio.save(business);
        return businessMapper.toDTO(saved);
    }

    /**
     * Elimina un negocio por id.
     */
    @Transactional
    public void delete(Long id) {
        if (!businessRepositorio.existsById(id)) {
            throw new RuntimeException("Negocio no encontrado con id " + id);
        }
        businessRepositorio.deleteById(id);
    }

    // --- ¡MÉTODO CORREGIDO! ---
    /**
     * Obtiene un negocio por el ID de usuario asociado.
     * Retorna null si no se encuentra ningún negocio con ese ID de usuario.
     */
    @Transactional(readOnly = true)
    public BusinessResponseDTO getByUserId(Long userId) {
        // La línea 92 (donde se generó el error) era: return businessRepositorio.findByIdUsuario(userId)
        // Se corrige a:
        return businessRepositorio.findByUsuario_Id(userId) // <-- ESTE ES EL CAMBIO CLAVE
                .map(businessMapper::toDTO)
                .orElse(null);
    }
}