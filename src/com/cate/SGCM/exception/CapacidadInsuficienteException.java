package com.cate.SGCM.exception;

/**
 * Señala que la capacidad solicitada para un estadio queda por debajo del mínimo permitido.
 */
public class CapacidadInsuficienteException extends RuntimeException {
    public CapacidadInsuficienteException(String message) {
        super(message);
    }
}
