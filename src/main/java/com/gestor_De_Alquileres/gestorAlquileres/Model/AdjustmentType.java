package com.gestor_De_Alquileres.gestorAlquileres.Model;

public enum AdjustmentType {
    EVERY_THREE_MONTHS(3),
    EVERY_FOUR_MONTHS(4);
    private final int months;

    AdjustmentType(int months) {
        this.months = months;
    }

    public int getMonths() {
        return months;
    }
}
