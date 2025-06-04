package com.example.Zitapp.Repositorios;

import com.example.Zitapp.Modelos.BusinnesService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessServiceRepositorio extends JpaRepository<BusinnesService, Long> {
    List<BusinnesService> findByBusinessId(Long businessId);
    List<BusinnesService> findByBusinessIdAndNombreContainingIgnoreCase(Long businessId, String nombre);
    List<BusinnesService> findByBusinessIdAndPrecioBetween(Long businessId, Double min, Double max);
}
