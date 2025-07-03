import { Component } from '@angular/core';
import { Property } from '../../models/property.model';
import { PropertyService } from '../../services/property.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PropertyResponseDTO } from '../../DTO/property-response-dto';

@Component({
  selector: 'app-property',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './property.component.html',
  styleUrl: './property.component.css'
})
export class PropertyComponent {
  propertyName: string = ''
  property?: PropertyResponseDTO | null = null
  errorMessage: string = ''

  constructor(private propertyService: PropertyService) { }

  searchPropertyByName() {
    this.propertyService.searchPropertyByName(this.propertyName).subscribe({
      next: (data) => {
        this.property = data
        this.errorMessage = ''
      },
      error: (err) => {
        this.property = null
        this.errorMessage = 'No se encontro ninguna propiedad con ese nombre'
      }
    })
  }
}
