import { Department } from "./department.model";

export interface Property {
    id: number;
    adress: string;
    propertyName: string;
    departmentList?: Department[];
}
