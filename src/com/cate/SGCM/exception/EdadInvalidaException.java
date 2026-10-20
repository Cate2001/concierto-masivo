package com.cate.SGCM.exception;

/**
 * Señala que la edad de un usuario es inferior a la mínima admitida por el sistema.
 */
public class EdadInvalidaException extends RuntimeException {
    public EdadInvalidaException(String message) {
        super(message);
    }
}
