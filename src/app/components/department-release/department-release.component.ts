import { Component } from '@angular/core';
import { DepartmentService } from '../../services/department.service';
import { Department } from '../../models/department.model';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { DepartmentResponseDTO } from '../../DTO/department-response-dto';

@Component({
  selector: 'app-department-release',
  standalone: true,
  imports: [RouterModule, FormsModule, NgIf],
  templateUrl: './department-release.component.html',
  styleUrl: './department-release.component.css'
})
export class DepartmentReleaseComponent {
  propertyName: string = '';
  floor: number = 0;
  letter: string = '';
  department: DepartmentResponseDTO | null = null;
  message: string = '';

  constructor(private departmentService: DepartmentService) {}

  releaseDepartment() {
    this.departmentService.releaseDepartment(this.propertyName, this.floor, this.letter).subscribe({
      next: (data) => {
        this.department = data;
      },
      error: (err) => {
        this.message = err.error || 'Ha ocurrido un problema cuando se intentó liberar el departamento';
      }
    });
  }
}
