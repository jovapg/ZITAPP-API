// src/main/java/com/example/Zitapp/Mappers/BusinessMapper.java
package com.example.Zitapp.Mappers;

import com.example.Zitapp.DTO.BusinessRequestDTO;
import com.example.Zitapp.DTO.BusinessResponseDTO;
import com.example.Zitapp.Modelos.Business;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping; // ¡Importar la anotación Mapping!
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
     * Importante: Ignoramos el mapeo directo del campo 'usuario' (entidad Users)
     * porque el BusinessRequestDTO tiene un Long idUsuario.
     * La lógica de buscar el objeto Users y asignarlo se hará en el servicio.
     * También ignoramos las listas de servicios, disponibilidades y citas si no las manejas aquí directamente.
     */
    @Mapping(target = "usuario", ignore = true) // <--- IGNORA el campo 'usuario' (objeto Users)
    @Mapping(target = "services", ignore = true) // Ignorar si no mapeas servicios directamente
    @Mapping(target = "availabilities", ignore = true) // Ignorar si no mapeas disponibilidades directamente
    @Mapping(target = "appointments", ignore = true) // Ignorar si no mapeas citas directamente
    Business toEntity(BusinessRequestDTO dto);

    /**
     * Convierte entidad Business a DTO de respuesta.
     * Aquí, mapeamos el 'idUsuario' del DTO a partir del 'id' del objeto 'usuario' de la entidad Business.
     */
    @Mapping(target = "idUsuario", source = "usuario.id") // <--- MAPEO CLAVE: De Business.usuario.id a BusinessResponseDTO.idUsuario
    BusinessResponseDTO toDTO(Business business);

    /**
     * Actualiza entidad Business existente con datos del DTO.
     * Similar a toEntity, ignoramos el campo 'usuario' ya que el servicio lo maneja.
     * También ignoramos el ID para no actualizarlo accidentalmente en una actualización.
     */
    @Mapping(target = "id", ignore = true) // No actualizar el ID
    @Mapping(target = "usuario", ignore = true) // <--- IGNORA el campo 'usuario' (objeto Users)
    @Mapping(target = "services", ignore = true)
    @Mapping(target = "availabilities", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    void updateEntityFromDTO(BusinessRequestDTO dto, @MappingTarget Business business);
}