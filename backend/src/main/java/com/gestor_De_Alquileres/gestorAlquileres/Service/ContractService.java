package com.gestor_De_Alquileres.gestorAlquileres.Service;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.ContractCreateDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.ContractEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.ContractResponseDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.ContractNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.ContractsListNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.TenantNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Contract;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Tenant;
import com.gestor_De_Alquileres.gestorAlquileres.Repository.iContractRepository;
import com.gestor_De_Alquileres.gestorAlquileres.Repository.iTenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ContractService implements iContractService{
    @Autowired
    iContractRepository contractRepo;
    @Autowired
    iTenantRepository tenantRepo;

    @Override
    public ContractResponseDTO saveContract(ContractCreateDTO contract) {
        Contract contract1 = new Contract();

        contract1.setStartDate(contract.getStartDate());
        contract1.setEndDate(contract.getEndDate());
        contract1.setAdjustment(contract.getAdjustment());
        Tenant tenant = tenantRepo.findBydni(contract.getDni())
                        .orElseThrow(() -> new TenantNotFound(contract.getDni()));
        contract1.setTenant(tenant);

        contractRepo.save(contract1);

        return ContractResponseDTO.contractToContractresponse(contract1);
    }

    @Override
    public ContractResponseDTO getContract(Long idContract) {
        Contract contract = contractRepo.findById(idContract)
                .orElseThrow(() -> new ContractNotFound());

        ContractResponseDTO contractResponse = ContractResponseDTO.contractToContractresponse(contract);
        return contractResponse;
    }

    @Override
    public List<ContractResponseDTO> getContracts() {
        List<Contract> contractsList = contractRepo.findAll();
        if (contractsList == null) throw new ContractsListNotFound();
        List<ContractResponseDTO> contractResponseList = ContractResponseDTO.contractListToContractResponseList(contractsList);
        return contractResponseList;
    }

    @Override
    public List<ContractResponseDTO> getContractsByTenant(String dniTenant) {
        List<Contract> contractsList = contractRepo.findAll();
        List<Contract> contractsByTenant = new ArrayList<>();

        if (contractsList == null) throw new ContractsListNotFound();

        for (Contract contract: contractsList){
            if (contract.getTenant().getDni().equals(dniTenant)){
                contractsByTenant.add(contract);
            }
        }

        List<ContractResponseDTO> contractResponseList = ContractResponseDTO.contractListToContractResponseList(contractsByTenant);
        return contractResponseList;
    }

    @Override
    public ContractResponseDTO deleteContract(Long idContract) {
        Contract contractToDelete = contractRepo.findById(idContract)
                .orElseThrow(() -> new ContractNotFound());

        contractRepo.delete(contractToDelete);
        ContractResponseDTO contractResponse = ContractResponseDTO.contractToContractresponse(contractToDelete);
        return contractResponse;
    }

    @Override
    public ContractResponseDTO editContract(Long idContract, ContractEditDTO contract) {
        Contract contractToEdit = contractRepo.findById(idContract)
                .orElseThrow(() -> new ContractNotFound());

        if (contract.getStartDate() != null) contractToEdit.setStartDate(contract.getStartDate());
        if (contract.getEndDate() != null) contractToEdit.setEndDate(contract.getEndDate());
        if (contract.getAdjustment() != null) contractToEdit.setAdjustment(contract.getAdjustment());
        if (contract.getDni() != null){
            Tenant tenant = tenantRepo.findBydni(contract.getDni())
                    .orElseThrow(() -> new TenantNotFound(contract.getDni()));
            contractToEdit.setTenant(tenant);
        }
        contractRepo.save(contractToEdit);
        ContractResponseDTO contractResponse = ContractResponseDTO.contractToContractresponse(contractToEdit);
        return contractResponse;
    }

    @Override
    public List<ContractResponseDTO> alertAdjustment() {
        List<Contract> contractList = contractRepo.findAll();
        List<ContractResponseDTO> contractsResponses = new ArrayList<>();
        Date date = new Date();

        if (contractList.isEmpty()) throw new ContractsListNotFound();

        for (Contract contract: contractList){

            if (contract.getMonthsUntilNextAdjustment() == 0 && contract.getEndDate().after(date)){
                ContractResponseDTO contractResponse = ContractResponseDTO.contractToContractresponse(contract);
                contractsResponses.add(contractResponse);
            }
        }
        return contractsResponses;
    }

    @Override
    public List<ContractResponseDTO> alertLastMonth() {
        List<Contract> contractList = contractRepo.findAll();
        List<ContractResponseDTO> contractResponseList = new ArrayList<>();

        if (contractList.isEmpty()) throw new ContractsListNotFound();

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date oneMonthLater = calendar.getTime();

        for (Contract contract : contractList) {
           if (contract.getEndDate().after(today) && contract.getEndDate().before(oneMonthLater)) {
                contractResponseList.add(ContractResponseDTO.contractToContractresponse(contract));
            }
        }

        return contractResponseList;
    }
}
