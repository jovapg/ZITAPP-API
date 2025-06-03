package com.example.Zitapp.Repositorios;

import com.example.Zitapp.Modelos.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessRepositorio  extends  JpaRepository<Business, Long>{
    Optional<Business> findByIdUsuario(Long idUsuario);



}