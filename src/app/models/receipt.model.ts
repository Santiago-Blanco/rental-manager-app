export interface Receipt {
    id: number;
    date: Date;
    rent: number;
    expenses: number;
    obrasSanitarias: number;
    total: number;
    totalInString: string;
    dniTenant: string;
}
