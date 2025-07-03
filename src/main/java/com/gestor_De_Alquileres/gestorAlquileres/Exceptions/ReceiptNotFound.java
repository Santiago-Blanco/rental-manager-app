package com.gestor_De_Alquileres.gestorAlquileres.Exceptions;

public class ReceiptNotFound extends RuntimeException{
    public ReceiptNotFound() {
        super("No existe un recibo con esa ID");
    }
}
