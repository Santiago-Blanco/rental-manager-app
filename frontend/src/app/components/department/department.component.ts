import { Component } from '@angular/core';
import { Department } from '../../models/department.model';
import { DepartmentService } from '../../services/department.service';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgIf } from '@angular/common';
import { DepartmentResponseDTO } from '../../DTO/department-response-dto';

@Component({
  selector: 'app-department',
  standalone: true,
  imports: [FormsModule, RouterModule, NgIf],
  templateUrl: './department.component.html',
  styleUrl: './department.component.css'
})
export class DepartmentComponent {
  department: DepartmentResponseDTO | null = null;
  propertyName: string = '';
  floor: number = 0;
  letter: string = '';
  message: string = '';

  constructor(private departmentService: DepartmentService) {}

  getDepartmentByPropertyFloorAndLetter() {
    this.departmentService.getDepartment(this.propertyName, this.floor, this.letter).subscribe({
      next: (data) => {
        this.department = data;
        this.message = '';
      },
      error: (error) => {
        this.department = null;
        this.message = error.error || 'No se encontró ningún departamento con esas características';
      }
    });
  }
}
