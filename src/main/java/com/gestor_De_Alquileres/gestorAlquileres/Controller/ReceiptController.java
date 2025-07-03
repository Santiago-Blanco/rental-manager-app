package com.gestor_De_Alquileres.gestorAlquileres.Controller;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.ReceiptAdjustmentDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.ReceiptCreateDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.ReceiptEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.ReceiptListNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.ReceiptNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.TenantNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Service.ReceiptService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("recibo")
public class ReceiptController {
    @Autowired
    ReceiptService receiptService;

    @PostMapping("/previsualizar")
    ResponseEntity<?> prewiewReceipt(@Valid @RequestBody ReceiptCreateDTO receipt, BindingResult result) {
        if (result.hasErrors()) {
            String errorMesagge = result.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errorMesagge);
        }
        if (receipt == null) return ResponseEntity.badRequest().body("El recibo no puede ser null");

        float total = receipt.getExpenses() + receipt.getRent() + receipt.getObrasSanitarias();
        return ResponseEntity.ok().body(Map.of(
                "total", total,
                "Mensaje", "ingrese la representacion en texto del total"
        ));
    }

    @PostMapping("/crear")
    ResponseEntity<?> saveReceipt(@Valid @RequestBody ReceiptCreateDTO receipt, BindingResult result) {
        if (result.hasErrors()) {
            String errorMesagge = result.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errorMesagge);
        }
        return ResponseEntity.ok().body(receiptService.saveReceipt(receipt));
    }

    @GetMapping("/{id_receipt}")
    ResponseEntity<?> getReceipt(@PathVariable Long id_receipt) {
        try {
            return ResponseEntity.ok().body(receiptService.getReceipt(id_receipt));
        } catch (ReceiptNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/inquilino/{dni_tenant}")
    ResponseEntity<?> getReceiptsByTenant(@PathVariable String dni_tenant) {
        try {
            return ResponseEntity.ok().body(receiptService.getReceiptsByTenant(dni_tenant));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/todos")
    ResponseEntity<?> getAllReceipts() {
        try {
            return ResponseEntity.ok().body(receiptService.getAllReceipts());
        } catch (ReceiptListNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id_receipt}")
    ResponseEntity<?> deleteReceipt(@PathVariable Long id_receipt) {
        try {
            return ResponseEntity.ok().body(receiptService.deleteReceipt(id_receipt));
        } catch (ReceiptNotFound e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @PutMapping("editar/{id_receipt}")
    ResponseEntity<?> editReceipt(@PathVariable Long id_receipt, @RequestBody ReceiptEditDTO receiptEdit) {
        try {
            return ResponseEntity.ok().body(receiptService.editReceipt(id_receipt, receiptEdit));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/ajuste/{id_receipt}")
    ResponseEntity<?> adjustReceiptAmounts(@PathVariable Long id_receipt, @RequestBody ReceiptAdjustmentDTO adjustment) {
        try {
            return ResponseEntity.ok().body(receiptService.editAmounts(id_receipt, adjustment));
        } catch (ReceiptNotFound e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
