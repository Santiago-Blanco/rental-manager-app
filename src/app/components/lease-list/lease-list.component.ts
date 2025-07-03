import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Lease } from '../../models/lease.model';
import { LeaseService } from '../../services/lease.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { LeaseResponseDto } from '../../DTO/lease-response-dto';

@Component({
  selector: 'app-lease-list',
  standalone: true,
  imports: [RouterModule, FormsModule, NgIf, NgFor, CommonModule],
  templateUrl: './lease-list.component.html',
  styleUrl: './lease-list.component.css'
})
export class LeaseListComponent {
  leaseList: LeaseResponseDto[] | null = null
  message: string = ''

  constructor(private leaseService: LeaseService) { }

  ngOnInit() {
    this.getAllLease();
  }

  getAllLease() {
    this.leaseService.getAll().subscribe({
      next: (data: LeaseResponseDto[]) => {
        this.leaseList = data
      },
      error: (err) => {
        this.message = err.error || 'No hay datos.'
      }
    })
  }
}
