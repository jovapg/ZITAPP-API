package com.example.Zitapp.Controladores;

import com.example.Zitapp.Modelos.Business;
import com.example.Zitapp.Servicios.BusinessServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/business")

public class BusinessControlador {

    @Autowired
    private BusinessServicio businessServicio;

    /**
     * Crear un nuevo negocio
     * @param business datos del negocio a crear
     * @return el negocio creado
     */
    @PostMapping
    public ResponseEntity<?> crearBusiness(@RequestBody Business business) {
        try {
            Business nuevoBusiness = businessServicio.saveBusiness(business);
            return new ResponseEntity<>(nuevoBusiness, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el negocio: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtener todos los negocios
     * @return lista de todos los negocios
     */
    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        try {
            List<Business> businesses = businessServicio.getAll();
            return new ResponseEntity<>(businesses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener los negocios: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtener un negocio por su ID
     * @param id identificador del negocio
     * @return el negocio encontrado o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable("id") Long id) {
        try {
            Optional<Business> business = businessServicio.getById(id);

            if (business.isPresent()) {
                return new ResponseEntity<>(business.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Negocio no encontrado con id: " + id,
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener el negocio: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualizar un negocio existente
     * @param id identificador del negocio a actualizar
     * @param businessDetalles datos actualizados del negocio
     * @return el negocio actualizado o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarBusiness(@PathVariable("id") Long id,
                                                @RequestBody Business businessDetalles) {
        try {
            Optional<Business> businessActualizado = businessServicio.updateBusiness(id, businessDetalles);

            if (businessActualizado.isPresent()) {
                return new ResponseEntity<>(businessActualizado.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Negocio no encontrado con id: " + id,
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el negocio: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Eliminar un negocio
     * @param id identificador del negocio a eliminar
     * @return 204 si se eliminó correctamente, 404 si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarBusiness(@PathVariable("id") Long id) {
        try {
            boolean eliminado = businessServicio.deleteBusiness(id);

            if (eliminado) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Negocio no encontrado con id: " + id,
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el negocio: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> obtenerBusinessPorUserId(@PathVariable("userId") Long userId) {
        try {
            Optional<Business> business = businessServicio.getByUserId(userId);
            if (business.isPresent()) {
                return new ResponseEntity<>(business.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No se encontró un negocio con el userId: " + userId,
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener el negocio por userId: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}