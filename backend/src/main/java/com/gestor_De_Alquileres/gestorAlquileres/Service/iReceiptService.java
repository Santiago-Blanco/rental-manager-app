package com.gestor_De_Alquileres.gestorAlquileres.Service;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.ReceiptAdjustmentDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.ReceiptCreateDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.ReceiptEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.ReceiptResponseDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Receipt;

import java.util.List;

public interface iReceiptService {
    ReceiptResponseDTO saveReceipt(ReceiptCreateDTO receiptCreate);
    ReceiptResponseDTO getReceipt(Long idReceipt);
    List<ReceiptResponseDTO> getReceiptsByTenant(String dniTenant);
    List<ReceiptResponseDTO> getAllReceipts();
    ReceiptResponseDTO deleteReceipt(Long idReceipt);
    ReceiptResponseDTO editReceipt(Long idReceipt, ReceiptEditDTO receiptEdit);
    ReceiptResponseDTO editAmounts(Long idReceipt, ReceiptAdjustmentDTO adjustment);
}
