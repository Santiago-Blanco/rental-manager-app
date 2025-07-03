package com.gestor_De_Alquileres.gestorAlquileres.Repository;

import com.gestor_De_Alquileres.gestorAlquileres.Model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface iPropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> findBypropertyName(String propertyName);
}
