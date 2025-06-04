package com.example.Zitapp.Controladores;

import com.example.Zitapp.DTO.BusinessRequestDTO;
import com.example.Zitapp.DTO.BusinessResponseDTO;
import com.example.Zitapp.Servicios.BusinessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar endpoints de negocios.
 */
@RestController
@RequestMapping("/api/businesses")
@Tag(name = "Negocios", description = "API para gestionar negocios")
public class BusinessController {

    private final BusinessService businessService;

    @Autowired
    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping
    @Operation(summary = "Obtiene todos los negocios")
    public ResponseEntity<List<BusinessResponseDTO>> getAll() {
        return ResponseEntity.ok(businessService.getAll());
    }

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
}
