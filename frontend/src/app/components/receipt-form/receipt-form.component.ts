import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ReceiptService } from '../../services/receipt.service';
import { ReceiptCreate } from '../../models/receipt-create';
import { RouterModule } from '@angular/router';
import { NgIf } from '@angular/common';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';
import { TenantService } from '../../services/tenant.service';
import { TenantResponseDTO } from '../../DTO/tenant-response-dto';

@Component({
  selector: 'app-receipt-form',
  templateUrl: './receipt-form.component.html',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule, NgIf]
})
export class ReceiptFormComponent {
  receiptForm: FormGroup
  totalCalculado: number | null = null
  message: string = ''
  lastReceiptSaved: ReceiptCreate | null = null
  tenant: TenantResponseDTO | null = null

  constructor(
    private fb: FormBuilder,
    private receiptService: ReceiptService,
    private tenantService: TenantService
  ) {
    this.receiptForm = this.fb.group({
      date: ["", Validators.required],
      rent: ["", Validators.required],
      expenses: ["", Validators.required],
      obrasSanitarias: ["", Validators.required],
      totalInString: ["", Validators.required],
      dniTenant: ["", Validators.required]
    });
  }

  calcularTotal() {
    const dto: ReceiptCreate = {
      date: this.receiptForm.get('date')?.value,
      rent: this.receiptForm.get('rent')?.value,
      expenses: this.receiptForm.get('expenses')?.value,
      obrasSanitarias: this.receiptForm.get('obrasSanitarias')?.value,
      totalInString: "",
      dniTenant: this.receiptForm.get('dniTenant')?.value
    };

    this.receiptService.previewReceipt(dto).subscribe({
      next: (data) => {
        this.totalCalculado = data.total;
      },
      error: (err) => {
        this.message = "Error al calcular el total: " + err.error;
      }
    });
  }

  onSubmit() {
    if (this.receiptForm.valid) {
      const receipt = this.receiptForm.value;

      this.receiptService.saveReceipt(receipt).subscribe({
        next: () => {
          this.message = "Recibo creado con éxito";
          this.lastReceiptSaved = receipt;
        },
        error: (err) => {
          this.message = "Error al crear recibo: " + err.error;
          this.lastReceiptSaved = null;
        }
      });
    }
  }

  generarPDF() {
    if (!this.lastReceiptSaved) return;

    const receipt = this.lastReceiptSaved;

    const fecha = new Date(receipt.date);
    const meses = [
      'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
      'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
    ];
    const mesNombre = meses[fecha.getMonth()];

    this.tenantService.getTenantByDni(receipt.dniTenant).subscribe({
      next: (tenant) => {
        const doc = new jsPDF();
        doc.setFontSize(16);
        doc.text('Recibo de Alquiler', 70, 20);

        const data = [
          ['Fecha', fecha.toDateString()],
          ['Mes', mesNombre],
          ['Nombre y Apellido', `${tenant.name} ${tenant.lastname}`],
          ['Monto Alquiler', `$${receipt.rent}`],
          ['Expensas', `$${receipt.expenses}`],
          ['Obras Sanitarias', `$${receipt.obrasSanitarias}`],
          ['Total en Letras', receipt.totalInString],
          ['DNI Inquilino', receipt.dniTenant],
        ];

        autoTable(doc, {
          startY: 30,
          body: data
        });

        doc.save(`recibo_${receipt.dniTenant}.pdf`);
      },
      error: (err) => {
        console.error("Error obteniendo inquilino:", err);
      }
    });
  }
}
