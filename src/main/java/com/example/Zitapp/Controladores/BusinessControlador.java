package com.example.Zitapp.Controladores;

import com.example.Zitapp.Modelos.Business;
import com.example.Zitapp.Servicios.BusinessServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Business> crearBusiness(@RequestBody Business business) {
        Business nuevoBusiness = businessServicio.saveBusiness(business);
        return new ResponseEntity<>(nuevoBusiness, HttpStatus.CREATED);
    }

    /**
     * Obtener todos los negocios
     * @return lista de todos los negocios
     */
    @GetMapping
    public List<Business> obtenerTodos() {
        List<Business> businesses = businessServicio.getAll();
        return businesses;
    }

    /**
     * Obtener un negocio por su ID
     * @param id identificador del negocio
     * @return el negocio encontrado o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Business> obtenerPorId(@PathVariable("id") Long id) {
        Optional<Business> business = businessServicio.getById(id);

        return business.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Actualizar un negocio existente
     * @param id identificador del negocio a actualizar
     * @param businessDetalles datos actualizados del negocio
     * @return el negocio actualizado o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Business> actualizarBusiness(@PathVariable("id") Long id,
                                                       @RequestBody Business businessDetalles) {
        Optional<Business> businessActualizado = businessServicio.updateBusiness(id, businessDetalles);

        return businessActualizado.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Eliminar un negocio
     * @param id identificador del negocio a eliminar
     * @return 204 si se eliminó correctamente, 404 si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBusiness(@PathVariable("id") Long id) {
        boolean eliminado = businessServicio.deleteBusiness(id);

        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}