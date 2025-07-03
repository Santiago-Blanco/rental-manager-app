import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TenantEdit } from '../../models/tenant-edit';
import { TenantService } from '../../services/tenant.service';

@Component({
  selector: 'app-edit-tenant',
  templateUrl: './tenant-edit.component.html',
  styleUrl: './tenant-edit.component.css',
  standalone: true,
  imports: [RouterModule, CommonModule, FormsModule]
})
export class EditTenantComponent {
  dni: string = '';
  tenant: TenantEdit = {
    name: '',
    lastname: '',
    dni: ''
  };
  encontrado: boolean = false;
  mensaje: string = '';

  constructor(private tenantService: TenantService) {}

  searchTenant() {
    this.tenantService.getTenantByDni(this.dni).subscribe({
      next: (data) => {
        this.tenant = { ...data };
        this.encontrado = true;
        this.mensaje = '';
      },
      error: () => {
        this.mensaje = 'No se encontró un inquilino con ese DNI.';
        this.encontrado = false;
      }
    });
  }

  editTenant() {
    this.tenantService.editTenant(this.dni, this.tenant).subscribe({
      next: () => {
        this.mensaje = 'Inquilino editado correctamente.';
        this.encontrado = false;
      },
      error: () => {
        this.mensaje = 'Error al editar el inquilino.';
      }
    });
  }
}