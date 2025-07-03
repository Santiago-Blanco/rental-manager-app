import { Component } from '@angular/core';
import { DepartmentService } from '../../services/department.service';
import { Department } from '../../models/department.model';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { DepartmentResponseDTO } from '../../DTO/department-response-dto';

@Component({
  selector: 'app-department-assign-tenant',
  standalone: true,
  imports: [RouterModule, FormsModule, NgIf],
  templateUrl: './department-assign-tenant.component.html',
  styleUrl: './department-assign-tenant.component.css'
})
export class DepartmentAssignTenantComponent {
  propertyName: string = '';
  floor: number = 0;
  letter: string = '';
  dniTenant: string = '';
  department?: DepartmentResponseDTO;
  departmentMessage: string = '';

  constructor(private departmentService: DepartmentService, private router: Router) {}

  assingTenant() {
    this.departmentService.assignTenant(this.propertyName, this.dniTenant, this.floor, this.letter).subscribe({
      next: (data) => {
        this.department = data;
        this.departmentMessage = '';
        this.router.navigate(['/contrato/crear']);
      },
      error: (err) => {
        this.departmentMessage = err.error || 'No se encontró ningún departamento con esos datos';
        this.department = undefined;
      }
    });
  }
}
