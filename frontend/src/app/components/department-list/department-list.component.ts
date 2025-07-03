import { Component } from '@angular/core';
import { DepartmentService } from '../../services/department.service';
import { Department } from '../../models/department.model';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';
import { RouterModule } from '@angular/router';
import { DepartmentResponseDTO } from '../../DTO/department-response-dto';

@Component({
  selector: 'app-department-list',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor, RouterModule],
  templateUrl: './department-list.component.html',
  styleUrl: './department-list.component.css'
})
export class DepartmentListComponent {
  department: DepartmentResponseDTO [] = []
  message: string = ''

  constructor(private departmentService: DepartmentService){}

  ngOnInit(){
    this.departmentService.getDepartments().subscribe({
      next: (data) =>{
        this.department = data
      },
      error: (err) => {
        this.message = err.error || 'Error al obtener los departamentos'
      }
    })
  }
}
