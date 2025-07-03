package com.gestor_De_Alquileres.gestorAlquileres.DTO;

import com.gestor_De_Alquileres.gestorAlquileres.Model.AdjustmentType;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;
@Data
public class ContractEditDTO {
    private Date startDate;
    private Date endDate;
    private AdjustmentType adjustment;
    private String dni;
}
