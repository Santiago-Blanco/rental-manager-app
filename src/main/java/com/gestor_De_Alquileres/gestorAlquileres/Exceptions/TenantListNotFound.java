package com.gestor_De_Alquileres.gestorAlquileres.Exceptions;

public class TenantListNotFound extends RuntimeException{
    public TenantListNotFound() {
        super("No hay inquilinos cargados");
    }
}
