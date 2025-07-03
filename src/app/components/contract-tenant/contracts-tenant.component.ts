import { Component } from '@angular/core';
import { Contract } from '../../models/contract.model';
import { ContractService } from '../../services/contract.service';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { ContractResponseDro } from '../../DTO/contract-response-dto';

@Component({
  selector: 'app-contracts-tenant',
  standalone: true,
  imports: [FormsModule, RouterModule, NgIf, NgFor, CommonModule],
  templateUrl: './contracts-tenant.component.html',
  styleUrl: './contracts-tenant.component.css'
})
export class ContractsTenantComponent {
  dniTenant: string = ''
  contracts: ContractResponseDro[] = []
  message: string = ''

  constructor(private contractService: ContractService){}

  getContractsByTenant(){
    this.contractService.getContractsByTenant(this.dniTenant).subscribe({
      next: (data) => {
        this.contracts = data
      },
      error: (error) => {
        this.message = error.error || 'No se econtraron contractos con ese DNI'
      }
    })
  }
}
