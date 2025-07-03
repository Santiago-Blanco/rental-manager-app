import { Component } from '@angular/core';
import { Receipt } from '../../models/receipt.model';
import { ReceiptService } from '../../services/receipt.service';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule, NgIf } from '@angular/common';
import { ReceitpResponseDto } from '../../DTO/receitp-response-dto';

@Component({
  selector: 'app-receipt',
  standalone: true,
  imports: [RouterModule, FormsModule, NgIf, CommonModule],
  templateUrl: './receipt.component.html',
  styleUrl: './receipt.component.css'
})
export class ReceiptComponent {
  id: number = 0
  receipt?: ReceitpResponseDto
  message: string = ''

  constructor(private receiptService: ReceiptService) { }

  getReceipt() {
    this.receiptService.getReceipt(this.id).subscribe({
      next: (data) => {
        this.receipt = data
      },
      error: (err) => {
        this.message = err.error || 'No se encontro recibo con ese ID'
      }
    })
  }
}
