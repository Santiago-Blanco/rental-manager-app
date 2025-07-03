import { Component } from '@angular/core';
import { Contract } from '../../models/contract.model';
import { ContractService } from '../../services/contract.service';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonModule, NgIf } from '@angular/common';
import { ContractResponseDro } from '../../DTO/contract-response-dto';

@Component({
  selector: 'app-contract',
  standalone: true,
  imports: [FormsModule, RouterModule, NgIf, CommonModule],
  templateUrl: './contract.component.html',
  styleUrl: './contract.component.css'
})
export class ContractComponent {
  id: number = 0
  contract: ContractResponseDro | null = null
  message: string = ''

  constructor(private contractService: ContractService){}

  searchContract(){
    this.contractService.getContract(this.id).subscribe({
      next: (data) => {
        this.contract = data
      },
      error: (error) => {
        this.message = error.error || 'No existe ningun contrato con esa ID'
      }
    })
  }
}
