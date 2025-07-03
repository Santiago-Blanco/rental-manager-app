package com.gestor_De_Alquileres.gestorAlquileres.Exceptions;

public class ReceiptListNotFound extends RuntimeException{
    public ReceiptListNotFound() {
        super("No se encontraron recibos");
    }
}
