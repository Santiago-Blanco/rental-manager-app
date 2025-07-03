package com.gestor_De_Alquileres.gestorAlquileres.Exceptions;

public class TenantNotFound extends RuntimeException {
    public TenantNotFound(String dni) {
        super("El inquilino con DNI: " + dni + " no existe");
    }
}

