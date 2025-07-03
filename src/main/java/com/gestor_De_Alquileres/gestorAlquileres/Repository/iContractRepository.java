package com.gestor_De_Alquileres.gestorAlquileres.Repository;

import com.gestor_De_Alquileres.gestorAlquileres.Model.Contract;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface iContractRepository extends JpaRepository<Contract, Long> {
    @Query("SELECT c FROM Contract c WHERE c.tenant = :tenant ORDER BY c.startDate DESC")
    List<Contract> findByTenantOrderByStartDateDesc(Tenant tenant);
}
