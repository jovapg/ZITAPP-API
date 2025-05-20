
package com.example.Zitapp.Repositorios;

import com.example.Zitapp.Modelos.BusinnesService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessServiceRepositorio extends JpaRepository<BusinnesService, Long> {
    // Encontrar servicios por id de negocio
    List<BusinnesService> findByBusinessId(Long businessId);

    // Encontrar servicios por rango de precio
    List<BusinnesService> findByBusinessIdAndPrecioBetween(Long businessId, Double precioMin, Double precioMax);

    // Buscar servicios por nombre que contenga cierta palabra
    List<BusinnesService> findByBusinessIdAndNombreContainingIgnoreCase(Long businessId, String nombre);
}