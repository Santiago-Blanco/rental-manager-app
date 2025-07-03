import { AdjustmentType } from "../models/adjustment-type"

export interface ContractResponseDro {
    id: number
    startDate: Date
    endDate: Date
    adjustment: AdjustmentType

    tenantName: string
    tenantLastname: string
    dni: string

    departmentFloor: number
    departmentLetter: string

    propertyName: string
    propertyAddress: string
}
