package com.gestor_De_Alquileres.gestorAlquileres.Exceptions;

public class PropertyNotFound extends RuntimeException{
    public PropertyNotFound() {
        super("La propiedad no existe");
    }
}
