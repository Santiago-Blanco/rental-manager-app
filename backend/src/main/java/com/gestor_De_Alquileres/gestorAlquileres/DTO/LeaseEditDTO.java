package com.gestor_De_Alquileres.gestorAlquileres.DTO;

import lombok.Data;

import java.util.Date;
@Data
public class LeaseEditDTO {
    private Date startDate;
    private Date endDate;
    private String dni;
    private Long idDepartment;
}
