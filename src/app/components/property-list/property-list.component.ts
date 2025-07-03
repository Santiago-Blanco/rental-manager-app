import { Component, OnInit } from '@angular/core';
import { Property } from '../../models/property.model';
import { PropertyService } from '../../services/property.service';
import { CommonModule } from '@angular/common';
import { Department } from '../../models/department.model';
import { RouterModule } from '@angular/router';
import { PropertyResponseDTO } from '../../DTO/property-response-dto';

@Component({
  selector: 'app-property-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './property-list.component.html',
  styleUrl: './property-list.component.css'
})
export class PropertyListComponent implements OnInit{
  properties: PropertyResponseDTO[] = []
  errorMessage: string = ''
  
  constructor(private propertyService: PropertyService){}

  ngOnInit(): void {
    this.propertyService.getAllProperties().subscribe({
      next: (data) => {
        this.properties = data
        this.errorMessage = ''
      },
      error: (err) => {
        this.errorMessage = err.error || "No se encontraron propiedades"
      }
    })
  }
}
