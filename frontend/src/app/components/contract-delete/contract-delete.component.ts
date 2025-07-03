import { Component } from '@angular/core';
import { Contract } from '../../models/contract.model';
import { ContractService } from '../../services/contract.service';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CommonModule, NgIf } from '@angular/common';
import { ContractResponseDro } from '../../DTO/contract-response-dto';

@Component({
  selector: 'app-contract-delete',
  standalone: true,
  imports: [FormsModule, RouterModule, NgIf, CommonModule],
  templateUrl: './contract-delete.component.html',
  styleUrl: './contract-delete.component.css'
})
export class ContractDeleteComponent {
  id: number = 0
  contract: ContractResponseDro | null = null
  message: string = ''

  constructor(private contractService: ContractService) { }

  searchContract() {
    this.contractService.getContract(this.id).subscribe({
      next: (data) => {
        this.contract = data
      },
      error: (err) => {
        this.message = err.error || 'No se ha encontrado ningun contrato con ese ID'
      }
    })
  }

  deleteContract() {
    this.contractService.deleteContract(this.id).subscribe({
      next: (data) => {
        this.contract = null
      },
      error: (err) => {
        this.message = err.error || 'Ha ocurrido un error cuando se intento eliminar el contrato'
      }
    })
  }
}
