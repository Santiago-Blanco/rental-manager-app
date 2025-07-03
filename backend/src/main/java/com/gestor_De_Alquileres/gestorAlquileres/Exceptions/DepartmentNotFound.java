package com.gestor_De_Alquileres.gestorAlquileres.Exceptions;

public class DepartmentNotFound extends RuntimeException{
    public DepartmentNotFound() {
        super("Departamento no existe");
    }
}
