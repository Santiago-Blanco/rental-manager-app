package com.gestor_De_Alquileres.gestorAlquileres.Controller;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.PropertyCreateDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.PropertyEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.PropertyListNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.PropertyNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Property;
import com.gestor_De_Alquileres.gestorAlquileres.Service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("propiedad")
public class PropertyController {
    @Autowired
    PropertyService propertyService;
    @PostMapping("/crear")
    ResponseEntity<?> saveProperty(@Valid @RequestBody PropertyCreateDTO property, BindingResult result){
        if (result.hasErrors()){
            String errorMessage = result.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errorMessage);
        }
        if (property == null) throw new RuntimeException("La propiedad no puede ser null");

        return ResponseEntity.ok().body(propertyService.saveProperty(property));
    }
    @GetMapping("/{propertyName}")
    ResponseEntity<?> getProperty(@PathVariable String propertyName){
        try {
            return ResponseEntity.ok().body(propertyService.getProperty(propertyName));
        }catch (PropertyNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/todas")
    ResponseEntity<?> getProperties(){
        try {
            return ResponseEntity.ok().body(propertyService.getProperties());
        }catch (PropertyListNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{propertyName}")
    ResponseEntity<?> deleteProperty(@PathVariable String propertyName){
        try {
            return ResponseEntity.ok().body(propertyService.deleteProperty(propertyName));
        }catch (PropertyNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("editar/{propertyName}")
    ResponseEntity<?> editProperty(@PathVariable String propertyName, @RequestBody PropertyEditDTO propertyEdit){
        try {
            return ResponseEntity.ok().body(propertyService.editProperty(propertyName, propertyEdit));
        }catch (PropertyNotFound e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
