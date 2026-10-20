package com.cate.SGCM.exception;

/**
 * Señala que los datos indicados coinciden con una canción ya registrada en el repertorio.
 *
 * Es una condición de negocio que quien llama puede prever, no un fallo de programación.
 */
public class CancionDuplicadaException extends RuntimeException {
    public CancionDuplicadaException(String message) {
        super(message);
    }
}
