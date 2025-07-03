import { Component } from '@angular/core';
import { PropertyEdit } from '../../models/property-edit';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { PropertyService } from '../../services/property.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-property-edit',
  standalone: true,
  imports: [FormsModule, RouterModule, CommonModule],
  templateUrl: './property-edit.component.html',
  styleUrl: './property-edit.component.css'
})
export class PropertyEditComponent {
  propertyName: string = ''
  propertyToEdit: PropertyEdit = { adress: '', propertyName: '' }
  message: string = ''

  constructor(private propertyService: PropertyService) { }

  searchPropertyByName() {
    this.message = ''
    this.propertyToEdit = { adress: '', propertyName: '' }
    this.propertyService.searchPropertyByName(this.propertyName).subscribe({
      next: (propertyEdit: PropertyEdit) => {
        this.propertyToEdit = {
          adress: propertyEdit.adress,
          propertyName: propertyEdit.propertyName
        };
      },
      error: () => {
        this.message = "No se ha encontrado ninguna propiedad con ese nombre."
        this.propertyToEdit = { adress: '', propertyName: '' }
      }
    });
  }

  editProperty() {
    this.message = ''
    this.propertyService.editProperty(this.propertyName, this.propertyToEdit).subscribe({
      next: () => {
        this.message = "Propiedad editada correctamente"
        this.propertyToEdit = { adress: '', propertyName: '' }
        this.propertyName = ''
      },
      error: () => {
        this.message = "Error al editar propiedad"
      }
    })
  }
}
