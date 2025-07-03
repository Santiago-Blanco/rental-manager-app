import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Contract } from '../models/contract.model';
import { ContractCreate } from '../models/contract-create';
import { ContractEdit } from '../models/contract-edit';
import { ContractResponseDro } from '../DTO/contract-response-dto';

@Injectable({
  providedIn: 'root'
})
export class ContractService {

  private baseUrl: string = 'http://localhost:8080/contrato'

  constructor(private http: HttpClient) { }

  saveContract(contract: ContractCreate): Observable<ContractResponseDro> {
    return this.http.post<ContractResponseDro>(`${this.baseUrl}/crear`, contract)
  }

  getContract(id: number): Observable<ContractResponseDro> {
    return this.http.get<ContractResponseDro>(`${this.baseUrl}/${id}`)
  }

  getContractsByTenant(dniTenant: string): Observable<ContractResponseDro[]> {
    return this.http.get<ContractResponseDro[]>(`${this.baseUrl}/inquilino/${dniTenant}`)
  }

  getContracts(): Observable<ContractResponseDro[]> {
    return this.http.get<ContractResponseDro[]>(`${this.baseUrl}/todos`)
  }

  editContract(id: number, contract: ContractEdit): Observable<ContractResponseDro> {
    return this.http.put<ContractResponseDro>(`${this.baseUrl}/editar/${id}`, contract)
  }

  deleteContract(id: number): Observable<ContractResponseDro> {
    return this.http.delete<ContractResponseDro>(`${this.baseUrl}/eliminar/${id}`)
  }

  alertAdjustment(): Observable<ContractResponseDro[]>{
    return this.http.get<ContractResponseDro[]>(`${this.baseUrl}/aviso/ajuste`)
  }

  contractExpiringSoon(): Observable<ContractResponseDro[]>{
    return this.http.get<ContractResponseDro[]>(`${this.baseUrl}/aviso/finalizacion`)
  }
}
