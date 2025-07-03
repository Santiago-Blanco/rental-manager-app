import { Component } from '@angular/core';
import { Department } from '../../models/department.model';
import { DepartmentService } from '../../services/department.service';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgFor, NgIf } from '@angular/common';
import { DepartmentResponseDTO } from '../../DTO/department-response-dto';

@Component({
  selector: 'app-departments-available',
  standalone: true,
  imports: [FormsModule, RouterModule, NgIf, NgFor],
  templateUrl: './departments-available.component.html',
  styleUrl: './departments-available.component.css'
})
export class DepartmentsAvailableComponent {
  departments: DepartmentResponseDTO[] = []
  message: string = ''

  constructor(private departmentService: DepartmentService) { }

  ngOnInit(){
    this.getDepartmentsAvailable()
  }

  getDepartmentsAvailable() {
    this.departmentService.getAvailable().subscribe({
      next: (data) => {
        this.departments = data
      },
      error: (error) => {
        this.message = error.error || 'Ha ocurrido un error al intentar obtener los departamentos disponibles'
      }
    })
  }
}
