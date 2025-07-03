import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Tenant } from '../../models/tenant.model';
import { TenantService } from '../../services/tenant.service';
import { TenantResponseDTO } from '../../DTO/tenant-response-dto';

@Component({
  selector: 'app-tenant-list',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './tenant-list.component.html',
  styleUrl: './tenant-list.component.css'
})
export class TenantListComponent implements OnInit{
  tenants: TenantResponseDTO[] = [];
  errorMessage: string = ''

  constructor(private tenantService: TenantService){}
  
  ngOnInit(): void {
    this.tenantService.getAllTenants().subscribe({
      next: (data) => {
        this.tenants = data;
      },
      error: (err) => {
        this.errorMessage =err.error || "Error al obtener los inquilinos "
      }
    });
  }
}
