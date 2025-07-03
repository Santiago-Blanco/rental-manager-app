package com.gestor_De_Alquileres.gestorAlquileres.Exceptions;

public class DepartmentListNotFound extends RuntimeException{
    public DepartmentListNotFound() {
        super("No hay departamentos cargados");
    }
}
