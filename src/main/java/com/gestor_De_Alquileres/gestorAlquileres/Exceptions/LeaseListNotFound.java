package com.gestor_De_Alquileres.gestorAlquileres.Exceptions;

public class LeaseListNotFound extends RuntimeException{
    public LeaseListNotFound() {
        super("No se encontraron alquileres");
    }
}
