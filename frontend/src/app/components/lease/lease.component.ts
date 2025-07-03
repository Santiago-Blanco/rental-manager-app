import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Lease } from '../../models/lease.model';
import { LeaseService } from '../../services/lease.service';
import { CommonModule, NgIf } from '@angular/common';
import { LeaseResponseDto } from '../../DTO/lease-response-dto';

@Component({
  selector: 'app-lease',
  standalone: true,
  imports: [RouterModule, FormsModule, NgIf, CommonModule],
  templateUrl: './lease.component.html',
  styleUrl: './lease.component.css'
})
export class LeaseComponent {
  id: number = 0
  lease?: LeaseResponseDto
  message: string = ''

  constructor(private leaseService: LeaseService) { }

  getLease() {
    this.leaseService.getLease(this.id).subscribe({
      next: (data: LeaseResponseDto) => {
        this.lease = data
      },
      error: (err) => {
        this.message = err.error || 'No hay datos con ese ID.'
      }
    })
  }
}
