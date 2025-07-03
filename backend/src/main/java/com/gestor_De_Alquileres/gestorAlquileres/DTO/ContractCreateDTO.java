package com.gestor_De_Alquileres.gestorAlquileres.DTO;

import com.gestor_De_Alquileres.gestorAlquileres.Model.AdjustmentType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
@Data
public class ContractCreateDTO {
    @NotNull(message = "La fecha de inicio no puede estar vacía")
    private Date startDate;

    @NotNull(message = "La fecha final no puede estar vacía")
    private Date endDate;

    @NotNull(message = "El ajuste no puede estar vacío")
    private AdjustmentType adjustment;

    @NotNull(message = "El DNI del inquilino no puede estar vacío")
    private String dni;
}
