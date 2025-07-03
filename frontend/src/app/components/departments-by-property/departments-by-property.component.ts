import { Component } from '@angular/core';
import { Department } from '../../models/department.model';
import { DepartmentService } from '../../services/department.service';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgFor, NgIf } from '@angular/common';
import { DepartmentResponseDTO } from '../../DTO/department-response-dto';

@Component({
  selector: 'app-departments-by-property',
  standalone: true,
  imports: [FormsModule, RouterModule, NgIf, NgFor],
  templateUrl: './departments-by-property.component.html',
  styleUrl: './departments-by-property.component.css'
})
export class DepartmentsByPropertyComponent {
  departments: DepartmentResponseDTO[] = []
  propertyName: string= ''
  message: string = ''

  constructor(private departmentService: DepartmentService){}

  getdepartmentsByProperty(){
    this.departmentService.getDepartmentsByProperty(this.propertyName).subscribe({
      next: (data) => {
        this.departments = data
      },
      error: (err) => {
        this.message = err.error || 'No se encontrar departmentos en esa propiedad'
      }
    })
  }
}
