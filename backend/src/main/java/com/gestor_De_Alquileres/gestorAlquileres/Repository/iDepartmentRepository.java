package com.gestor_De_Alquileres.gestorAlquileres.Repository;

import com.gestor_De_Alquileres.gestorAlquileres.Model.Department;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Properties;
@Repository
public interface iDepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByFloorAndLetterAndProperty(int floor, char letter, Property property);
}
