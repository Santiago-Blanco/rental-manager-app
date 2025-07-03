package com.gestor_De_Alquileres.gestorAlquileres.Controller;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.DepartmentCreateDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.DepartmentEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.ContractsListNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.DepartmentListNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.DepartmentNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.PropertyNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("departamento")
public class DepartmentController {
    @Autowired
    DepartmentService serviceDepartment;

    @PostMapping("/crear")
    ResponseEntity<?> saveDepartment(@Valid @RequestBody DepartmentCreateDTO department, BindingResult result){
        if (result.hasErrors()){
            String errorMesagge = result.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errorMesagge);
        }
        if (department == null) throw new RuntimeException("El departamento no puede ser null");

        return ResponseEntity.ok().body(serviceDepartment.saveDepartment(department));
    }
    @GetMapping("/buscar/{property_name}/{floor}/{letter}")
    ResponseEntity<?> getDepartment(@PathVariable String property_name, @PathVariable int floor , @PathVariable String letter){
        try {
            char letterChar = letter.charAt(0);
            return ResponseEntity.ok().body(serviceDepartment.getDepartment(property_name, floor, letterChar));
        }catch (DepartmentNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/todos")
    ResponseEntity<?> getDepartments(){
        try {
            return ResponseEntity.ok().body(serviceDepartment.getDepartments());
        }catch (DepartmentListNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/todos/{property_name}")
    ResponseEntity<?> getDepartmensByProperty(@PathVariable String property_name){
        try {
            return ResponseEntity.ok().body(serviceDepartment.getDepartmensByProperty(property_name));
        }catch (PropertyNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("eliminar/{property_name}/{floor}/{letter}")
    ResponseEntity<?> deleteDepartment(@PathVariable String property_name, @PathVariable int floor, @PathVariable char letter){
        try {
            return ResponseEntity.ok().body(serviceDepartment.deleteDepartment(property_name, floor, letter));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("editar/{property_name}/{floor}/{letter}")
    ResponseEntity<?> editDepartment(@PathVariable String property_name, @PathVariable int floor, @PathVariable String letter, @RequestBody DepartmentEditDTO departmentEdit){
        try {
            char letterChar = letter.charAt(0);
            return ResponseEntity.ok().body(serviceDepartment.editDepartment(property_name, floor, letterChar, departmentEdit));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/asignar/inquilino/{property_name}/{dni}/{floor}/{letter}")
    ResponseEntity<?> assignTenantToDepartment(@PathVariable String property_name, @PathVariable String dni, @PathVariable int floor, @PathVariable char letter){
        try {
            return ResponseEntity.ok().body(serviceDepartment.assignTenantToDepartment(property_name, dni, floor, letter));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/disponibles")
    ResponseEntity<?> getAvailable(){
        try {
            return ResponseEntity.ok().body(serviceDepartment.getAvailable());
        }catch (DepartmentListNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/liberar/{property_name}/{floor}/{letter}")
    ResponseEntity<?> releaseDepartment(@PathVariable String property_name, @PathVariable int floor, @PathVariable char letter){
        try {
            return ResponseEntity.ok().body(serviceDepartment.releaseDepartment(property_name, floor, letter));
        }catch (DepartmentNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/vencimiento/contrato")
    ResponseEntity<?> releaseExpiredContracts(){
        try {
            return ResponseEntity.ok().body(serviceDepartment.releaseExpiredContracts());
        }catch (ContractsListNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
