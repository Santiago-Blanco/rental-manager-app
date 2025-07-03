package com.gestor_De_Alquileres.gestorAlquileres.DTO;

import com.gestor_De_Alquileres.gestorAlquileres.Model.Lease;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class LeaseResponseDTO {
    private Date startDate;
    private Date endDate;

    private String tenantName;
    private String tenantLastName;
    private String tenantDNI;

    private int floor;
    private char letter;

    private String propertyName;
    private String address;

    public static  LeaseResponseDTO leaseToLeaseResponse(Lease lease){
        LeaseResponseDTO leaseResponse = new LeaseResponseDTO();

        leaseResponse.setStartDate(lease.getStartDate());
        leaseResponse.setEndDate(lease.getEndDate());
        if (lease.getTenant() != null) {
            leaseResponse.setTenantName(lease.getTenant().getName());
            leaseResponse.setTenantLastName(lease.getTenant().getLastname());
            leaseResponse.setTenantDNI(lease.getTenant().getDni());
        }

        if (lease.getDepartment() != null && lease.getDepartment().getProperty() != null){
            leaseResponse.setPropertyName(lease.getDepartment().getProperty().getPropertyName());
            leaseResponse.setAddress(lease.getDepartment().getProperty().getAdress());

            leaseResponse.setFloor(lease.getDepartment().getFloor());
            leaseResponse.setLetter(lease.getDepartment().getLetter());
        }

        return leaseResponse;
    }
}
