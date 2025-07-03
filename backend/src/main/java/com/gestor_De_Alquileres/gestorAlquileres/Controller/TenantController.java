package com.gestor_De_Alquileres.gestorAlquileres.Controller;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.TenantCreateDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.TenantEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.TenantListNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.TenantNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Tenant;
import com.gestor_De_Alquileres.gestorAlquileres.Service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("inquilino")
public class TenantController {
    @Autowired
    TenantService serviceTenant;

    @PostMapping("/crear")
    ResponseEntity<?> saveTenant(@Valid @RequestBody TenantCreateDTO tenant, BindingResult result){
        if (result.hasErrors()){
            String errorMesagge = result.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(","));
            return ResponseEntity.badRequest().body(errorMesagge);
        }
        if (tenant == null) return ResponseEntity.badRequest().body("El inquilino no puede ser null");

        return ResponseEntity.ok().body(serviceTenant.saveTenant(tenant));
    }
    @GetMapping("/{dni}")
    ResponseEntity<?> getTenant(@PathVariable String dni){
        try {
            return ResponseEntity.ok().body(serviceTenant.getTenant(dni));
        }catch (TenantNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/todos")
    ResponseEntity<?> getTenants(){
        try {
            return ResponseEntity.ok().body(serviceTenant.getTenants());
        }catch (TenantListNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/eliminar/{dni}")
    ResponseEntity<?> deleteTenant(@PathVariable String dni){
        try {
            return ResponseEntity.ok().body(serviceTenant.deleteTenant(dni));
        }catch (TenantNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("editar/{dni}")
    ResponseEntity<?> editTenant(@PathVariable String dni,@RequestBody TenantEditDTO tenantEdit){
        try{
            return ResponseEntity.ok().body(serviceTenant.editTenant(dni, tenantEdit));
        }catch (TenantNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/disponibles")
    ResponseEntity<?> tenantsWithOutDepartments(){
        try {
            return ResponseEntity.ok().body(serviceTenant.tenantsWithOutDepartments());
        }catch (TenantListNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
