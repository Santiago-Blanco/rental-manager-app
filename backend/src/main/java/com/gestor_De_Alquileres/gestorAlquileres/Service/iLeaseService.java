package com.gestor_De_Alquileres.gestorAlquileres.Service;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.LeaseEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.LeaseResponseDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Lease;

import java.util.List;

public interface iLeaseService {
    LeaseResponseDTO getLease(Long idLease);
    List<LeaseResponseDTO> getAll();
    List<LeaseResponseDTO> getLeaseByTenant(String dniTenant);
    List<LeaseResponseDTO> getLeaseByDpertment(String propertyName, int floor, char letter);
    LeaseResponseDTO editLease(Long idLease, LeaseEditDTO leaseEdit);
}
