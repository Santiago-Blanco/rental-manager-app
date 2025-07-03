import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { PropertyService } from '../../services/property.service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-property-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './property-form.component.html',
  styleUrl: './property-form.component.css'
})
export class PropertyFormComponent {
  propertyForm: FormGroup;
  mensaje: string = ''

  constructor(private fb: FormBuilder, private propertyService: PropertyService, private router: Router){
    this.propertyForm = fb.group({
      propertyName: ['', Validators.required],
      adress: ['', Validators.required]
    });
  }

  onSubmit(){
    if (this.propertyForm.valid) {
      this.propertyService.createProperty(this.propertyForm.value).subscribe({
        next: () => {
          this.mensaje = 'Propiedad creada exitosamente.';
          this.propertyForm.reset();
        },
        error: (err) => {
          this.mensaje = 'Error al crear la propiedad: ' + (err.error || 'Desconocido');
        }
      });
    }
  }
}
