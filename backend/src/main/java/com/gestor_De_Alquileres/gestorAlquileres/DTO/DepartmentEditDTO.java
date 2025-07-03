package com.gestor_De_Alquileres.gestorAlquileres.DTO;

import com.gestor_De_Alquileres.gestorAlquileres.Model.Property;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Tenant;
import lombok.Data;

@Data
public class DepartmentEditDTO {
    private Integer floor;
    private Character letter;
    private Long idProperty;
    private Long idTenant;
}
