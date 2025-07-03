import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { RouterModule } from '@angular/router';
import { TenantService } from '../../services/tenant.service';

@Component({
  selector: 'app-tenant-form',
  templateUrl: './tenant-form.component.html',
  standalone: true,
  imports:[ReactiveFormsModule, NgIf, RouterModule],
})
export class TenantFormComponent {
  tenantForm: FormGroup
  message: string = ''

  constructor(private fb: FormBuilder, private tenantService: TenantService) {
   this.tenantForm = this.fb.group({
    name: ['', Validators.required],
    lastname: ['', Validators.required],
    dni: ['', Validators.required],
    propertyId: [null],
    departmentId: [null], 
    contractId: [null]
    });

  }

  onSubmit() {
    if (this.tenantForm.valid) {
      this.tenantService.createTenant(this.tenantForm.value).subscribe({
        next: () =>{
          this.tenantForm.reset()
          this.message = 'Inquilino creado con exito'
        },
        error: (err) =>{
          this.message = 'Error al crear al inquilino' + err.error
        }
      })
    }
  }
}
