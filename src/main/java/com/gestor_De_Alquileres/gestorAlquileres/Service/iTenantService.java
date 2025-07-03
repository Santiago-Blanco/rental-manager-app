package com.gestor_De_Alquileres.gestorAlquileres.Service;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.TenantCreateDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.TenantEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.TenantResponseDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Tenant;

import java.util.List;

public interface iTenantService {
    TenantResponseDTO saveTenant(TenantCreateDTO tenant);
    TenantResponseDTO getTenant(String dni);
    List<TenantResponseDTO> getTenants();
    TenantResponseDTO deleteTenant(String dni);
    TenantResponseDTO editTenant(String dni, TenantEditDTO tenantEdit);
    List<TenantResponseDTO> tenantsWithOutDepartments();
}
