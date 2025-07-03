import { Component } from '@angular/core';
import { Department } from '../../models/department.model';
import { DepartmentService } from '../../services/department.service';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgIf } from '@angular/common';
import { DepartmentResponseDTO } from '../../DTO/department-response-dto';

@Component({
  selector: 'app-department-delete',
  standalone: true,
  imports: [FormsModule, RouterModule, NgIf],
  templateUrl: './department-delete.component.html',
  styleUrl: './department-delete.component.css'
})
export class DepartmentDeleteComponent {
  propertyName: string = '';
  floor: number = 0;
  letter: string = '';
  department: DepartmentResponseDTO | null = null;
  message: string = '';

  constructor(private departmentService: DepartmentService) {}

  searchDepartment() {
    this.message = '';
    this.departmentService.getDepartment(this.propertyName, this.floor, this.letter).subscribe({
      next: (data) => {
        this.department = data;
      },
      error: (error) => {
        this.department = null;
        this.message = error.error || 'No se ha encontrado un departamento con esas características';
      }
    });
  }

  deleteDepartment() {
    this.message = '';
    this.departmentService.deleteDepartment(this.propertyName, this.floor, this.letter).subscribe({
      next: () => {
        this.department = null;
        this.message = 'Departamento eliminado correctamente';
      },
      error: (error) => {
        this.message = error.error || 'Ha ocurrido un error al intentar eliminar el departamento';
      }
    });
  }
}

