import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Receipt } from '../../models/receipt.model';
import { ReceiptService } from '../../services/receipt.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { ReceitpResponseDto } from '../../DTO/receitp-response-dto';

@Component({
  selector: 'app-receipt-tenant',
  standalone: true,
  imports: [RouterModule, FormsModule, NgIf, NgFor, CommonModule],
  templateUrl: './receipt-tenant.component.html',
  styleUrl: './receipt-tenant.component.css'
})
export class ReceiptTenantComponent {
  dniTenant: string = ''
  receiptsByTenant: ReceitpResponseDto[] | null = null
  message: string = ''

  constructor(private receiptService: ReceiptService) { }


  listReceiptsByTenant() {
    this.receiptService.getReceiptByTenant(this.dniTenant).subscribe({
      next: (data) => {
        this.receiptsByTenant = data
      },
      error: (err) => {
        this.message = err.error || 'No existen recibos de un inquilino con ese DNI.'
      }
    })
  }
}
