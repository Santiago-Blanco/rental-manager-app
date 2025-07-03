package com.gestor_De_Alquileres.gestorAlquileres.Controller;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.ContractCreateDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.ContractEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.ContractNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.ContractsListNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Contract;
import com.gestor_De_Alquileres.gestorAlquileres.Service.ContractService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("contrato")
public class ContractController {
    @Autowired
    ContractService contractService;
    @PostMapping("/crear")
    ResponseEntity<?> contractSave(@Valid @RequestBody ContractCreateDTO contract, BindingResult result){
        if (result.hasErrors()){
            String errorMesagge = result.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errorMesagge);
        }
        if (contract == null) return ResponseEntity.badRequest().body("El contrato no puede ser null");

        return ResponseEntity.ok().body(contractService.saveContract(contract));
    }
    @GetMapping("/{id_contract}")
    ResponseEntity<?> getContract(@PathVariable Long id_contract){
        try {
            return ResponseEntity.ok().body(contractService.getContract(id_contract));
        }catch (ContractNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/todos")
    ResponseEntity<?> getContracts(){
        try {
            return ResponseEntity.ok().body(contractService.getContracts());
        }catch (ContractsListNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/inquilino/{dni_tenant}")
    ResponseEntity<?> getContractsByTenant(@PathVariable String dni_tenant){
        try {
            return ResponseEntity.ok().body(contractService.getContractsByTenant(dni_tenant));
        }catch (ContractsListNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/eliminar/{id_contract}")
    ResponseEntity<?> deleteContract(@PathVariable Long id_contract){
        try {
            return ResponseEntity.ok().body(contractService.deleteContract(id_contract));
        }catch (ContractNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/editar/{id_contract}")
    ResponseEntity<?> editContract(@PathVariable Long id_contract, @RequestBody ContractEditDTO contract){
        System.out.println("Llegó petición PUT para id: " + id_contract);
        System.out.println("DTO recibido: " + contract);
        try {
            return ResponseEntity.ok().body(contractService.editContract(id_contract, contract));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("aviso/ajuste")
    ResponseEntity<?> alertAdjustment(){
        try{
            return ResponseEntity.ok().body(contractService.alertAdjustment());
        }catch (ContractsListNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("aviso/finalizacion")
    ResponseEntity<?> getContractsExpiringSoon(){
        try {
            return ResponseEntity.ok().body(contractService.alertLastMonth());
        }catch (ContractsListNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
