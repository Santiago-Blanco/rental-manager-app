package com.gestor_De_Alquileres.gestorAlquileres.Exceptions;

public class BusyDepartment extends RuntimeException{
    public BusyDepartment() {
        super("Departamento ocupado");
    }
}
