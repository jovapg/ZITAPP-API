// src/main/java/com/example/Zitapp/Repositorios/BusinessRepositorio.java
package com.example.Zitapp.Repositorios;

import com.example.Zitapp.Modelos.Business;
// No es estrictamente necesario importar Users aquí, pero es una buena práctica si estás trabajando
// con sus propiedades en el método findByUsuario_Id.
// import com.example.Zitapp.Modelos.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessRepositorio extends JpaRepository<Business, Long> {

    // ¡CAMBIO CLAVE AQUÍ!
    // Ahora que la entidad Business tiene un campo 'usuario' de tipo Users,
    // buscamos por el ID de ese objeto 'usuario'.
    // El nombre del método sigue la convención de Spring Data JPA:
    // findBy + [NombreDelCampoDeLaRelacion] + _ + [NombreDelCampoDelIdEnLaEntidadRelacionada]
    // En este caso: findBy + Usuario + _ + Id
    Optional<Business> findByUsuario_Id(Long userId);
}