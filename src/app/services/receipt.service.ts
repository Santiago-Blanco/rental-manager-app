import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReceiptCreate } from '../models/receipt-create';
import { Observable } from 'rxjs';
import { ReceiptEdit } from '../models/receipt-edit';
import { ReceitpResponseDto } from '../DTO/receitp-response-dto';

@Injectable({
  providedIn: 'root'
})
export class ReceiptService {
  private baseUrl: string = 'http://localhost:8080/recibo'

  constructor(private http: HttpClient) { }

  saveReceipt(receipt: ReceiptCreate): Observable<ReceitpResponseDto> {
  return this.http.post<ReceitpResponseDto>(`${this.baseUrl}/crear`, receipt);
}

previewReceipt(receipt: ReceiptCreate): Observable<any> {
  return this.http.post(`${this.baseUrl}/previsualizar`, receipt);
}

getReceipt(id: number): Observable<ReceitpResponseDto> {
  return this.http.get<ReceitpResponseDto>(`${this.baseUrl}/${id}`);
}

getReceiptByTenant(dniTenant: string): Observable<ReceitpResponseDto[]> {
  return this.http.get<ReceitpResponseDto[]>(`${this.baseUrl}/inquilino/${dniTenant}`);
}

getReceipts(): Observable<ReceitpResponseDto[]> {
  return this.http.get<ReceitpResponseDto[]>(`${this.baseUrl}/todos`);
}

deleteReceipt(id: number): Observable<ReceitpResponseDto> {
  return this.http.delete<ReceitpResponseDto>(`${this.baseUrl}/eliminar/${id}`);
}

editReceipt(id: number, receipt: ReceiptEdit): Observable<ReceitpResponseDto> {
  return this.http.put<ReceitpResponseDto>(`${this.baseUrl}/editar/${id}`, receipt);
}

editAmount(id: number, receipt: ReceitpResponseDto): Observable<ReceitpResponseDto> {
  return this.http.put<ReceitpResponseDto>(`${this.baseUrl}/ajuste/${id}`, receipt);
}
}
