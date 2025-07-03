import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { DepartmentService } from '../../services/department.service';
import { RouterModule } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-department-form',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule, NgIf],
  templateUrl: './department-form.component.html',
  styleUrl: './department-form.component.css'
})
export class DepartmentFormComponent {
  departmentForm: FormGroup
  message: string = ''

  constructor(private fb: FormBuilder, private departmentService: DepartmentService){
    this.departmentForm = fb.group({
      floor: ['', [Validators.required, Validators.min(0)]],
      letter:['', [Validators.required, Validators.maxLength(1)]],
      propertyName:['', [Validators.required]]
    })
  }

  onSubmit(){
    if(this.departmentForm.valid){
      this.departmentService.saveDepartment(this.departmentForm.value).subscribe({
        next: () => {
          this.departmentForm.reset()
          this.message = 'Departamento creado con exito'
        },
        error: (err) =>{
          this.message = 'Error al crear departmento: ' + err.error
        }
      })
    }
  }
}
