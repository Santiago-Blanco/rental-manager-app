import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Property } from '../models/property.model';
import { PropertyEditComponent } from '../components/property-edit/property-edit.component';
import { PropertyEdit } from '../models/property-edit';
import { PropertyResponseDTO } from '../DTO/property-response-dto';
import { PropertyCreate } from '../models/property-create';

@Injectable({
  providedIn: 'root'
})
export class PropertyService {

  private baseUrl = 'http://localhost:8080/propiedad';

  constructor(private http: HttpClient) { }

  createProperty(property: PropertyCreate): Observable<PropertyResponseDTO> {
    return this.http.post<PropertyResponseDTO>(`${this.baseUrl}/crear`, property);
  }

  searchPropertyByName(propertyName: string): Observable<PropertyResponseDTO> {
    return this.http.get<PropertyResponseDTO>(`${this.baseUrl}/${propertyName}`);
  }

  getAllProperties(): Observable<PropertyResponseDTO[]> {
    return this.http.get<PropertyResponseDTO[]>(`${this.baseUrl}/todas`);
  }

  editProperty(propertyName: string, propertyEdit: PropertyEdit): Observable<PropertyResponseDTO> {
    return this.http.put<PropertyResponseDTO>(`${this.baseUrl}/editar/${propertyName}`, propertyEdit);
  }

  deleteProperty(propertyName: string): Observable<PropertyResponseDTO> {
    return this.http.delete<PropertyResponseDTO>(`${this.baseUrl}/eliminar/${propertyName}`);
  }
}