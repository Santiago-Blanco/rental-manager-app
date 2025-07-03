package com.gestor_De_Alquileres.gestorAlquileres.Controller;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.LeaseEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.LeaseListNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.LeaseNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("historial")
public class LeaseController {
    @Autowired
    LeaseService leaseService;
    @GetMapping("/{id_lease}")
    ResponseEntity<?> getLease(@PathVariable Long id_lease){
        try {
            return ResponseEntity.ok().body(leaseService.getLease(id_lease));
        }catch (LeaseNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/todos")
    ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok().body(leaseService.getAll());
        }catch (LeaseListNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/inquilino/{dni_tenant}")
    ResponseEntity<?> getLeaseByTenant(@PathVariable String dni_tenant){
        try {
            return ResponseEntity.ok().body(leaseService.getLeaseByTenant(dni_tenant));
        }catch (LeaseListNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("departamento/{property_name}/{floor}/{letter}")
    ResponseEntity<?> getLeaseByDepartment(@PathVariable String property_name, @PathVariable int floor, @PathVariable char letter){
        try {
            return ResponseEntity.ok().body(leaseService.getLeaseByDpertment(property_name, floor, letter));
        }catch (LeaseListNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("editar/{id_lease}")
    ResponseEntity<?> editLease(@PathVariable Long id_lease, @RequestBody LeaseEditDTO leaseEdit){
        try {
            return ResponseEntity.ok().body(leaseService.editLease(id_lease, leaseEdit));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
