package com.gestor_De_Alquileres.gestorAlquileres.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TenantCreateDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String lastname;

    @NotBlank(message = "El DNI no puede estar vacío")
    private String dni;
}
