import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Receipt } from '../../models/receipt.model';
import { ReceiptService } from '../../services/receipt.service';
import { CommonModule, NgIf } from '@angular/common';
import { ReceitpResponseDto } from '../../DTO/receitp-response-dto';

@Component({
  selector: 'app-receipt-delete',
  standalone: true,
  imports: [RouterModule, FormsModule, NgIf, CommonModule],
  templateUrl: './receipt-delete.component.html',
  styleUrl: './receipt-delete.component.css'
})
export class ReceiptDeleteComponent {
  id: number = 0
  receipt: ReceitpResponseDto | null = null
  message: string = ''

  constructor(private receiptService: ReceiptService) { }

  searchReceipt(){
    this.receiptService.getReceipt(this.id).subscribe({
      next: (data) =>{
        this.receipt = data
      },
      error: (err) =>{
        this.message = err.error || 'No existe ningun recibo con ese ID.'
      }
    })
  }

  deleteReceipt() {
    this.receiptService.deleteReceipt(this.id).subscribe({
      next: (data) => {
        this.receipt = null
        this.message = "Recibo eliminado correctamente"
      },
      error: (err) => {
        this.message = err.error || 'No se encontro ningun recibo con ese ID.'
      }
    })
  }
}
