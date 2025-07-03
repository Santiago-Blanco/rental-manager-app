package com.gestor_De_Alquileres.gestorAlquileres.Exceptions;

public class ContractNotFound extends RuntimeException{
    public ContractNotFound() {
        super("No existe un contrato con esa ID");
    }
}
