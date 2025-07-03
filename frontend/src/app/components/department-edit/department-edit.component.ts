import { Component } from '@angular/core';
import { Department } from '../../models/department.model';
import { DepartmentEdit } from '../../models/department-edit';
import { DepartmentService } from '../../services/department.service';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule, NgIf } from '@angular/common';
import { DepartmentResponseDTO } from '../../DTO/department-response-dto';

@Component({
  selector: 'app-department-edit',
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule, NgIf],
  templateUrl: './department-edit.component.html',
  styleUrl: './department-edit.component.css'
})
export class DepartmentEditComponent {
  propertyName: string = '';
  floor: number = 0;
  letter: string = '';

  departmentToEdit: DepartmentEdit = {};
  updatedDepartment: DepartmentResponseDTO | null = null;

  message: string = '';

  constructor(private departmentService: DepartmentService) {}

  searchDepartment() {
    this.message = '';
    this.updatedDepartment = null;

    this.departmentService.getDepartment(this.propertyName, this.floor, this.letter).subscribe({
      next: (data) => {
        this.updatedDepartment = data;
        this.departmentToEdit = {
          floor: data.floor,
          letter: data.letter,
        };
      },
      error: (error) => {
        this.message = error.error || 'Departamento no encontrado.';
      }
    });
  }

  editDepartment() {
    if (!this.updatedDepartment) return;

    this.departmentService.editDepartment(this.propertyName, this.floor, this.letter, this.departmentToEdit).subscribe({
      next: (data) => {
        this.updatedDepartment = data;
        this.message = 'Departamento actualizado correctamente.';
      },
      error: (error) => { 
        this.message = error.error || 'Error al editar el departamento.';
      }
    });
  }
}
