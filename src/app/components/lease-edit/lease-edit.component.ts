import { Component } from '@angular/core';
import { LeaseEdit } from '../../models/lease-edit';
import { LeaseService } from '../../services/lease.service';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-lease-edit',
  standalone: true,
  imports: [RouterModule, FormsModule, NgIf],
  templateUrl: './lease-edit.component.html',
  styleUrl: './lease-edit.component.css'
})
export class LeaseEditComponent {
  id: number = 0
  lease: LeaseEdit = {}
  message: string = ''

  constructor(private leaseService: LeaseService) { }

  editLease() {
    this.leaseService.editLease(this.id, this.lease).subscribe({
      next: (data) => {
        this.lease = data
      },
      error: (err) => {
        this.message = err.error || 'Error al intentar editar el historial.'
      }
    })
  }
}
