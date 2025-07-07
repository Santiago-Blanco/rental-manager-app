package com.gestor_De_Alquileres.gestorAlquileres.Service;

import com.gestor_De_Alquileres.gestorAlquileres.DTO.ReceiptAdjustmentDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.ReceiptCreateDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.ReceiptEditDTO;
import com.gestor_De_Alquileres.gestorAlquileres.DTO.ReceiptResponseDTO;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.ContractNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.ReceiptListNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.ReceiptNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Exceptions.TenantNotFound;
import com.gestor_De_Alquileres.gestorAlquileres.Model.AdjustmentType;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Contract;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Receipt;
import com.gestor_De_Alquileres.gestorAlquileres.Model.Tenant;
import com.gestor_De_Alquileres.gestorAlquileres.Repository.iContractRepository;
import com.gestor_De_Alquileres.gestorAlquileres.Repository.iReceiptRepository;
import com.gestor_De_Alquileres.gestorAlquileres.Repository.iTenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptService implements iReceiptService{
    @Autowired
    iReceiptRepository receiptRepo;
    @Autowired
    iTenantRepository tenantRepo;
    @Autowired
    iContractRepository contractRepo;

    @Override
    public ReceiptResponseDTO saveReceipt(ReceiptCreateDTO receiptCreate) {
        Receipt receipt = new Receipt();

        receipt.setDate(receiptCreate.getDate());
        receipt.setRent(receiptCreate.getRent());
        receipt.setExpenses(receiptCreate.getExpenses());
        receipt.setObrasSanitarias(receiptCreate.getObrasSanitarias());
        receipt.setOthers(receiptCreate.getOthers());

        Tenant tenant = tenantRepo.findBydni(receiptCreate.getDniTenant())
                .orElseThrow(() -> new TenantNotFound(receiptCreate.getDniTenant()));
        receipt.setTenant(tenant);

        receipt.setTotal(receipt.getRent() + receipt.getExpenses() + receipt.getObrasSanitarias());
        receipt.setTotalInString(receiptCreate.getTotalInString());

        Contract contract = contractRepo.findByTenantOrderByStartDateDesc(tenant)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ContractNotFound());

        int monthLeft = contract.getMonthsUntilNextAdjustment();
        monthLeft--;

        if (monthLeft == 0) {
            if (contract.getAdjustment() == AdjustmentType.EVERY_THREE_MONTHS) {
                monthLeft = 3;
            } else {
                monthLeft = 4;
            }
            System.out.println("Hay que actualizar los valores del alquiler");
        }

        contract.setMonthsUntilNextAdjustment(monthLeft);

        contractRepo.save(contract);
        receiptRepo.save(receipt);

        return ReceiptResponseDTO.fromReceipt(receipt);
    }

    @Override
    public ReceiptResponseDTO getReceipt(Long idReceipt) {
        Receipt receipt = receiptRepo.findById(idReceipt)
                .orElseThrow(() -> new ReceiptNotFound());

        return ReceiptResponseDTO.fromReceipt(receipt);
    }

    @Override
    public List<ReceiptResponseDTO> getReceiptsByTenant(String dniTenant) {
        List<Receipt> receiptsList = receiptRepo.findAll();
        if (receiptsList.isEmpty()) throw new ReceiptListNotFound();

        List<ReceiptResponseDTO> receiptsByTenantDTO = new ArrayList<>();

        for (Receipt receipt : receiptsList) {
            if (receipt.getTenant().getDni().equals(dniTenant)) {
                receiptsByTenantDTO.add(ReceiptResponseDTO.fromReceipt(receipt));
            }
        }

        return receiptsByTenantDTO;
    }

    @Override
    public List<ReceiptResponseDTO> getAllReceipts() {
        List<Receipt> receiptsList = receiptRepo.findAll();
        if (receiptsList.isEmpty()) throw new ReceiptListNotFound();

        List<ReceiptResponseDTO> responseList = new ArrayList<>();

        for (Receipt receipt : receiptsList) {
            responseList.add(ReceiptResponseDTO.fromReceipt(receipt));
        }

        return responseList;
    }

    @Override
    public ReceiptResponseDTO deleteReceipt(Long idReceipt) {
        Receipt receipt = receiptRepo.findById(idReceipt)
                .orElseThrow(() -> new ReceiptNotFound());
        receiptRepo.delete(receipt);
        return ReceiptResponseDTO.fromReceipt(receipt);
    }

    @Override
    public ReceiptResponseDTO editReceipt(Long idReceipt, ReceiptEditDTO receiptEdit) {
        Receipt receipt = receiptRepo.findById(idReceipt)
                .orElseThrow(() -> new ReceiptNotFound());

        if (receiptEdit.getDate() != null) receipt.setDate(receiptEdit.getDate());
        if (receiptEdit.getRent() != null) receipt.setRent(receiptEdit.getRent());
        if (receiptEdit.getExpenses() != null) receipt.setExpenses(receiptEdit.getExpenses());
        if (receiptEdit.getObrasSanitarias() != null) receipt.setObrasSanitarias(receiptEdit.getObrasSanitarias());
        if (receiptEdit.getOthers() != null) receipt.setOthers(receipt.getOthers());
        if (receiptEdit.getTotal() != null) receipt.setTotal(receiptEdit.getTotal());
        if (receiptEdit.getTotalInString() != null) receipt.setTotalInString(receiptEdit.getTotalInString());
        if (receiptEdit.getDni() != null) {
            Tenant tenant = tenantRepo.findBydni(receiptEdit.getDni())
                    .orElseThrow(() -> new TenantNotFound(receiptEdit.getDni()));
            receipt.setTenant(tenant);
        }

        receiptRepo.save(receipt);

        return ReceiptResponseDTO.fromReceipt(receipt);
    }

    @Override
    public ReceiptResponseDTO editAmounts(Long idReceipt, ReceiptAdjustmentDTO adjustment) {
        Receipt receipt = receiptRepo.findById(idReceipt)
                .orElseThrow(() -> new ReceiptNotFound());

        if (adjustment.getRent() != null) receipt.setRent(adjustment.getRent());
        if (adjustment.getExpenses() != null) receipt.setExpenses(adjustment.getExpenses());
        if (adjustment.getObrasSanitarias() != null) receipt.setObrasSanitarias(adjustment.getObrasSanitarias());
        if (adjustment.getOthers() != null) receipt.setOthers(adjustment.getOthers());

        receipt.setTotal(receipt.getRent() + receipt.getExpenses() + receipt.getObrasSanitarias());

        receiptRepo.save(receipt);

        return ReceiptResponseDTO.fromReceipt(receipt);
    }
}
