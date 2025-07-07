package com.gestor_De_Alquileres.gestorAlquileres.DTO;

import lombok.Data;

@Data
public class ReceiptAdjustmentDTO {
    private Float rent;
    private Float expenses;
    private Float obrasSanitarias;
    private Float others;
}
