package com.gestor_De_Alquileres.gestorAlquileres.Service;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.TenantCreateDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.TenantEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.TenantResponseDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.TenantListNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.TenantNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Tenant;
import com.gestor_De_Alquileres.gestorAlquileres.Repository.iTenantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TenantService implements iTenantService{
    @Autowired
    iTenantRepository tenantRepo;

    @Override
    public TenantResponseDTO saveTenant(TenantCreateDTO tenantCreate) {
        Tenant tenant = new Tenant();
        tenant.setName(tenantCreate.getName());
        tenant.setLastname(tenantCreate.getLastname());
        tenant.setDni(tenantCreate.getDni());
        tenantRepo.save(tenant);
        return new TenantResponseDTO(tenant);
    }

    @Override
    public TenantResponseDTO getTenant(String dni) {
        Tenant tenant = tenantRepo.findBydni(dni)
                .orElseThrow(() -> new TenantNotFound(dni));
        return new TenantResponseDTO(tenant);
    }

    @Override
    public List<TenantResponseDTO> getTenants() {
        List<Tenant> tenantList = tenantRepo.findAll();
        if (tenantList.isEmpty()) throw new TenantListNotFound();

        List<TenantResponseDTO> dtoList = new ArrayList<>();
        for (Tenant tenant : tenantList) {
            dtoList.add(new TenantResponseDTO(tenant));
        }
        return dtoList;
    }

    @Override
    @Transactional
    public TenantResponseDTO deleteTenant(String dni) {
        Tenant tenant = tenantRepo.findBydni(dni)
                .orElseThrow(() -> new TenantNotFound(dni));
        tenantRepo.deleteBydni(dni);
        return new TenantResponseDTO(tenant);
    }

    @Override
    public TenantResponseDTO editTenant(String dni, TenantEditDTO tenantEdit) {
        Tenant tenant = tenantRepo.findBydni(dni)
                .orElseThrow(() -> new TenantNotFound(dni));

        if (tenantEdit.getName() != null && !tenantEdit.getName().isEmpty()) {
            tenant.setName(tenantEdit.getName());
        }

        if (tenantEdit.getLastname() != null && !tenantEdit.getLastname().isEmpty()) {
            tenant.setLastname(tenantEdit.getLastname());
        }

        if (tenantEdit.getDni() != null && !tenantEdit.getDni().isEmpty()) {
            tenant.setDni(tenantEdit.getDni());
        }

        tenantRepo.save(tenant);
        return new TenantResponseDTO(tenant);
    }

    @Override
    public List<TenantResponseDTO> tenantsWithOutDepartments() {
        List<Tenant> tenantList = tenantRepo.findAll();
        if (tenantList.isEmpty()) throw new TenantListNotFound();

        List<TenantResponseDTO> dtoList = new ArrayList<>();
        for (Tenant tenant : tenantList) {
            if (tenant.getDepartment() == null) {
                dtoList.add(new TenantResponseDTO(tenant));
            }
        }
        return dtoList;
    }
}
