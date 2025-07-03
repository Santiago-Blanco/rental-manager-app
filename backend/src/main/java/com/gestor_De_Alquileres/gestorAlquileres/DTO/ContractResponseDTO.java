package com.gestor_De_Alquileres.gestorAlquileres.DTO;

import com.gestor_De_Alquileres.gestorAlquileres.Model.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ContractResponseDTO {
        private Date startDate;
        private Date endDate;
        private AdjustmentType adjustment;

        private String tenantName;
        private String tenantLastname;
        private String dni;

        private int departmentFloor;
        private char departmentLetter;

        private String propertyName;
        private String propertyAddress;

    public static ContractResponseDTO contractToContractresponse(Contract contract) {
        Tenant tenant = contract.getTenant();

        ContractResponseDTO contractResponse = new ContractResponseDTO();
        contractResponse.setStartDate(contract.getStartDate());
        contractResponse.setEndDate(contract.getEndDate());
        contractResponse.setAdjustment(contract.getAdjustment());

        if (tenant != null) {
            contractResponse.setTenantName(tenant.getName());
            contractResponse.setTenantLastname(tenant.getLastname());
            contractResponse.setDni(tenant.getDni());

            Department dept = tenant.getDepartment();

            if (dept != null && dept.getProperty() != null) {
                Property prop = dept.getProperty();
                contractResponse.setDepartmentFloor(dept.getFloor());
                contractResponse.setDepartmentLetter(dept.getLetter());
                contractResponse.setPropertyName(prop.getPropertyName());
                contractResponse.setPropertyAddress(prop.getAdress());
            } else {
                contractResponse.setDepartmentFloor(-1);
                contractResponse.setDepartmentLetter('-');
                contractResponse.setPropertyName("N/D");
                contractResponse.setPropertyAddress("N/D");
            }
        } else {
            contractResponse.setTenantName("N/D");
            contractResponse.setTenantLastname("");
            contractResponse.setDni("");
            contractResponse.setDepartmentFloor(-1);
            contractResponse.setDepartmentLetter('-');
            contractResponse.setPropertyName("N/D");
            contractResponse.setPropertyAddress("N/D");
        }

        return contractResponse;
    }

    public static List<ContractResponseDTO> contractListToContractResponseList(List<Contract> contracts) {
        List<ContractResponseDTO> contractResponseList = new ArrayList<>();
        for (Contract contract : contracts) {
            contractResponseList.add(contractToContractresponse(contract));
        }
        return contractResponseList;
    }
}

