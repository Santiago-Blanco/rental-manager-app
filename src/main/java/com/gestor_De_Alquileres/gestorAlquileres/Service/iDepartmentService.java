package com.gestor_De_Alquileres.gestorAlquileres.Service;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.DepartmentCreateDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.DepartmentEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.DepartmentResponseDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Contract;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Department;

import java.util.List;

public interface iDepartmentService {
    DepartmentResponseDTO saveDepartment(DepartmentCreateDTO department);
    DepartmentResponseDTO getDepartment(String propertyName, int floor, char letter);
    List<DepartmentResponseDTO> getDepartments();
    List<DepartmentResponseDTO> getDepartmensByProperty(String propertyName);
    DepartmentResponseDTO deleteDepartment(String propertyName, int floor, char letter);
    DepartmentResponseDTO editDepartment(String propertyName, int floor, char letter, DepartmentEditDTO departmentEdit);
    DepartmentResponseDTO assignTenantToDepartment(String propertyName, String dni, int floor, char letter);
    List<DepartmentResponseDTO> getAvailable();
    DepartmentResponseDTO releaseDepartment(String propertyName, int floor, char letter);
    List<Contract> releaseExpiredContracts();
}
