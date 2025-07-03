import { TenantResponseDTO } from "./tenant-response-dto";

export interface DepartmentResponseDTO {
    floor: number;
    letter: string;
    propertyName: string;
    tenant?: TenantResponseDTO;
}
