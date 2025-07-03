package com.gestor_De_Alquileres.gestorAlquileres.Repository;

import com.gestor_De_Alquileres.gestorAlquileres.Model.Department;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface iLeaseRepository extends JpaRepository<Lease, Long> {
    Optional<Lease> findByDepartmentAndEndDateIsNull(Department department);
    List<Lease> findByDepartment(Department department);
}
