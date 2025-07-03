package com.gestor_De_Alquileres.gestorAlquileres.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;
    private Date date;
    private float rent;
    private float expenses;
    private float obrasSanitarias;
    private float total;
    private String totalInString;
    @ManyToOne
    @JoinColumn(name = "id_inquilino")
    Tenant tenant;
}
