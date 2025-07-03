package com.gestor_De_Alquileres.gestorAlquileres.Service;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.DepartmentCreateDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.DepartmentEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.DepartmentResponseDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.*;
import com.gestor_De_Alquileres.gestorAlquileres.Model.*;
import com.gestor_De_Alquileres.gestorAlquileres.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService implements iDepartmentService{
    @Autowired
    iDepartmentRepository departmentrepo;
    @Autowired
    iPropertyRepository propertyrepo;
    @Autowired
    iTenantRepository tenantrepo;
    @Autowired
    iLeaseRepository leaseRepo;
    @Autowired
    iContractRepository contractRepo;
    @Override
    public DepartmentResponseDTO saveDepartment(DepartmentCreateDTO department) {
        Department depa = new Department();
        depa.setFloor(department.getFloor());
        depa.setLetter(department.getLetter());

        Property property = propertyrepo.findBypropertyName(department.getPropertyName())
                .orElseThrow(() -> new PropertyNotFound());
        depa.setProperty(property);

        departmentrepo.save(depa);
        return new DepartmentResponseDTO(depa);
    }

    @Override
    public DepartmentResponseDTO getDepartment(String propertyName, int floor, char letter) {
        Property property = propertyrepo.findBypropertyName(propertyName)
                .orElseThrow(() -> new PropertyNotFound());
        Department department = departmentrepo.findByFloorAndLetterAndProperty(floor, letter, property)
                .orElseThrow(() -> new DepartmentNotFound());
        return new DepartmentResponseDTO(department);
    }

    @Override
    public List<DepartmentResponseDTO> getDepartments() {
        List<Department> departmentList = departmentrepo.findAll();
        if (departmentList.isEmpty()) throw new DepartmentListNotFound();

        List<DepartmentResponseDTO> dtoList = new ArrayList<>();
        for (Department department : departmentList) {
            dtoList.add(new DepartmentResponseDTO(department));
        }
        return dtoList;
    }

    @Override
    public List<DepartmentResponseDTO> getDepartmensByProperty(String propertyName) {
        Property property = propertyrepo.findBypropertyName(propertyName)
                .orElseThrow(() -> new PropertyNotFound());

        List<Department> departmentsList = departmentrepo.findAll();

        List<DepartmentResponseDTO> dtoList = new ArrayList<>();
        for (Department department : departmentsList) {
            if (department.getProperty().equals(property)) {
                dtoList.add(new DepartmentResponseDTO(department));
            }
        }
        return dtoList;
    }

    @Override
    public DepartmentResponseDTO deleteDepartment(String propertyName, int floor, char letter) {
        Property property = propertyrepo.findBypropertyName(propertyName)
                .orElseThrow(() -> new PropertyNotFound());

        Department department = departmentrepo.findByFloorAndLetterAndProperty(floor, letter, property)
                .orElseThrow(() -> new DepartmentNotFound());

        departmentrepo.delete(department);
        return new DepartmentResponseDTO(department);
    }

    @Override
    public DepartmentResponseDTO editDepartment(String propertyName, int floor, char letter, DepartmentEditDTO departmentEdit) {
        Property property = propertyrepo.findBypropertyName(propertyName)
                .orElseThrow(() -> new PropertyNotFound());

        Department department = departmentrepo.findByFloorAndLetterAndProperty(floor, letter, property)
                .orElseThrow(() -> new DepartmentNotFound());

        if (departmentEdit.getFloor() != null) department.setFloor(departmentEdit.getFloor());
        if (departmentEdit.getLetter() != null) department.setLetter(departmentEdit.getLetter());

        if (departmentEdit.getIdProperty() != null){
            Property newProperty = propertyrepo.findById(departmentEdit.getIdProperty())
                    .orElseThrow(() -> new PropertyNotFound());
            department.setProperty(newProperty);
        }

        if (departmentEdit.getIdTenant() != null){
            Tenant tenant = tenantrepo.findById(departmentEdit.getIdTenant())
                    .orElseThrow(() -> new RuntimeException("El inquilino no existe"));
            department.setTenant(tenant);
        }
        departmentrepo.save(department);
        return new DepartmentResponseDTO(department);
    }

    @Override
    public DepartmentResponseDTO assignTenantToDepartment(String propertyName, String dni, int floor, char letter) {
        Property property = propertyrepo.findBypropertyName(propertyName)
                .orElseThrow(() -> new PropertyNotFound());

        Tenant tenant = tenantrepo.findBydni(dni)
                .orElseThrow(() -> new TenantNotFound(dni));

        Department department = departmentrepo.findByFloorAndLetterAndProperty(floor, letter, property)
                .orElseThrow(() -> new DepartmentNotFound());

        if(department.getTenant() == null) {
            department.setTenant(tenant);
            departmentrepo.save(department);

            Lease lease = new Lease();
            lease.setTenant(tenant);
            lease.setDepartment(department);
            lease.setStartDate(new Date());
            leaseRepo.save(lease);
        } else {
            throw new BusyDepartment();
        }

        return new DepartmentResponseDTO(department);
    }

    @Override
    public List<DepartmentResponseDTO> getAvailable() {
        List<Department> departmentList = departmentrepo.findAll();
        if (departmentList.isEmpty()) throw new DepartmentListNotFound();

        List<DepartmentResponseDTO> dtoList = new ArrayList<>();
        for (Department department : departmentList) {
            if (department.getTenant() == null) {
                dtoList.add(new DepartmentResponseDTO(department));
            }
        }
        return dtoList;
    }

    @Override
    public DepartmentResponseDTO releaseDepartment(String propertyName, int floor, char letter) {
        Property property = propertyrepo.findBypropertyName(propertyName)
                .orElseThrow(() -> new PropertyNotFound());

        Department department = departmentrepo.findByFloorAndLetterAndProperty(floor, letter, property)
                .orElseThrow(() -> new DepartmentNotFound());

        department.setTenant(null);
        departmentrepo.save(department);

        Lease lease = leaseRepo.findByDepartmentAndEndDateIsNull(department)
                .orElseThrow(() -> new LeaseNotFound());
        lease.setEndDate(new Date());
        leaseRepo.save(lease);

        return new DepartmentResponseDTO(department);
    }

    @Override
    public List<Contract> releaseExpiredContracts() {
        List<Contract> contractList = contractRepo.findAll();
        List<Contract> contractExpired = new ArrayList<>();
        Date date = new Date();

        if (contractList.isEmpty()) throw new ContractsListNotFound();

        for(Contract contract: contractList){
            if (contract.getEndDate().before(date)){
                contractExpired.add(contract);
            }
        }

        for (Contract contract: contractExpired) {
            Department dept = contract.getTenant().getDepartment();
            if (dept != null) {
                releaseDepartment(
                        dept.getProperty().getPropertyName(),
                        dept.getFloor(),
                        dept.getLetter()
                );
            }
        }
        return contractExpired;
    }
}
