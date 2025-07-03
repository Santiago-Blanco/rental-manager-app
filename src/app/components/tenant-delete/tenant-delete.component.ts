import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Tenant } from '../../models/tenant.model';
import { TenantService } from '../../services/tenant.service';
import { CommonModule } from '@angular/common';
import { TenantResponseDTO } from '../../DTO/tenant-response-dto';

@Component({
  selector: 'app-tenant-delete',
  standalone: true,
  imports: [RouterModule, CommonModule, FormsModule],
  templateUrl: './tenant-delete.component.html',
  styleUrl: './tenant-delete.component.css'
})
export class TenantDeleteComponent {
  dni: string = ''
  tenant: TenantResponseDTO | null = null
  message: string = ''

  constructor(private tenantService: TenantService){}

  searchTenant(){
    this.tenantService.getTenantByDni(this.dni).subscribe({
      next: (data) => {
        this.tenant = data;
      },
      error: (err) => {
        this.message = err.error || 'Inquilino no encontrado'
      }
    });
  }

  deleteTenant(){
    this.tenantService.deleteTenant(this.dni).subscribe({
      next: () => {
        this.message = 'Inquilino eliminado correctamente.';
        this.tenant = null;
      },
      error: (err) => {
        this.message = err.error || 'Error al eliminar el inquilino.'
      }
    });
  }
}
