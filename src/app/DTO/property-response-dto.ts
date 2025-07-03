import { DepartmentResponseDTO } from "./department-response-dto";

export interface PropertyResponseDTO {
  propertyName: string;
  adress: string;
  departmentList?: DepartmentResponseDTO[];
}