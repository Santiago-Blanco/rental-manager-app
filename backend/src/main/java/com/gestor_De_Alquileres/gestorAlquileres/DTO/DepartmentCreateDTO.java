package com.gestor_De_Alquileres.gestorAlquileres.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepartmentCreateDTO {
    @NotNull(message = "El piso no puede ser null")
    private Integer floor;

    @NotNull(message = "La letra no puede ser null")
    private Character letter;

    @NotNull(message = "La propiedad no puede ser null")
    private String propertyName;
}
