package com.gestor_De_Alquileres.gestorAlquileres.Service;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.LeaseEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.LeaseResponseDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.*;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Department;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Lease;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Property;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Tenant;
import com.gestor_De_Alquileres.gestorAlquileres.Repository.iDepartmentRepository;
import com.gestor_De_Alquileres.gestorAlquileres.Repository.iLeaseRepository;
import com.gestor_De_Alquileres.gestorAlquileres.Repository.iPropertyRepository;
import com.gestor_De_Alquileres.gestorAlquileres.Repository.iTenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaseService implements iLeaseService{
    @Autowired
    iLeaseRepository leaseRepo;
    @Autowired
    iTenantRepository tenantRepo;
    @Autowired
    iDepartmentRepository departmentRepo;
    @Autowired
    iPropertyRepository propertyRepo;

    @Override
    public LeaseResponseDTO getLease(Long idLease) {
        Lease lease = leaseRepo.findById(idLease)
                .orElseThrow(()-> new LeaseNotFound());

        return LeaseResponseDTO.leaseToLeaseResponse(lease);
    }

    @Override
    public List<LeaseResponseDTO> getAll() {
        List<Lease> leaseList = leaseRepo.findAll();
        List<LeaseResponseDTO> leaseResponseList = new ArrayList<>();

        if (leaseList.isEmpty()) throw new LeaseListNotFound();

        for (Lease lease: leaseList){
            leaseResponseList.add(LeaseResponseDTO.leaseToLeaseResponse(lease));
        }
        return  leaseResponseList;
    }

    @Override
    public List<LeaseResponseDTO> getLeaseByTenant(String dniTenant) {
        List<Lease> leasesList = leaseRepo.findAll();
        List<LeaseResponseDTO> leaseResponseList = new ArrayList<>();

        if (leasesList.isEmpty()) throw new LeaseListNotFound();

        for (Lease lease: leasesList){
            if (lease.getTenant().getDni().equals(dniTenant)){
                leaseResponseList.add(LeaseResponseDTO.leaseToLeaseResponse(lease));
            }
        }
        return leaseResponseList;
    }

    @Override
    public List<LeaseResponseDTO> getLeaseByDpertment(String  propertyName, int floor, char letter) {
        Property property = propertyRepo.findBypropertyName(propertyName)
                .orElseThrow(()-> new PropertyNotFound());

        Department department = departmentRepo.findByFloorAndLetterAndProperty(floor, letter, property)
                .orElseThrow(()-> new DepartmentNotFound());

        List<Lease> leaseList = leaseRepo.findByDepartment(department);
        List<LeaseResponseDTO> leaseResponseList = new ArrayList<>();

        for (Lease lease: leaseList){
            leaseResponseList.add(LeaseResponseDTO.leaseToLeaseResponse(lease));
        }

        return leaseResponseList;
    }

    @Override
    public LeaseResponseDTO editLease(Long idLease, LeaseEditDTO leaseEdit) {
        Lease lease = leaseRepo.findById(idLease)
                .orElseThrow(()-> new LeaseNotFound());

        if (leaseEdit.getStartDate() != null) lease.setStartDate(leaseEdit.getStartDate());
        if (leaseEdit.getEndDate() != null) lease.setEndDate(leaseEdit.getEndDate());
        if (leaseEdit.getDni() != null){
            Tenant tenant = tenantRepo.findBydni(leaseEdit.getDni())
                    .orElseThrow(()-> new TenantNotFound(leaseEdit.getDni()));
            lease.setTenant(tenant);
        }
        if (leaseEdit.getIdDepartment() != null){
            Department department = departmentRepo.findById(leaseEdit.getIdDepartment())
                    .orElseThrow(()-> new DepartmentNotFound());
            lease.setDepartment(department);
        }

        leaseRepo.save(lease);
        return LeaseResponseDTO.leaseToLeaseResponse(lease);
    }
}
