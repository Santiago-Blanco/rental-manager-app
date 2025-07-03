import { AdjustmentType } from "./adjustment-type"

export interface ContractEdit {
    startDate?: Date
    endDate?: Date
    adjustment?: AdjustmentType
    dni?: string
}
