package com.gestor_De_Alquileres.gestorAlquileres.Repository;

import com.gestor_De_Alquileres.gestorAlquileres.Model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface iTenantRepository extends JpaRepository<Tenant, Long> {
    Optional<Tenant> findBydni(String dni);
    Optional<Tenant> deleteBydni(String dni);
}
