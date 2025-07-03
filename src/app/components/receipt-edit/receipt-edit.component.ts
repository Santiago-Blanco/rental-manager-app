import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ReceiptEdit } from '../../models/receipt-edit';
import { ReceiptService } from '../../services/receipt.service';
import { NgIf } from '@angular/common';
import { Receipt } from '../../models/receipt.model';
import { ReceitpResponseDto } from '../../DTO/receitp-response-dto';

@Component({
  selector: 'app-receipt-edit',
  standalone: true,
  imports: [RouterModule, FormsModule, NgIf],
  templateUrl: './receipt-edit.component.html',
  styleUrl: './receipt-edit.component.css'
})
export class ReceiptEditComponent {
  id: number = 0;
  receiptEdit: ReceiptEdit = {};
  message: string = '';
  receiptResponse: ReceitpResponseDto | null = null

  constructor(private receiptService: ReceiptService) { }

  searchReceipt() {
    this.receiptService.getReceipt(this.id).subscribe({
      next: (data) => {
        this.receiptResponse = data
      },
      error: (err) => {
        this.message = err.error || 'Error al buscar recibo'
      }
    })
  }


  editReceipt() {
    this.receiptService.editReceipt(this.id, this.receiptEdit).subscribe({
      next: () => {
        this.receiptEdit = {};
        this.id = 0;
      },
      error: () => {
        this.message = 'Error al editar el recibo.';
      }
    });
  }
}
