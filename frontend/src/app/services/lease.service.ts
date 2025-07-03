import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LeaseEdit } from '../models/lease-edit';
import { LeaseResponseDto } from '../DTO/lease-response-dto';

@Injectable({
  providedIn: 'root'
})
export class LeaseService {
  private baseUrl: string = 'http://localhost:8080/historial'

  constructor(private http: HttpClient) { }

  getLease(id: number): Observable<LeaseResponseDto>{
    return this.http.get<LeaseResponseDto>(`${this.baseUrl}/${id}`)
  }

  getAll(): Observable<LeaseResponseDto[]>{
    return this.http.get<LeaseResponseDto[]>(`${this.baseUrl}/todos`)
  }

  getLeaseByTenant(dniTenant: string): Observable<LeaseResponseDto[]>{
    return this.http.get<LeaseResponseDto[]>(`${this.baseUrl}/inquilino/${dniTenant}`)
  }

  getLeaseByDepartment(propertyName: string, floor: number, letter: string): Observable<LeaseResponseDto[]>{
    return this.http.get<LeaseResponseDto[]>(`${this.baseUrl}/departamento/${propertyName}/${floor}/${letter}`)
  }

  editLease(id: number, lease: LeaseEdit): Observable<LeaseResponseDto>{
    return this.http.put<LeaseResponseDto>(`${this.baseUrl}/editar/${id}`, lease)
  }
}
