import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Receipt } from '../../models/receipt.model';
import { ReceiptService } from '../../services/receipt.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { ReceitpResponseDto } from '../../DTO/receitp-response-dto';

@Component({
  selector: 'app-receipt-list',
  standalone: true,
  imports: [RouterModule, NgIf, NgFor, CommonModule],
  templateUrl: './receipt-list.component.html',
  styleUrl: './receipt-list.component.css'
})
export class ReceiptListComponent {
  receipts: ReceitpResponseDto[] | null = null
  message: string = ''

  constructor(private receiptService: ReceiptService) { }

  getReceiptsList() {
    this.receiptService.getReceipts().subscribe({
      next: (data) => {
        this.receipts = data
      },
      error: (err) => {
        this.message = err.error || "No hay recibos."
      }
    })
  }

  ngOnInit() {
    this.getReceiptsList()
  }

}
