import { Component } from '@angular/core';
import { PropertyService } from '../../services/property.service';
import { Property } from '../../models/property.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PropertyResponseDTO } from '../../DTO/property-response-dto';

@Component({
  selector: 'app-property-delete',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './property-delete.component.html',
  styleUrl: './property-delete.component.css'
})
export class PropertyDeleteComponent {
  propertyName: string = ''
  property?: PropertyResponseDTO 
  message: string = ''

  constructor(private propertyService: PropertyService){}

  searchPropertyByName(){
    this.propertyService.searchPropertyByName(this.propertyName).subscribe({
      next: (data) => {
        this.property = data
      },
      error: (err) => {
        this.message = "No se ha encontrado ninguna propiedad con ese nombre."
      }
    })
  }

  deleteProperty(){
    this.propertyService.deleteProperty(this.propertyName).subscribe({
      next: (res) =>{
        this.propertyName = ''
        this.property = undefined
        this.message = "Propiedad eliminada correctamente"
      },
      error: (err) => {
        this.message = "Error al elimnar la propiedad"
      }
    })
  }
}
