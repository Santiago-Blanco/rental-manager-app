import { Component } from '@angular/core';
import { ContractService } from '../../services/contract.service';
import { ContractCreate } from '../../models/contract-create';
import { AdjustmentType } from '../../models/adjustment-type';
import { Contract } from '../../models/contract.model';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule, NgIf } from '@angular/common';
import { ContractResponseDro } from '../../DTO/contract-response-dto';

@Component({
  selector: 'app-contract-form',
  standalone: true,
  imports: [RouterModule, FormsModule, NgIf, CommonModule],
  templateUrl: './contract-form.component.html',
  styleUrl: './contract-form.component.css'
})
export class ContractFormComponent {
  AdjustmentType = AdjustmentType;

  contractCreate: ContractCreate = {
    startDate: new Date(),
    endDate: new Date(),
    adjustment: AdjustmentType.EVERY_FOUR_MONTHS,
    dni: ''
  };

  contract: ContractResponseDro | null = null;
  message: string = '';

  constructor(private contractService: ContractService) { }

  saveContract() {
    this.contractService.saveContract(this.contractCreate).subscribe({
      next: (data) => {
        this.contract = data;
      },
      error: (err) => {
        this.message = err.error || 'Ha ocurrido un error cuando se intentó crear el contrato.';
      }
    });
  }
}
