package com.gestor_De_Alquileres.gestorAlquileres.DTO;

import com.gestor_De_Alquileres.gestorAlquileres.Model.Tenant;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
@Data
public class ReceiptEditDTO {
    private Date date;
    private Float rent;
    private Float expenses;
    private Float obrasSanitarias;
    private Float total;
    private Float others;
    private String totalInString;
    private String dni;
}
