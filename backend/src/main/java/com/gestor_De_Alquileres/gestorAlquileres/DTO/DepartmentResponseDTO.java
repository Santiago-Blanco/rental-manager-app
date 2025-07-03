package com.gestor_De_Alquileres.gestorAlquileres.DTO;

import com.gestor_De_Alquileres.gestorAlquileres.Model.Department;
import lombok.Data;

@Data
public class DepartmentResponseDTO {
    private int floor;
    private char letter;
    private String propertyName;
    private TenantResponseDTO tenant;

    public DepartmentResponseDTO(Department department) {
        this.floor = department.getFloor();
        this.letter = department.getLetter();
        this.propertyName = department.getProperty() != null
                ? department.getProperty().getPropertyName()
                : null;

        this.tenant = department.getTenant() != null
                ? new TenantResponseDTO(department.getTenant())
                : null;
    }
}

