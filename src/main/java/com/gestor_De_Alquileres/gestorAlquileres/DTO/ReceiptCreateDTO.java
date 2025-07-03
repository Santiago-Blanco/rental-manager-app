package com.gestor_De_Alquileres.gestorAlquileres.DTO;

import com.gestor_De_Alquileres.gestorAlquileres.Model.Tenant;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
@Data
public class ReceiptCreateDTO {
    @NotNull(message = "La fecha de el recibo no puede ser null")
    private Date date;

    @Min(value = 0, message = "El valor debe ser cero o positivo")
    private float rent;
    @Min(value = 0, message = "El valor debe ser cero o positivo")
    private float expenses;
    @Min(value = 0, message = "El valor debe ser cero o positivo")
    private float obrasSanitarias;
    private String totalInString;
    @NotNull(message = "El DNI del inquilino no puede ser null")
    private String dniTenant;
}
