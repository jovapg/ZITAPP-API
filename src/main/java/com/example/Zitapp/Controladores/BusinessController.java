// src/main/java/com/example/Zitapp/Controladores/BusinessController.java
package com.example.Zitapp.Controladores;

import com.example.Zitapp.DTO.BusinessRequestDTO;
import com.example.Zitapp.DTO.BusinessResponseDTO;
import com.example.Zitapp.Servicios.BusinessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Importar HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar endpoints de negocios.
 */
@RestController
@RequestMapping("/api/businesses") // Este es el RequestMapping base para el controlador
@Tag(name = "Negocios", description = "API para gestionar negocios")
public class BusinessController {

    private final BusinessService businessService;

    @Autowired
    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    // http://localhost:8081/api/businesses
    @GetMapping
    @Operation(summary = "Obtiene todos los negocios")
    public ResponseEntity<List<BusinessResponseDTO>> getAll() {
        return ResponseEntity.ok(businessService.getAll());
    }

    // http://localhost:8081/api/businesses/1
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un negocio por ID")
    public ResponseEntity<BusinessResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(businessService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo negocio")
    public ResponseEntity<BusinessResponseDTO> create(@Valid @RequestBody BusinessRequestDTO dto) {
        BusinessResponseDTO created = businessService.create(dto);
        return ResponseEntity.status(201).body(created);
    }

    // http://localhost:8081/api/businesses/1
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un negocio existente")
    public ResponseEntity<BusinessResponseDTO> update(@PathVariable Long id, @Valid @RequestBody BusinessRequestDTO dto) {
        BusinessResponseDTO updated = businessService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un negocio por ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        businessService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // --- ¡ BUSCAR NEGOCIO POR ID DE USUARIO! ---
    // La URL para este endpoint será: http://localhost:8081/api/businesses/byUser/{userId}
    @GetMapping("/byUser/{userId}")
    @Operation(summary = "Obtiene un negocio por el ID de usuario asociado")
    public ResponseEntity<BusinessResponseDTO> getBusinessByUserId(@PathVariable Long userId) {
        BusinessResponseDTO businessDTO = businessService.getByUserId(userId);
        if (businessDTO != null) {
            return ResponseEntity.ok(businessDTO);
        } else {
            // Devuelve 404 si no se encuentra ningún negocio asociado a ese usuario
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}