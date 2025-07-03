import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from "./components/header/header.component";
import { Department } from './models/department.model';
import { DepartmentService } from './services/department.service';
import { NgFor, NgIf } from '@angular/common';
import { DepartmentResponseDTO } from './DTO/department-response-dto';
import { ContractService } from './services/contract.service';
import { ContractResponseDro } from './DTO/contract-response-dto';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, HeaderComponent, NgIf, NgFor],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  releasedDepartments: DepartmentResponseDTO[] = []
  contractAdjustment: ContractResponseDro[] = []
  contractFinishing: ContractResponseDro[] = []

  showReleasedDepartments: boolean = true
  showContractAdjustment: boolean = true
  showContractFinish: boolean = true

  messageDpto: string = ''
  messageContractAdjustment: string = ''
  messageContractFinish: string = ''

  constructor(private departmentService: DepartmentService, private contractService: ContractService) {}

  ngOnInit() {
    this.departmentService.autoReleaseDepartment().subscribe({
      next: (data) => {
        this.releasedDepartments = data
      },
      error: (err) => {
        this.messageDpto = err.error || 'Error al verificar contratos vencidos'
      }
    });

    this.contractService.alertAdjustment().subscribe({
      next: (data) => {
        this.contractAdjustment = data
      },
      error: (err) => {
        this.messageContractAdjustment = err.error || 'Error al verificar los ajustes de los contratos'
      }
    });

    this.contractService.contractExpiringSoon().subscribe({
      next: (data) =>{
        this.contractFinishing = data
      },
      error: (err) =>{
        this.messageContractFinish = err.error || 'Error al verificar los contratos que finalizan'
      }
    })
  }

  hideReleasedDepartments() {
    this.showReleasedDepartments = false
  }

  hideContractAdjustment() {
    this.showContractAdjustment = false
  }

  hideContractFinish(){
    this.showContractFinish = false
  }
}
