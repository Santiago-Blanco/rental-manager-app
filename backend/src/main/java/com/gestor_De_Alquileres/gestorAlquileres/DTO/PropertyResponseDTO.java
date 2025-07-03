package com.gestor_De_Alquileres.gestorAlquileres.DTO;

import com.gestor_De_Alquileres.gestorAlquileres.Model.Department;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Property;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PropertyResponseDTO {
    private String propertyName;
    private String adress;
    private List<DepartmentResponseDTO> departmentList;

    public PropertyResponseDTO(Property property) {
        this.propertyName = property.getPropertyName();
        this.adress = property.getAdress();

        this.departmentList = new ArrayList<>();

        if (property.getDepartmentList() != null) {
            for (Department dept : property.getDepartmentList()) {
                this.departmentList.add(new DepartmentResponseDTO(dept));
            }
        }
    }
}


