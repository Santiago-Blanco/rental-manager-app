import { AdjustmentType } from "./adjustment-type"

export interface ContractCreate {
    startDate: Date
    endDate: Date
    adjustment: AdjustmentType
    dni: string
}
