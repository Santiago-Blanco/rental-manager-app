package com.gestor_De_Alquileres.gestorAlquileres.DTO;

import com.gestor_De_Alquileres.gestorAlquileres.Model.Tenant;
import lombok.Data;
import lombok.Getter;

import javax.swing.*;

@Data
public class TenantResponseDTO {
    private String name;
    private String lastname;
    private String dni;
    private String propertyName;
    private Integer departmentFloor;
    private Character departmentLetter;

    public TenantResponseDTO(Tenant tenant) {
        this.name = tenant.getName();
        this.lastname = tenant.getLastname();
        this.dni = tenant.getDni();

        if (tenant.getDepartment() != null) {
            this.departmentFloor = tenant.getDepartment().getFloor();
            this.departmentLetter = tenant.getDepartment().getLetter();

            if (tenant.getDepartment().getProperty() != null) {
                this.propertyName = tenant.getDepartment().getProperty().getPropertyName();
            }
        } else {
            this.propertyName = null;
            this.departmentFloor = null;
            this.departmentLetter = null;
        }
    }
}




