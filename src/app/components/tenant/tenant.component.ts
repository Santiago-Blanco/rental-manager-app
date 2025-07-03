import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Tenant } from '../../models/tenant.model';
import { TenantService } from '../../services/tenant.service';
import { TenantResponseDTO } from '../../DTO/tenant-response-dto';


@Component({
  selector: 'app-tenant',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './tenant.component.html',
  styleUrl: './tenant.component.css'
})
export class TenantComponent {
  dni: string = '';
  tenant?: TenantResponseDTO;
  errorMessage: string = '';

  constructor(private tenantService: TenantService) { }

  searchForDni() {
    this.tenantService.getTenantByDni(this.dni).subscribe({
      next: (data) => {
        this.tenant = data;
        this.errorMessage = '';
      },
      error: (err) => {
        this.errorMessage = err.error || 'No se encontró inquilino con ese DNI.';
        this.tenant = undefined;
      }
    });
  }
}