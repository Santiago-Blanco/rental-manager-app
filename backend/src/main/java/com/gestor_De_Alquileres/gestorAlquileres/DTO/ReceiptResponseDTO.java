package com.gestor_De_Alquileres.gestorAlquileres.DTO;

import com.gestor_De_Alquileres.gestorAlquileres.Model.Receipt;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class ReceiptResponseDTO {
    private Long id;
    private Date date;
    private float rent;
    private float expenses;
    private float obrasSanitarias;
    private float total;
    private float others;
    private String totalInString;
    private String dniTenant;

    public static ReceiptResponseDTO fromReceipt(Receipt receipt) {
        ReceiptResponseDTO dto = new ReceiptResponseDTO();
        dto.setId(receipt.getId());
        dto.setDate(receipt.getDate());
        dto.setRent(receipt.getRent());
        dto.setExpenses(receipt.getExpenses());
        dto.setObrasSanitarias(receipt.getObrasSanitarias());
        dto.setTotal(receipt.getTotal());
        dto.setTotalInString(receipt.getTotalInString());
        dto.setDniTenant(receipt.getTenant().getDni());
        return dto;
    }
}
