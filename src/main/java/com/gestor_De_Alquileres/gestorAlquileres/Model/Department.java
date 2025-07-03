package com.gestor_De_Alquileres.gestorAlquileres.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "El piso no puede ser null")
    private int floor;
    @NotBlank(message = "La letra no puede ser null")
    private char letter;
    @ManyToOne
    @JoinColumn(name = "id_propiedad")
    Property property;
    @OneToOne
    @JoinColumn(name = "id_inquilino")
    @JsonManagedReference("department-tenant")
    private Tenant tenant;
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("department-lease")
    List<Lease> leaseList;
}
