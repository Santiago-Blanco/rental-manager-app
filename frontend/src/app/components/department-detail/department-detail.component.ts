import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { DepartmentService } from '../../services/department.service';
import { Department } from '../../models/department.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { DepartmentResponseDTO } from '../../DTO/department-response-dto';

@Component({
  selector: 'app-department-detail',
  templateUrl: './department-detail.component.html',
  styleUrls: ['./department-detail.component.css'],
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule]
})
export class DepartmentDetailComponent implements OnInit {
  department: DepartmentResponseDTO | null = null;
  mensaje: string = '';
  propertyName!: string;
  floor!: number;
  letter!: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private departmentService: DepartmentService
  ) {}

  ngOnInit(): void {
    this.propertyName = this.route.snapshot.paramMap.get('propertyName')!;
    this.floor = Number(this.route.snapshot.paramMap.get('floor'));
    this.letter = this.route.snapshot.paramMap.get('letter')!;
    this.loadDepartment();
  }

  loadDepartment(): void {
    this.departmentService.getDepartment(this.propertyName, this.floor, this.letter).subscribe({
      next: (dept) => this.department = dept,
      error: (err) => this.mensaje = 'Error al obtener el departamento: ' + (err.error?.message || err.message)
    });
  }

  releaseDepartment(): void {
    if (!this.department) return;
    this.departmentService.releaseDepartment(this.propertyName, this.floor, this.letter)
      .subscribe({
        next: () => {
          this.mensaje = 'Departamento liberado correctamente.';
          this.loadDepartment(); // actualiza datos
        },
        error: (err) => {
          this.mensaje = 'Error al liberar departamento: ' + (err.error?.message || err.message);
        }
      });
  }

  deleteDepartment(): void {
    if (!this.department) return;
    this.departmentService.deleteDepartment(this.propertyName, this.floor, this.letter)
      .subscribe({
        next: () => {
          this.mensaje = 'Departamento eliminado correctamente.';
          this.router.navigate(['/propiedad']); // cambia si querés otro destino
        },
        error: (err) => {
          this.mensaje = 'Error al eliminar departamento: ' + (err.error?.message || err.message);
        }
      });
  }
}
