import { Component } from '@angular/core';
import { ContractService } from '../../services/contract.service';
import { ContractEdit } from '../../models/contract-edit';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgIf } from '@angular/common';
import { ContractResponseDro } from '../../DTO/contract-response-dto';

@Component({
  selector: 'app-contract-edit',
  standalone: true,
  imports: [FormsModule, RouterModule, NgIf],
  templateUrl: './contract-edit.component.html',
  styleUrl: './contract-edit.component.css'
})
export class ContractEditComponent {
  id: number = 0
  contract: ContractEdit | null = null
  contractResponse: ContractResponseDro | null = null
  message: string = ''

  constructor(private contractService: ContractService) { }

  searchContract() {
    this.contractService.getContract(this.id).subscribe({
      next: (data) => {
        this.contractResponse = data
        this.contract = {
        startDate: data.startDate,
        endDate: data.endDate,
        adjustment: data.adjustment,
        dni: data.dni 
      };
      },
      error: (err) => {
        this.contract = null
        this.message = err.error || 'No se ha encontrado un contrato con esa ID'
      }
    })
  }

  editContract() {
    if (!this.contract) return;

    this.contractService.editContract(this.id, this.contract).subscribe({
      next: (data) => {
        this.message = 'Contrato editado exitosamente.'
        console.log(this.contract)
      },
      error: (err) => {
        this.message = err.error || 'Ha ocurrido un error cuando se quiso editar el contrato'
      }
    })
  }
}
