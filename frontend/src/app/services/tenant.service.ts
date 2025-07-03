import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TenantEdit } from '../models/tenant-edit';
import { TenantResponseDTO } from '../DTO/tenant-response-dto';
import { TenantCreate } from '../models/tenant-create';

@Injectable({
  providedIn: 'root'
})
export class TenantService {
  private baseUrl = 'http://localhost:8080/inquilino'; 

  constructor(private http: HttpClient) {}

  createTenant(tenant: TenantCreate): Observable<TenantResponseDTO> {
  return this.http.post<TenantResponseDTO>(`${this.baseUrl}/crear`, tenant);
}

getTenantByDni(dni: string): Observable<TenantResponseDTO> {
  return this.http.get<TenantResponseDTO>(`${this.baseUrl}/${dni}`);
}

getAllTenants(): Observable<TenantResponseDTO[]> {
  return this.http.get<TenantResponseDTO[]>(`${this.baseUrl}/todos`);
}

editTenant(dni: string, tenantEdit: TenantEdit): Observable<TenantResponseDTO> {
  return this.http.put<TenantResponseDTO>(`${this.baseUrl}/editar/${dni}`, tenantEdit);
}

deleteTenant(dni: string): Observable<any> {
  return this.http.delete(`${this.baseUrl}/eliminar/${dni}`);
}
}
