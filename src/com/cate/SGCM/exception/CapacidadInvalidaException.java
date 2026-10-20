package com.cate.SGCM.exception;

/**
 * Señala que la capacidad solicitada para un estadio no cumple el formato que exige el recinto.
 */
public class CapacidadInvalidaException extends RuntimeException {
    public CapacidadInvalidaException(String message) {
        super(message);
    }
}
