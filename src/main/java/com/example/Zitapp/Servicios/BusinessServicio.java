package com.example.Zitapp.Servicios;

import com.example.Zitapp.Modelos.Business;
import com.example.Zitapp.Repositorios.BusinessRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessServicio {
    @Autowired
    private BusinessRepositorio businessRepositorio;

    public Business saveBusiness(Business business) {
        return businessRepositorio.save(business);
    }

    public List<Business> getAll() {
        return businessRepositorio.findAll();
    }

    public Optional<Business> getById(Long id) {
        return businessRepositorio.findById(id);
    }

    public Optional<Business> updateBusiness(Long id, Business businessData) {
        return businessRepositorio.findById(id).map(business -> {
          business.setNombre(businessData.getNombre());
          business.setDescripcion(businessData.getDescripcion());
          business.setCategoria(businessData.getCategoria());
          business.setDireccion(businessData.getDireccion());
          business.setIdUsuario(businessData.getIdUsuario());
          business.setImagenUrl(businessData.getImagenUrl());
          return businessRepositorio.save(business);
        });
    }

    public boolean deleteBusiness(Long id) {
        return businessRepositorio.findById(id).map(business -> {
            businessRepositorio.delete(business);
            return true;
        }).orElse(false);
    }

    public Optional<Business> getByUserId(Long userId) {
        return businessRepositorio.findByIdUsuario(userId);
    }

}
