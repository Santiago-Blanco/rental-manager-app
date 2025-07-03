package com.gestor_De_Alquileres.gestorAlquileres.Exceptions;

public class ContractsListNotFound extends RuntimeException{
    public ContractsListNotFound() {
        super("No hay contratos cargados");
    }
}
