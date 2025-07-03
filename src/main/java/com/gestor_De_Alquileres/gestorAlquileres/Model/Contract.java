package com.gestor_De_Alquileres.gestorAlquileres.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Date startDate;
    private Date endDate;
    private AdjustmentType adjustment;
    private int monthsUntilNextAdjustment;

    @ManyToOne
    @JoinColumn(name = "id_inquilino")
    @JsonBackReference("contract-tenant")
    private Tenant tenant;
}
