import { Department } from "./department.model";
import { Tenant } from "./tenant.model";

export interface Lease {
    id: number;
    startDate: Date;
    endDate?: Date;
    tenant: Tenant;
    department: Department;
}
