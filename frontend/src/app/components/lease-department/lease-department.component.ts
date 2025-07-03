import { Component } from '@angular/core';
import { Lease } from '../../models/lease.model';
import { LeaseService } from '../../services/lease.service';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { LeaseResponseDto } from '../../DTO/lease-response-dto';

@Component({
  selector: 'app-lease-department',
  standalone: true,
  imports: [RouterModule, FormsModule, NgIf, NgFor, CommonModule],
  templateUrl: './lease-department.component.html',
  styleUrl: './lease-department.component.css'
})
export class LeaseDepartmentComponent {
  propertyName: string = ''
  floor: number = 0
  letter: string = ''
  leaseList: LeaseResponseDto[] | null = null
  message: string = ''

  constructor(private leaseService: LeaseService) { }

  getLeaseByDepartment() {
    this.leaseService.getLeaseByDepartment(this.propertyName, this.floor, this.letter).subscribe({
      next: (data: LeaseResponseDto[]) => {
        this.leaseList = data
      },
      error: (err) => {
        this.message = err.error || 'No hay datos para ese departamento.'
      }
    }
    )
  }
}
