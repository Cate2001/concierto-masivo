package com.cate.SGCM.exception;

/**
 * Señala que el precio base de un boleto no alcanza el mínimo admitido por el sistema.
 */
public class PrecioBaseInsuficienteException extends RuntimeException {
    public PrecioBaseInsuficienteException(String message) {
        super(message);
    }
}
