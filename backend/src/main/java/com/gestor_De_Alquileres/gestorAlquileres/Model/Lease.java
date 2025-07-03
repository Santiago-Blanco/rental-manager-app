package com.gestor_De_Alquileres.gestorAlquileres.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private Date startDate;

    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "id_inquilino")
    @JsonBackReference("lease-tenant")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    @JsonBackReference("lease-department")
    private Department department;
}
