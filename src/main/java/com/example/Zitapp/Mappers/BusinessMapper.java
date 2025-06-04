package com.example.Zitapp.Mappers;

import com.example.Zitapp.DTO.BusinessRequestDTO;
import com.example.Zitapp.DTO.BusinessResponseDTO;
import com.example.Zitapp.Modelos.Business;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * Mapper para convertir entre entidad Business y sus DTOs.
 */
@Mapper(componentModel = "spring")
public interface BusinessMapper {

    BusinessMapper INSTANCE = Mappers.getMapper(BusinessMapper.class);

    /**
     * Convierte DTO de request a entidad Business.
     */
    Business toEntity(BusinessRequestDTO dto);

    /**
     * Convierte entidad Business a DTO de respuesta.
     */
    BusinessResponseDTO toDTO(Business business);

    /**
     * Actualiza entidad Business existente con datos del DTO.
     */
    void updateEntityFromDTO(BusinessRequestDTO dto, @MappingTarget Business business);
}
