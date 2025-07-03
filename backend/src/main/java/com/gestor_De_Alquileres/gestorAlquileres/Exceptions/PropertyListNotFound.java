package com.gestor_De_Alquileres.gestorAlquileres.Exceptions;

public class PropertyListNotFound extends RuntimeException{
    public PropertyListNotFound() {
        super("Todavia no hay propiedades cargadas");
    }
}
