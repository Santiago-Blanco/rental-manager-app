export interface ReceiptCreate {
    date: Date
    rent: number
    expenses: number
    obrasSanitarias: number
    others: number
    totalInString: string
    dniTenant: string
}
