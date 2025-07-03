import { Department } from './department.model';
import { Receipt } from './receipt.model';
import { Contract } from './contract.model';
import { Lease } from './lease.model';

export interface Tenant {
  id: number;
  name: string;
  lastname: string;
  dni: string;
  department?: Department;
  receiptList?: Receipt[];
  contractList?: Contract[];
  leaseList?: Lease[];
}