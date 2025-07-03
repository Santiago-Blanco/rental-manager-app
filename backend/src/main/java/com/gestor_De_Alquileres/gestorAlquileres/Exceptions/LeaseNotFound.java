package com.gestor_De_Alquileres.gestorAlquileres.Exceptions;

public class LeaseNotFound extends RuntimeException{
    public LeaseNotFound() {
        super("No se ha encontrado el alquiler");
    }
}
