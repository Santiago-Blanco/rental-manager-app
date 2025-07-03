import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Contract } from '../../models/contract.model';
import { FormsModule } from '@angular/forms';
import { ContractService } from '../../services/contract.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { ContractResponseDro } from '../../DTO/contract-response-dto';

@Component({
  selector: 'app-contract-list',
  standalone: true,
  imports: [RouterModule, FormsModule, NgIf, NgFor, CommonModule],
  templateUrl: './contract-list.component.html',
  styleUrl: './contract-list.component.css'
})
export class ContractListComponent {
  contracts: ContractResponseDro[] = []
  message: string = ''

  constructor(private contractService: ContractService) { }

  ngOnInit() {
    this.getContracts()
  }

  getContracts() {
    this.contractService.getContracts().subscribe({
      next: (data) => {
        this.contracts = data
      },
      error: (err) => {
        this.message = err.error || 'No hay contratos'
      }
    })
  }
}
