package com.gestor_De_Alquileres.gestorAlquileres.Service;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.ContractCreateDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.ContractEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.ContractResponseDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Contract;

import java.util.List;

public interface iContractService {
    ContractResponseDTO saveContract(ContractCreateDTO contract);
    ContractResponseDTO getContract(Long idContract);
    List<ContractResponseDTO> getContracts();
    List<ContractResponseDTO> getContractsByTenant(String dniTenant);
    ContractResponseDTO deleteContract(Long idContract);
    ContractResponseDTO editContract(Long idContract, ContractEditDTO contract);
    List<ContractResponseDTO> alertAdjustment();
    List<ContractResponseDTO> alertLastMonth();
}
