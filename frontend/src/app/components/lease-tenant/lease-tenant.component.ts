import { Component } from '@angular/core';
import { Lease } from '../../models/lease.model';
import { LeaseService } from '../../services/lease.service';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { LeaseResponseDto } from '../../DTO/lease-response-dto';

@Component({
  selector: 'app-lease-tenant',
  standalone: true,
  imports: [RouterModule, FormsModule, NgIf, NgFor, CommonModule],
  templateUrl: './lease-tenant.component.html',
  styleUrl: './lease-tenant.component.css'
})
export class LeaseTenantComponent {
  dniTenant: string = ''
  leaseList: LeaseResponseDto[] | null = null
  message: string = ''

  constructor(private leaseService: LeaseService) { }

  getLeaseByTenant() {
    this.leaseService.getLeaseByTenant(this.dniTenant).subscribe({
      next: (data: LeaseResponseDto[]) => {
        this.leaseList = data
      },
      error: (err) => {
        this.message = err.error || 'No hay datos con ese DNI.'
      }
    })
  }
}
