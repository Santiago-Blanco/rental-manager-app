import { Injectable } from '@angular/core';
import { Department } from '../models/department.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { DepartmentCreate } from '../models/department-create';
import { PropertyEdit } from '../models/property-edit';
import { DepartmentEdit } from '../models/department-edit';
import { DepartmentResponseDTO } from '../DTO/department-response-dto';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  private baseUrl: string = "http://localhost:8080/departamento"

  constructor(private http: HttpClient) { }

  saveDepartment(department: DepartmentCreate): Observable<DepartmentResponseDTO> {
    return this.http.post<DepartmentResponseDTO>(`${this.baseUrl}/crear`, department);
  }

  getDepartment(property: string, floor: number, letter: string): Observable<DepartmentResponseDTO> {
    return this.http.get<DepartmentResponseDTO>(`${this.baseUrl}/buscar/${property}/${floor}/${letter}`);
  }

  getDepartments(): Observable<DepartmentResponseDTO[]> {
    return this.http.get<DepartmentResponseDTO[]>(`${this.baseUrl}/todos`);
  }

  getDepartmentsByProperty(propertyName: string): Observable<DepartmentResponseDTO[]> {
    return this.http.get<DepartmentResponseDTO[]>(`${this.baseUrl}/todos/${propertyName}`);
  }

  releaseDepartment(propertyName: string, floor: number, letter: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/liberar/${propertyName}/${floor}/${letter}`, {});
  }

  editDepartment(propertyName: string, floor: number, letter: string, department: DepartmentEdit): Observable<DepartmentResponseDTO> {
    return this.http.put<DepartmentResponseDTO>(`${this.baseUrl}/editar/${propertyName}/${floor}/${letter}`, department);
  }

  deleteDepartment(property: string, floor: number, letter: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/eliminar/${property}/${floor}/${letter}`);
  }

  assignTenant(propertyName: string, dni: string, floor: number, letter: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/asignar/inquilino/${propertyName}/${dni}/${floor}/${letter}`, {});
  }

  getAvailable(): Observable<DepartmentResponseDTO[]> {
    return this.http.get<DepartmentResponseDTO[]>(`${this.baseUrl}/disponibles`);
  }

  autoReleaseDepartment(): Observable<DepartmentResponseDTO[]> {
    return this.http.get<DepartmentResponseDTO[]>(`${this.baseUrl}/vencimiento/contrato`);
  }
}
