import { Lease } from "./lease.model";
import { Property } from "./property.model";
import { Tenant } from "./tenant.model";

export interface Department {
    id: number;
    floor: number;
    letter: string;
    property: Property;
    tenant?: Tenant;
    leaseList?: Lease[];
}
