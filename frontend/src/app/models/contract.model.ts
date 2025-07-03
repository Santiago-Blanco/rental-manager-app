import { AdjustmentType } from "./adjustment-type";
import { Tenant } from "./tenant.model";

export interface Contract {
    id: number;
    startDate: Date;
    endDate: Date;
    adjustment: AdjustmentType;
    monthsUntilNextAdjustment: number;
    tenant: Tenant;
}
